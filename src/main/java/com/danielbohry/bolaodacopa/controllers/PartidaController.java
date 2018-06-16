package com.danielbohry.bolaodacopa.controllers;

import com.danielbohry.bolaodacopa.entities.Partida;
import com.danielbohry.bolaodacopa.services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    @Autowired
    PartidaService service;

    @GetMapping
    public ResponseEntity<List<Partida>> buscar() {
        List<Partida> response = service.buscar();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(String id){
        Partida response = service.buscarPorId(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Partida> salvar(@RequestBody Partida partida) {
        Partida response = service.salvar(partida);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
