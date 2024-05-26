package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.example.apssemestre2.model.Aparelho;

public class AparelhoRepository extends BaseRepository<Aparelho> {
    private final String TABELA = "aparelho";

    @Override
    public Aparelho buscar(long id) {
        return null;
    }

    @Override
    public Aparelho buscar(Aparelho filtro) {
        var aparelhos = listar(filtro);

        return aparelhos.isEmpty() ? null : aparelhos.get(0);
    }

    @Override
    public boolean cadastrar(Aparelho aparelho) {
        String sql = "INSERT INTO " + TABELA + " (nome, modelo, marca, potencia, id_categoria) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, aparelho.getNome());
            ps.setString(2, aparelho.getModelo());
            ps.setString(3, aparelho.getMarca());
            ps.setString(4, aparelho.getPotencia());
            ps.setInt(5, aparelho.getIdCategoria());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(Aparelho aparelho) {
        String sql = "DELETE FROM " + TABELA + " WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, aparelho.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Aparelho aparelho) {
        String sql = "UPDATE " + TABELA + " SET id_categoria = ?, nome = ?, modelo = ?, marca = ?, potencia = ?, uso_medio = ? WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, aparelho.getIdCategoria());
            ps.setString(2, aparelho.getNome());
            ps.setString(3, aparelho.getModelo());
            ps.setString(4, aparelho.getMarca());
            ps.setString(5, aparelho.getPotencia());
            ps.setInt(6, aparelho.getUsoMedio());
            ps.setInt(7, aparelho.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<Aparelho> listar(Aparelho filtroModel) {
        String sql = "SELECT * FROM " + TABELA;
        String sqlWhere = " WHERE ";

        List<Object> filtros = new ArrayList<>();

        sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getNome(), "nome", "=");

        if (Objects.nonNull(filtroModel.getIdCategoria()) && filtroModel.getIdCategoria() != 0) {
            sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getIdCategoria(), "id_categoria", "=");
        }

        if (Objects.nonNull(filtroModel.getId()) && filtroModel.getId() != 0) {
            sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getId(), "id", "!=");
        }

        if (!filtros.isEmpty()) {
            sql += sqlWhere;
        }

        List<Aparelho> aparelhos = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Conexao.getConexao().prepareStatement(sql);

            for (int i = 0; i < filtros.size(); i++) {
                statement.setObject(i + 1, filtros.get(i));
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Aparelho aparelho = new Aparelho();

                aparelho.setId(resultSet.getInt("id"));
                aparelho.setIdCategoria(resultSet.getInt("id_categoria"));
                aparelho.setNome(resultSet.getString("nome"));
                aparelho.setModelo(resultSet.getString("modelo"));
                aparelho.setMarca(resultSet.getString("marca"));
                aparelho.setPotencia(resultSet.getString("potencia"));

                aparelhos.add(aparelho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aparelhos;
    }
}
