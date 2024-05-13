package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService extends BaseService<Categoria> {

    private final CategoriaRepository repository;

    public CategoriaService() {
        this.repository = new CategoriaRepository();
    }

    @Override
    public Categoria buscar() {
        return null;
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
        return false;
    }

    @Override
    public boolean salvar(Categoria model) {
        return false;
    }

    @Override
    public List<Categoria> listar() {
        return new ArrayList<>();
    }
}
