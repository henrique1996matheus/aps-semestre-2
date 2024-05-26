package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.repository.AparelhoRepository;

import java.util.List;

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
        return null;
    }

    @Override
    public void cadastrar(Aparelho aparelho) {
        repository.cadastrar(aparelho);
    }

    @Override
    public void excluir(Aparelho aparelho) {
        repository.excluir(aparelho);
    }

    @Override
    public List<Aparelho> listar(Aparelho filtro) {
//        filtro.setIdCategoria(1);
        var lista = repository.listar(filtro);

        return lista;
    }
}
