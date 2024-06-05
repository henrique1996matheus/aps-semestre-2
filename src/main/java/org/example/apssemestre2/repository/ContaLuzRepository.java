package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.Consumo;
import org.example.apssemestre2.model.ContaLuz;

public class ContaLuzRepository extends BaseRepository<ContaLuz> {
    private final String TABELA = "conta_luz";

    @Override
    public boolean cadastrar(ContaLuz model) {
        String sql = "INSERT INTO " + TABELA + " (bandeira, referencia, vencimento, consumo, valor) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setObject(1, model.getBandeira());
            ps.setObject(2, model.getReferencia());
            ps.setObject(3, model.getVencimento());
            ps.setObject(4, model.getConsumo());
            ps.setObject(5, model.getValor());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public ContaLuz buscar(long id) {
        return null;
    }

    @Override
    public ContaLuz buscar(ContaLuz filtro) {
        String sql = "SELECT * FROM " + TABELA + " WHERE (vencimento = ? OR referencia = ?) AND id != ?";

        ContaLuz model = null;

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setObject(1, filtro.getVencimento());
            statement.setObject(2, filtro.getReferencia());
            statement.setObject(3, filtro.getId());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                model = new ContaLuz();

                preencherModel(model, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public boolean atualizar(ContaLuz model) {
        String sql = "UPDATE " + TABELA + " SET bandeira = ?, referencia = ?, vencimento = ?, consumo = ?, valor = ? WHERE id = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setObject(1, model.getBandeira());
            ps.setObject(2, model.getReferencia());
            ps.setObject(3, model.getVencimento());
            ps.setObject(4, model.getConsumo());
            ps.setObject(5, model.getValor());
            ps.setInt(6, model.getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean excluir(ContaLuz model) {
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
    public List<ContaLuz> listar(ContaLuz filtroModel) {
        String sql = "SELECT * FROM " + TABELA;
        String sqlWhere = " WHERE ";

        List<Object> filtros = new ArrayList<>();

//        sqlWhere = montarWhere(sqlWhere, filtros, filtroModel.getData(), "data", "=");

        if (!filtros.isEmpty()) {
            sql += sqlWhere;
        }

        List<ContaLuz> lista = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Conexao.getConexao().prepareStatement(sql);

            for (int i = 0; i < filtros.size(); i++) {
                statement.setObject(i + 1, filtros.get(i));
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ContaLuz model = new ContaLuz();

                preencherModel(model, resultSet);

                lista.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private static void preencherModel(ContaLuz model, ResultSet resultSet) throws SQLException {
        model.setId(resultSet.getInt("id"));
        model.setBandeira(resultSet.getString("bandeira"));
        model.setReferencia(resultSet.getObject("referencia", LocalDate.class));
        model.setVencimento(resultSet.getObject("vencimento", LocalDate.class));
        model.setConsumo(resultSet.getFloat("consumo"));
        model.setValor(resultSet.getFloat("valor"));
    }

    public ContaLuz buscarPorData(LocalDate dataFiltro) {
        String sql = "SELECT * FROM " + TABELA + " WHERE MONTH(referencia) = ? AND YEAR(referencia) = ?";

        ContaLuz model = null;

        try (PreparedStatement statement = Conexao.getConexao().prepareStatement(sql)) {
            statement.setObject(1, dataFiltro.getMonth().ordinal() + 1);
            statement.setObject(2, dataFiltro.getYear());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                model = new ContaLuz();

                preencherModel(model, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
}
