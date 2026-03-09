package com.estoque.veiculos.repository;

import com.estoque.veiculos.model.StatusVeiculo;
import com.estoque.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByMarcaIgnoreCase(String marca);

    List<Veiculo> findByStatus(StatusVeiculo status);

    List<Veiculo> findByMarcaIgnoreCaseAndStatus(String marca, StatusVeiculo status);
}
