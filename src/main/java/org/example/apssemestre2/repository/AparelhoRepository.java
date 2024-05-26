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
    public Aparelho buscar() {
        return null;
    }

    @Override
    public boolean salvar() {
        return false;
    }

    @Override
    public boolean cadastrar(Aparelho aparelho) {
        String sql = "INSERT INTO " + TABELA + " (nome, modelo, marca, potencia) VALUES (?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, aparelho.getNome());
            ps.setString(2, aparelho.getModelo());
            ps.setString(3, aparelho.getMarca());
            ps.setString(4, aparelho.getPotencia());

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
        String sql = "UPDATE " + TABELA + " SET nome = ?, modelo = ?, marca = ?, potencia = ? WHERE id = ?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, aparelho.getNome());
            ps.setString(2, aparelho.getModelo());
            ps.setString(3, aparelho.getMarca());
            ps.setString(4, aparelho.getPotencia());
            ps.setInt(5, aparelho.getId());

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

        if (Objects.nonNull(filtroModel.getIdCategoria())) {
            sql += " WHERE id_categoria = ? ";
        }

        List<Aparelho> aparelhos = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = Conexao.getConexao().prepareStatement(sql);

            if (Objects.nonNull(filtroModel.getIdCategoria())) {
                statement.setInt(1, filtroModel.getIdCategoria());
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
