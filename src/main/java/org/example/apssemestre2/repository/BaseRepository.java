package org.example.apssemestre2.repository;

import java.util.List;

public abstract class BaseRepository<T> {
    public abstract T buscar();

    public abstract boolean cadastrar(T model);

    public abstract boolean atualizar(T model);

    public abstract boolean excluir(T model);


    public abstract boolean salvar();

    public abstract List<T> listar();
}
