package com.danielbohry.bolaodacopa.repositories;

import com.danielbohry.bolaodacopa.entities.Apostador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ApostadorRepository extends MongoRepository<Apostador, String> {

    Optional<Apostador> findById(String id);

}
