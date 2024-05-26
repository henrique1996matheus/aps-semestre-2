package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Consumo;
import org.example.apssemestre2.model.ContaLuz;
import org.example.apssemestre2.repository.Conexao;
import org.example.apssemestre2.repository.ContaLuzRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ContaLuzService extends BaseService<ContaLuz> {
    private final String TABELA = "conta_luz";
    private final ContaLuzRepository repository;

    public ContaLuzService() {
        repository = new ContaLuzRepository();
    }

    @Override
    public ContaLuz buscar(long id) {
        return null;
    }

    @Override
    public ContaLuz buscar(ContaLuz filtro) {
        return repository.buscar(filtro);
    }

    @Override
    public void cadastrar(ContaLuz model) throws Exception {
        if (Objects.isNull(model)) {
            throw new Exception("Conta de Luz está nulo.");
        }

        if (Objects.isNull(model.getBandeira())) {
            throw new Exception("Conta de Luz precisa de uma bandeira!");
        }

        if (Objects.isNull(model.getReferencia())) {
            throw new Exception("Conta de Luz precisa de uma bandeira!");
        }

        if (Objects.isNull(model.getVencimento())) {
            throw new Exception("Conta de Luz precisa de um vencimento!");
        }

        if (model.getConsumo() <= 0) {
            throw new Exception("Conta de Luz precisa de um consumo maior que zero!");
        }

        if (model.getValor() <= 0) {
            throw new Exception("Conta de Luz precisa de um valor maior que zero!");
        }

        ContaLuz duplicado = buscar(new ContaLuz(model.getId(), model.getVencimento(), model.getReferencia()));

        if (Objects.nonNull(duplicado)) {
            throw new Exception("Já existe uma Conta de Luz com esse Vencimento ou Referência!");
        }

        if (model.getId() > 0) {
            repository.atualizar(model);
        } else {
            repository.cadastrar(model);
        }
    }

    @Override
    public void excluir(ContaLuz model) {
        repository.excluir(model);
    }

    @Override
    public List<ContaLuz> listar(ContaLuz filtro) {
        var lista = repository.listar(filtro);

        return lista;
    }
}
