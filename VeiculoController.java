package com.estoque.veiculos.controller;

import com.estoque.veiculos.model.Veiculo;
import com.estoque.veiculos.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@Valid @RequestBody Veiculo veiculo) {
        Veiculo salvo = veiculoService.cadastrar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listar(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String status) {
        List<Veiculo> veiculos = veiculoService.filtrar(marca, status);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.buscarPorId(id);
        return veiculo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        Double preco = campos.get("preco") != null
                ? ((Number) campos.get("preco")).doubleValue() : null;
        Double quilometragem = campos.get("quilometragem") != null
                ? ((Number) campos.get("quilometragem")).doubleValue() : null;
        String status = campos.get("status") != null
                ? campos.get("status").toString() : null;

        try {
            Optional<Veiculo> atualizado = veiculoService.atualizarCampos(id, preco, quilometragem, status);
            return atualizado.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "Status inválido. Use: DISPONIVEL, VENDIDO, RESERVADO, MANUTENCAO"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        boolean removido = veiculoService.remover(id);
        return removido
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
