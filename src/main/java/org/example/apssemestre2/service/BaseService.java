package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;

import java.util.List;

public abstract class BaseService<T> {
    public abstract T buscar(long id);
    public abstract T buscar(T filtro);

    public abstract void cadastrar(T model) throws Exception;

    public abstract void excluir(T model) throws Exception;

    public abstract List<T> listar(T filtro);
}