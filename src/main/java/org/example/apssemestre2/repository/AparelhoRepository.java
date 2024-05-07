package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.apssemestre2.model.Aparelho;

public class AparelhoRepository extends BaseRepository<Aparelho> {
    @Override
    public Aparelho buscar() {
        return null;
    }

    @Override
    public boolean salvar() {
        return false;
    }

    @Override
    public void cadastrar(Aparelho aparelho) {
        String sql = "INSERT INTO APARELHOS (NOME,MODELO,MARCA,POTENCIA) VALUES (?,?,?,?)";

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
        }
    }

    @Override
    public void excluir(Aparelho aparelho) {
        String sql = "DELETE FROM APARELHOS WHERE idAparelho = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, aparelho.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Aparelho aparelho) {
        String sql = "UPDATE APARELHOS SET NOME=?, MODELO=?, MARCA=?, POTENCIA=? WHERE idAparelho=?";

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
        }
    }

    @Override
    public List<Aparelho> listar() {

        String sql = "SELECT * FROM APARELHOS";

        List<Aparelho> aparelhos = new ArrayList<Aparelho>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();

            while (rset.next()) {

                Aparelho aparelho = new Aparelho();

                aparelho.setId(rset.getInt("idAparelho"));
                aparelho.setNome(rset.getString("nome"));
                aparelho.setModelo(rset.getString("modelo"));
                aparelho.setMarca(rset.getString("marca"));
                aparelho.setPotencia(rset.getString("potencia"));

                aparelhos.add(aparelho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aparelhos;
    }
}
