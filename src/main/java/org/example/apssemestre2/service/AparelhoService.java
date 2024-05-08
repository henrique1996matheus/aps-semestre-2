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
    public Aparelho buscar() {
        return null;
    }

    @Override
    public boolean cadastrar(Aparelho aparelho) {
        return repository.cadastrar(aparelho);
    }

    @Override
    public boolean atualizar(Aparelho aparelhoAntigo, Aparelho aparelhoNovo) {
        aparelhoNovo.setId(aparelhoAntigo.getId());

        if (repository.atualizar(aparelhoNovo)) {
            aparelhoAntigo.setNome(aparelhoAntigo.getNome());
            aparelhoAntigo.setModelo(aparelhoAntigo.getModelo());
            aparelhoAntigo.setMarca(aparelhoAntigo.getMarca());
            aparelhoAntigo.setPotencia(aparelhoAntigo.getPotencia());

            return true;
        }

        return false;
    }

    @Override
    public boolean excluir(Aparelho aparelho) {
        return repository.excluir(aparelho);
    }

    @Override
    public boolean salvar(Aparelho aparelho) {
        return false;
    }

    @Override
    public List<Aparelho> listar() {
        var lista = repository.listar();

        return lista;
    }
}
