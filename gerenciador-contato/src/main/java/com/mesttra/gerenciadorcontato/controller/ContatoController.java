package com.mesttra.gerenciadorcontato.controller;

import com.mesttra.gerenciadorcontato.model.Contato;
import com.mesttra.gerenciadorcontato.repository.ContatoRepository;
import com.mesttra.gerenciadorcontato.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public List<Contato> getAllContatos() {
        return contatoService.listarTodosContatos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.consultarContatoPorId(id);
        return contato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public List<Contato> buscarPorNome(@RequestParam String nome) {
        return contatoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/email")
    public List<Contato> buscarPorEmail(@RequestParam String email) {
        return contatoRepository.findByEmailContainingIgnoreCase(email);
    }

    @GetMapping("/nome-email")
    public ResponseEntity<List<Contato>> buscarPorNomeEEmail(@RequestParam String nome, @RequestParam String email) {
        List<Contato> contatos = contatoService.buscarPorNomeEEmail(nome, email);
        if (contatos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se não encontrar nenhum contato
        }
        return ResponseEntity.ok(contatos); // Retorna 200 com a lista de contatos
    }

    @PostMapping
    public ResponseEntity<Contato> createOrUpdateContato(@RequestBody Contato contato) {
        Contato savedContato = contatoService.salvarContato(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        contatoService.excluirContato(id);
        return ResponseEntity.noContent().build();
    }

}
