package com.mesttra.gerenciadorcontato.repository;

import com.mesttra.gerenciadorcontato.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    // Método para buscar por nome
    List<Contato> findByNomeContainingIgnoreCase(String nome);

    List<Contato> findByEmailContainingIgnoreCase(String email);

    List<Contato> findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCase(String nome, String email);

}
