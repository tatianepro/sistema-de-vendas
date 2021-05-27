package com.github.tatianepro.rest.controller;

import com.github.tatianepro.domain.entity.Cliente;
import com.github.tatianepro.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente.get());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (!cliente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.delete(cliente.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteRepository
                .findById(id)
                .map(cli -> {
                    cliente.setId(cli.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity find(Cliente filtroCliente) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtroCliente, matcher);
        List<Cliente> clientes = clienteRepository.findAll(example);
        return ResponseEntity.ok(clientes);
    }

}
