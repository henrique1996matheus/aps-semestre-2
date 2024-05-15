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
    public boolean cadastrar(Aparelho aparelho) {
        String sql = "INSERT INTO APARELHOS (NOME, MODELO, MARCA, POTENCIA,idCategoria ) VALUES (?, ?, ?, ?, ?)";

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
        String sql = "DELETE FROM APARELHOS WHERE idAparelho = ?";
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
        String sql = "UPDATE APARELHOS SET NOME=?, MODELO=?, MARCA=?, POTENCIA=?, idCategoria=? WHERE idAparelho=?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, aparelho.getNome());
            ps.setString(2, aparelho.getModelo());
            ps.setString(3, aparelho.getMarca());
            ps.setString(4, aparelho.getPotencia());
            ps.setInt(5, aparelho.getIdCategoria());
            ps.setInt(6, aparelho.getId());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public List<Aparelho> listar() {

        String sql = "SELECT aparelhos.*, categorias.nomeCategoria AS nomeCategoria " +
                "FROM aparelhos " +
                "LEFT JOIN categorias ON aparelhos.idCategoria = categorias.idCategoria";

        List<Aparelho> aparelhos = new ArrayList<>();

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

                if (rset.getObject("idCategoria") == null ) {
                    aparelho.setNomeCategoria("Sem categoria cadastrada");
                } else {
                    aparelho.setNomeCategoria(rset.getString("nomeCategoria"));
                }

                aparelhos.add(aparelho);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aparelhos;
    }

    @Override
    public List<String> listarString() {
        String sql = "SELECT nomeCategoria FROM CATEGORIAS ORDER BY nomeCategoria";

        List<String> categorias = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categorias.add(rs.getString("nomeCategoria"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public int buscarCategoriaPeloId(String nomeCategoria) {
        int idCategoria = 0;
        String sql = "SELECT idcategoria FROM categorias WHERE nomecategoria = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, nomeCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idCategoria = rs.getInt("idcategoria");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCategoria;
    }
}