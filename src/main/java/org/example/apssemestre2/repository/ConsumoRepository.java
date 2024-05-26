package org.example.apssemestre2.repository;

import org.example.apssemestre2.model.Consumo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsumoRepository extends BaseRepository<Consumo> {
    private final String TABELA = "consumo";

    @Override
    public Consumo buscar(long id) {
        return null;
    }

    @Override
    public Consumo buscar(Consumo filtro) {
        var usos = listar(filtro);

        return usos.isEmpty() ? null : usos.get(0);
    }

    @Override
    public boolean cadastrar(Consumo model) {
        String sql = "INSERT INTO " + TABELA + " (data, gasto_hora, id_aparelho) VALUES (?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setObject(1, model.getData());
            ps.setInt(2, model.getGastoHora());
            ps.setInt(3, model.getIdAparelho());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Consumo model) {
        String sql = "UPDATE " + TABELA + " SET data = ?, gasto_hora = ?, id_aparelho = ? WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setObject(1, model.getData());
            ps.setInt(2, model.getGastoHora());
            ps.setInt(3, model.getIdAparelho());
            ps.setInt(4, model.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(Consumo model) {
        String sql = "DELETE FROM " + TABELA + " WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, model.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<Consumo> listar(Consumo filtroModel) {
        String sql = "SELECT * FROM " + TABELA;
        String sqlWhere = " WHERE ";

        List<Object> filtros = new ArrayList<>();

        sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getData(), "data", "=");

        if (Objects.nonNull(filtroModel.getIdAparelho()) && filtroModel.getIdAparelho() != 0) {
            sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getIdAparelho(), "id_aparelho", "=");
        }

        if (Objects.nonNull(filtroModel.getId()) && filtroModel.getId() != 0) {
            sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getId(), "id", "!=");
        }

        if (!filtros.isEmpty()) {
            sql += sqlWhere;
        }

        List<Consumo> consumos = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Conexao.getConexao().prepareStatement(sql);

            for (int i = 0; i < filtros.size(); i++) {
                statement.setObject(i + 1, filtros.get(i));
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Consumo aparelho = new Consumo();

                preencherModel(aparelho, resultSet);

                consumos.add(aparelho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consumos;
    }

    private static void preencherModel(Consumo aparelho, ResultSet resultSet) throws SQLException {
        aparelho.setId(resultSet.getInt("id"));
        aparelho.setIdAparelho(resultSet.getInt("id_aparelho"));
        aparelho.setData(resultSet.getObject("data", LocalDate.class));
        aparelho.setGastoHora(resultSet.getInt("gasto_hora"));
    }

    public List<Consumo> listarPorData(LocalDate dataAtual) {
        String sql = " select c.id_aparelho, data, c.gasto_hora " +
                " from " + TABELA + " c " +
                " where " +
                " year(data) = ? " +
                " and month(data) = ? " +
                " order by id_aparelho, data";

        List<Consumo> consumos = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Conexao.getConexao().prepareStatement(sql);

            statement.setObject(1, dataAtual.getYear());
            statement.setObject(2, dataAtual.getMonth().ordinal() + 1);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Consumo aparelho = new Consumo();

                preencherModel(aparelho, resultSet);

                consumos.add(aparelho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
