package com.estoque.veiculos.service;

import com.estoque.veiculos.model.StatusVeiculo;
import com.estoque.veiculos.model.Veiculo;
import com.estoque.veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public List<Veiculo> filtrar(String marca, String status) {
        if (marca != null && !marca.isBlank() && status != null && !status.isBlank()) {
            StatusVeiculo statusEnum = StatusVeiculo.valueOf(status.toUpperCase());
            return veiculoRepository.findByMarcaIgnoreCaseAndStatus(marca, statusEnum);
        } else if (marca != null && !marca.isBlank()) {
            return veiculoRepository.findByMarcaIgnoreCase(marca);
        } else if (status != null && !status.isBlank()) {
            StatusVeiculo statusEnum = StatusVeiculo.valueOf(status.toUpperCase());
            return veiculoRepository.findByStatus(statusEnum);
        }
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> atualizarCampos(Long id, Double preco, Double quilometragem, String status) {
        Optional<Veiculo> optional = veiculoRepository.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Veiculo veiculo = optional.get();
        if (preco != null) {
            veiculo.setPreco(preco);
        }
        if (quilometragem != null) {
            veiculo.setQuilometragem(quilometragem);
        }
        if (status != null && !status.isBlank()) {
            veiculo.setStatus(StatusVeiculo.valueOf(status.toUpperCase()));
        }
        return Optional.of(veiculoRepository.save(veiculo));
    }

    public boolean remover(Long id) {
        if (!veiculoRepository.existsById(id)) {
            return false;
        }
        veiculoRepository.deleteById(id);
        return true;
    }
}
