package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.example.apssemestre2.model.Categoria;

public class CategoriaRepository extends BaseRepository<Categoria> {
    private final String TABELA = "categoria";

    @Override
    public Categoria buscar(long id) {
        return null;
    }

    @Override
    public Categoria buscar(Categoria filtro) {
        var categorias = listar(filtro);

        return categorias.isEmpty() ? null : categorias.get(0);
    }

    @Override
    public boolean cadastrar(Categoria categoria) {
        String sql = "INSERT INTO " + TABELA + " (nome) VALUES (?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, categoria.getNome());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(Categoria categoria) {
        String sql = "DELETE FROM " + TABELA + " WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, categoria.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Categoria categoria) {
        String sql = "UPDATE " + TABELA + " SET nome = ? WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<Categoria> listar(Categoria filtroModel) {
        String sql = "SELECT * FROM " + TABELA;
        String sqlWhere = " WHERE ";

        List<Object> filtros = new ArrayList<>();

        sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getNome(), "nome", "=");

        if (filtroModel.getId() != 0) {
            sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getId(), "id", "!=");
        }

        if (!filtros.isEmpty()) {
            sql += sqlWhere;
        }

        List<Categoria> categorias = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Conexao.getConexao().prepareStatement(sql);

            for (int i = 0; i < filtros.size(); i++) {
                statement.setObject(i + 1, filtros.get(i));
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Categoria categoria = new Categoria();

                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }
}
