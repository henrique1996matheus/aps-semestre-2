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
        String sql = "SELECT * FROM " + TABELA + " WHERE id = ?";

        Aparelho aparelho = new Aparelho();

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                aparelho.setId(resultSet.getInt("id"));
                aparelho.setIdCategoria(resultSet.getInt("id_categoria"));
                aparelho.setNome(resultSet.getString("nome"));
                aparelho.setModelo(resultSet.getString("modelo"));
                aparelho.setMarca(resultSet.getString("marca"));
                aparelho.setPotencia(resultSet.getString("potencia"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aparelho;
    }

    @Override
    public Aparelho buscar(Aparelho filtro) {
        var aparelhos = listar(filtro);

        return aparelhos.isEmpty() ? null : aparelhos.get(0);
    }

    @Override
    public boolean cadastrar(Aparelho model) {
        String sql = "INSERT INTO " + TABELA + " (nome, modelo, marca, potencia, id_categoria) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, model.getNome());
            ps.setString(2, model.getModelo());
            ps.setString(3, model.getMarca());
            ps.setString(4, model.getPotencia());
            ps.setInt(5, model.getIdCategoria());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(Aparelho model) {
        String sql = "DELETE FROM " + TABELA + " WHERE id = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, model.getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Aparelho aparelho) {
        String sql = "UPDATE " + TABELA + " SET id_categoria = ?, nome = ?, modelo = ?, marca = ?, potencia = ?, uso_medio = ? WHERE id = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setInt(1, aparelho.getIdCategoria());
            ps.setString(2, aparelho.getNome());
            ps.setString(3, aparelho.getModelo());
            ps.setString(4, aparelho.getMarca());
            ps.setString(5, aparelho.getPotencia());
            ps.setInt(6, aparelho.getUsoMedio());
            ps.setInt(7, aparelho.getId());

            ps.execute();
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

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            for (int i = 0; i < filtros.size(); i++) {
                statement.setObject(i + 1, filtros.get(i));
            }

            ResultSet resultSet = statement.executeQuery();

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
