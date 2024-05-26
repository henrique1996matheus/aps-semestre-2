package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.repository.AparelhoRepository;
import org.example.apssemestre2.repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaService extends BaseService<Categoria> {

    private final CategoriaRepository repository;
    private final AparelhoRepository aparelhoRepository;

    public CategoriaService() {
        repository = new CategoriaRepository();
        aparelhoRepository = new AparelhoRepository();
    }

    @Override
    public Categoria buscar(long id) {
        return null;
    }

    @Override
    public Categoria buscar(Categoria filtro) {
        return repository.buscar(filtro);
    }

    @Override
    public void cadastrar(Categoria model) throws Exception {
        Categoria duplicado = buscar(model);

        if (Objects.nonNull(duplicado)) {
            throw new Exception("Categoria " + model.getNome() + " já existe no sistema!");
        }

        if (model.getId() > 0) {
            repository.atualizar(model);
        } else {
            repository.cadastrar(model);
        }
    }

    @Override
    public void excluir(Categoria model) throws Exception {
        Aparelho filtroAparelho = new Aparelho();
        filtroAparelho.setIdCategoria(model.getId());

        var aparelhos = aparelhoRepository.listar(filtroAparelho);

        if (!aparelhos.isEmpty()) {
            throw new Exception("Categoria não pode ser apaga pois está sendo utilizada em " + aparelhos.size() + " aparelho(s)!");
        }

        repository.excluir(model);
    }

    @Override
    public List<Categoria> listar(Categoria filtro) {
        return repository.listar(filtro);
    }
}
