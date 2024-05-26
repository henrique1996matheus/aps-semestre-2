package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.repository.AparelhoRepository;

import java.util.List;
import java.util.Objects;

public class AparelhoService extends BaseService<Aparelho> {
    AparelhoRepository repository;

    public AparelhoService() {
        repository = new AparelhoRepository();
    }

    @Override
    public Aparelho buscar(long id) {
        return null;
    }

    @Override
    public Aparelho buscar(Aparelho filtro) {
        return repository.buscar(filtro);
    }

    @Override
    public void cadastrar(Aparelho model) throws Exception {
        if (Objects.isNull(model) || Objects.isNull(model.getNome()) || model.getNome().isEmpty()) {
            throw new Exception("Aparelho precisa de um nome válido!");
        }

        Aparelho duplicado = buscar(new Aparelho(model.getId(), model.getNome()));

        if (Objects.nonNull(duplicado)) {
            throw new Exception("Aparelho " + model.getNome() + " já existe no sistema!");
        }

        if (model.getId() > 0) {
            repository.atualizar(model);
        } else {
            repository.cadastrar(model);
        }
    }

    @Override
    public void excluir(Aparelho aparelho) {
        repository.excluir(aparelho);
    }

    @Override
    public List<Aparelho> listar(Aparelho filtro) {
        var lista = repository.listar(filtro);

        return lista;
    }
}
