package com.danielbohry.bolaodacopa.services;

import com.danielbohry.bolaodacopa.entities.Apostador;
import com.danielbohry.bolaodacopa.entities.Palpite;
import com.danielbohry.bolaodacopa.entities.Partida;
import com.danielbohry.bolaodacopa.repositories.ApostadorRepository;
import com.danielbohry.bolaodacopa.repositories.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class ApostadorService {

    private static final String MSG_ERRO_APOSTADOR_NAO_EXISTE = "Esse apostador nao existe.";

    @Autowired
    private ApostadorRepository repo;

    @Autowired
    private PartidaService partidaService;

    public List<Apostador> buscar() {
        return repo.findAll();
    }

    public Apostador buscarPorId(String id) {
        Optional<Apostador> apostador = repo.findById(id);
        return apostador
                .orElseThrow(() -> new ResourceAccessException(MSG_ERRO_APOSTADOR_NAO_EXISTE));
    }

    public Apostador salvar(Apostador apostador) {
        return repo.save(apostador);
    }

    public Apostador adicionarPalpite(String id, Palpite palpite) {
        Optional<Apostador> entity = repo.findById(id);

        if (entity.isPresent()) {
            Apostador apostador = entity.get();
            List<Palpite> palpistesDoApostador = apostador.getPalpites();
            palpistesDoApostador.add(palpite);
            apostador.setPalpites(palpistesDoApostador);

            return repo.save(apostador);
        } else {
            throw new ResourceAccessException(MSG_ERRO_APOSTADOR_NAO_EXISTE);
        }
    }

    public Integer buscarPontuacao(String id) {
        Optional<Apostador> entity = repo.findById(id);

        if (entity.isPresent()) {
            Apostador apostador = entity.get();
            List<Palpite> palpites = apostador.getPalpites();
            List<Partida> partidas = partidaService.buscar();
            return calculaPontuacao(palpites, partidas);
        } else {
            throw new ResourceAccessException(MSG_ERRO_APOSTADOR_NAO_EXISTE);
        }
    }

    private Integer calculaPontuacao(List<Palpite> palpites, List<Partida> partidas) {
        Integer total = 0;

        for (Palpite p : palpites) {
            Integer pontos = 0;

            Optional<Partida> partidaApostada = partidas.stream()
                    .filter(partida -> isPartidaApostada(p, partida))
                    .findFirst();

            if (partidaApostada.isPresent()) {
                String vencedor = partidaService.getVencedor(partidaApostada.get());

                if (isResultadoExato(p, partidaApostada.get()))
                    pontos = pontos + PontosEnum.RESULTADO.getPontos();
                else if (vencedor.equals(getPalpiteVencedor(p)))
                    pontos = pontos + PontosEnum.VENCEDOR.getPontos();
                
                total = total + pontos;
            }

        }

        return total;
    }

    private String getPalpiteVencedor(Palpite palpite) {
        if (palpite.getResultadoTimeA() > palpite.getResultadoTimeB()) {
            return palpite.getTimeA();
        } else if (palpite.getResultadoTimeA() < palpite.getResultadoTimeB()) {
            return palpite.getTimeB();
        } else {
            return "empate";
        }
    }

    private boolean isResultadoExato(Palpite p, Partida partidaApostada) {
        return partidaApostada.getResultadoTimeA().equals(p.getResultadoTimeA())
                && partidaApostada.getResultadoTimeB().equals(p.getResultadoTimeB());
    }

    private boolean isPartidaApostada(Palpite p, Partida partida) {
        return partida.getTimeA().equals(p.getTimeA())
                && partida.getTimeB().equals(p.getTimeB());
    }

}
