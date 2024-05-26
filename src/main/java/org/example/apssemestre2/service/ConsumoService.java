package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Consumo;
import org.example.apssemestre2.repository.ConsumoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsumoService extends BaseService<Consumo> {
    private final ConsumoRepository repository;

    public ConsumoService() {
        repository = new ConsumoRepository();
    }

    @Override
    public Consumo buscar(long id) {
        return null;
    }

    @Override
    public Consumo buscar(Consumo filtro) {
        return repository.buscar(filtro);
    }

    @Override
    public void cadastrar(Consumo model) throws Exception {
        if (Objects.isNull(model)) {
            throw new Exception("Consumo está nulo.");
        }

        if (Objects.isNull(model.getData())) {
            throw new Exception("Consumo precisa de uma data!");
        }

        if (model.getGastoHora() <= 0) {
            throw new Exception("Gasto em hora precisa ser um valor maior que zero!");
        }

        if (model.getIdAparelho() == 0) {
            throw new Exception("É necessário informar um aparelho!");
        }

        Consumo duplicado = buscar(new Consumo(model.getId(), model.getIdAparelho(), model.getData()));

        if (Objects.nonNull(duplicado)) {
            throw new Exception("Já existe um registro deste aparelho nesta data!");
        }

        if (model.getId() > 0) {
            repository.atualizar(model);
        } else {
            repository.cadastrar(model);
        }
    }

    @Override
    public void excluir(Consumo model) throws Exception {
        repository.excluir(model);
    }

    @Override
    public List<Consumo> listar(Consumo filtro) {
        var lista = repository.listar(filtro);

        return lista;
    }
}
