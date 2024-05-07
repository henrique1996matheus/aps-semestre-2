package org.example.apssemestre2.repository;

import java.util.List;

public abstract class BaseRepository<T> {
    public abstract T buscar();

    public abstract void cadastrar(T entidade);

    public abstract void atualizar(T entidade);

    public abstract void excluir(T entidade);


    public abstract boolean salvar();

    public abstract List<T> listar();
}
