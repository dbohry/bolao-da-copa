package com.danielbohry.bolaodacopa.services;

import com.danielbohry.bolaodacopa.entities.Apostador;
import com.danielbohry.bolaodacopa.entities.Palpite;
import com.danielbohry.bolaodacopa.repositories.ApostadorRepository;
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

}
