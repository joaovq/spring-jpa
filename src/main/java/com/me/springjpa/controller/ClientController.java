package com.me.springjpa.controller;

import com.me.springjpa.model.Cliente;
import com.me.springjpa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/")
public class ClientController {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @GetMapping("getAll")
    public List<Cliente> getAll(){
        List<Cliente> list = clientRepository.findAll();
        return list;
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id){
        Optional<Cliente> byId = clientRepository.findById(id);
        Cliente cliente = byId.orElse(null);
        return ResponseEntity.status(200).body(cliente);
    }

    @PostMapping("salvar")
    public ResponseEntity<String> postClient(@RequestBody Cliente cliente){
        Cliente cliente1 = clientRepository.save(cliente);
        return ResponseEntity.status(201).body("Salvo com sucesso");
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable int id,@RequestBody Cliente clienteAtualizado){
        Optional<Cliente> byId = clientRepository.findById(id);

        if (byId.isPresent()) {
            Cliente cliente = byId.get();

            cliente.setNome(clienteAtualizado.getNome());
            cliente.setIdade(clienteAtualizado.getIdade());
            cliente.setConta(clienteAtualizado.getConta());
            clientRepository.save(cliente);
            return ResponseEntity.ok().body(cliente);
        }

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("deletar/{id}")
    public String deleteClient(@PathVariable int id){
        clientRepository.delete(Objects.requireNonNull(getById(id).getBody()));
        return "Deletado com sucesso";
    }

}
