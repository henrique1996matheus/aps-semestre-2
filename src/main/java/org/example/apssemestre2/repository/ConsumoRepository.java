package org.example.apssemestre2.repository;

import org.example.apssemestre2.model.Consumo;

import java.util.List;

public class ConsumoRepository extends BaseRepository<Consumo> {
    @Override
    public Consumo buscar() {
        return null;
    }

    @Override
    public boolean cadastrar(Consumo model) {

        return false;
    }

    @Override
    public boolean atualizar(Consumo model) {
        return false;
    }

    @Override
    public boolean excluir(Consumo model) {
        return false;
    }

    @Override
    public boolean salvar() {
        return false;
    }

    @Override
    public List<Consumo> listar() {
        return null;
    }
}
