package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;

import java.util.List;

public abstract class BaseService<T> {
    public abstract T buscar();

    public abstract boolean cadastrar(T model);

    public abstract boolean atualizar(T antigo, T novo);

    public abstract boolean excluir(T model);

    public abstract boolean salvar(T model);

    public abstract List<T> listar(T filtro);
}