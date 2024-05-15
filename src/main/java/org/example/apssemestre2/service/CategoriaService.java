package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.repository.CategoriaRepository;

import java.util.List;

public class CategoriaService extends BaseService<Categoria> {
    CategoriaRepository repository;

    public CategoriaService() {
        this.repository = new CategoriaRepository();
    }

    @Override
    public boolean cadastrar(Categoria model) {
        return repository.cadastrar(model);
    }

    @Override
    public boolean atualizar(Categoria antigo, Categoria novo) {
        novo.setId(antigo.getId());

        if (repository.atualizar(novo)) {
            antigo.setNome(novo.getNome());
        }

        return false;
    }



    @Override
    public boolean excluir(Categoria model) {
        return repository.excluir(model);
    }

    @Override
    public boolean salvar(Categoria model) {
        return false;
    }

    @Override
    public List<Categoria> listar() {
        var lista = repository.listar();
        return lista;
    }

    @Override
    public Categoria buscar() {
        return null;
    }

    @Override
    public List<String> listarString() {
        return List.of();
    }
}
