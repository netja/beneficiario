package com.jardim.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jardim.model.Beneficiario;


public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

    List<Beneficiario> findByNome(String nome);
}
