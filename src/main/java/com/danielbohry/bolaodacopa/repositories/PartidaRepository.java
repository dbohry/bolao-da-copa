package com.danielbohry.bolaodacopa.repositories;

import com.danielbohry.bolaodacopa.entities.Partida;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartidaRepository extends MongoRepository<Partida, String> {

    Optional<Partida> findById(String id);

}
