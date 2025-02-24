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

    //findAll default em repository
    public List<Contato> listarTodosContatos(){
        return contatoRepository.findAll();
    }

    //findById -  default em repository
    public Optional<Contato> consultarContatoPorId(Long id){
        return contatoRepository.findById(id);
    }

    public List<Contato> buscarPorNome(String nome){
        return contatoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Contato> buscarPorEmail(String email){
        return contatoRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<Contato> buscarPorNomeEEmail(String nome, String email) {
        return contatoRepository.findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(nome, email);
    }

    public List<Contato> buscarPorCpf(String cpf){
       return contatoRepository.findByCpfContainingIgnoreCase(cpf);
    }

    public Contato salvarContato(Contato contato){
        return contatoRepository.save(contato);
    }

    public void excluirContato(Long id){
        contatoRepository.deleteById(id);
    }
}
