package org.example.apssemestre2.repository;

import java.util.List;
import java.util.Objects;

public abstract class BaseRepository<T> {
    public abstract T buscar(long id);

    public abstract T buscar(T filtro);

    public abstract boolean cadastrar(T model);

    public abstract boolean atualizar(T model);

    public abstract boolean excluir(T model);

    public abstract List<T> listar(T filtroModel);

    protected String montarWhere(String where, List<Object> listaFiltro, Object valor, String campo, String operador) {
        if (Objects.nonNull(valor)) {
            if (!listaFiltro.isEmpty()) {
                where += " AND ";
            }

            where += " " + campo + " " + operador + " ? ";
            listaFiltro.add(valor);
        }

        return where;
    }
}
