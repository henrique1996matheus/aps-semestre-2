package org.example.apssemestre2.service;

import java.util.List;

public abstract class BaseService<T> {
    public abstract T buscar();

    public abstract boolean cadastrar(T model);

    public abstract boolean atualizar(T model);

    public abstract boolean excluir(T model);

    public abstract boolean salvar(T model);

    public abstract List<T> listar();
}