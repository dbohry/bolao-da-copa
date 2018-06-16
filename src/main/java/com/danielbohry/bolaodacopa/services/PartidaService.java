package com.danielbohry.bolaodacopa.services;

import com.danielbohry.bolaodacopa.entities.Partida;
import com.danielbohry.bolaodacopa.repositories.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository repo;

    public List<Partida> buscar() {
        List<Partida> partidas = repo.findAll();
        return partidas;
    }

    public Partida buscarPorId(String id) {
        Optional<Partida> partida = repo.findById(id);
        return partida
                .orElseThrow(() -> new ResourceAccessException("Essa partida nao existe."));
    }

    public Partida salvar(Partida p) {
        return repo.save(p);
    }

}
