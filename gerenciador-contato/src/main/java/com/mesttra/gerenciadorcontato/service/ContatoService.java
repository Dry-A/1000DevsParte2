package com.mesttra.gerenciadorcontato.service;

import com.mesttra.gerenciadorcontato.model.Contato;
import com.mesttra.gerenciadorcontato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private  ContatoRepository contatoRepository;

    public List<Contato> listarTodosContatos(){
        return contatoRepository.findAll();
    }

    public Optional<Contato> consultarContatoPorId(Long id){
        return contatoRepository.findById(id);
    }

    public List<Contato> buscarPorNomeEEmail(String nome, String email) {
        return contatoRepository.findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(nome, email);
    }


    public Contato salvarContato(Contato contato){
        return contatoRepository.save(contato);
    }

    public void excluirContato(Long id){
        contatoRepository.deleteById(id);
    }
}
