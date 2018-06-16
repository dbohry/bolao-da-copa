package com.danielbohry.bolaodacopa.controllers;

import com.danielbohry.bolaodacopa.entities.Apostador;
import com.danielbohry.bolaodacopa.entities.Palpite;
import com.danielbohry.bolaodacopa.services.ApostadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apostadores")
public class ApostadorController {

    @Autowired
    private ApostadorService service;

    @GetMapping
    public ResponseEntity<List<Apostador>> buscar() {
        List<Apostador> response = service.buscar();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apostador> buscarPorId(@PathVariable("id") String id) {
        Apostador response = service.buscarPorId(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Apostador> salvar(@RequestBody Apostador apostador) {
        Apostador response = service.salvar(apostador);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apostador> adicionarPalpite(@PathVariable("id") String id,
                                                      @RequestBody Palpite palpite) {
        Apostador response = service.adicionarPalpite(id, palpite);
        return ResponseEntity.ok().body(response);
    }
}
