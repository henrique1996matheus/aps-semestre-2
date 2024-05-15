package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.apssemestre2.model.Categoria;

public class CategoriaRepository extends BaseRepository<Categoria> {
    @Override
    public Categoria buscar() {
        return null;
    }

    @Override
    public boolean salvar() {
        return false;
    }

    @Override
    public boolean cadastrar(Categoria categoria) {
        String sql = "INSERT INTO CATEGORIAS (NOMECATEGORIA) VALUES (?)";

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
        String sqlUpdateAparelhos = "UPDATE APARELHOS SET idCategoria = null WHERE idCategoria = ?";
        String sqlDeleteCategoria = "DELETE FROM CATEGORIAS WHERE idCategoria = ?";

        PreparedStatement psUpdateAparelhos = null;
        PreparedStatement psDeleteCategoria = null;

        try {
            psUpdateAparelhos = Conexao.getConexao().prepareStatement(sqlUpdateAparelhos);
            psUpdateAparelhos.setInt(1, categoria.getId());
            psUpdateAparelhos.executeUpdate();

            psDeleteCategoria = Conexao.getConexao().prepareStatement(sqlDeleteCategoria);
            psDeleteCategoria.setInt(1, categoria.getId());
            psDeleteCategoria.executeUpdate();

            psUpdateAparelhos.close();
            psDeleteCategoria.close();
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Categoria categoria) {
        String sql = "UPDATE CATEGORIAS SET nomeCategoria=? WHERE idCategoria=?";

        PreparedStatement ps = null;

        try{
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
    public List<Categoria> listar() {

        String sql = "SELECT * FROM CATEGORIAS";

        List<Categoria> categorias = new ArrayList<Categoria>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();

            while (rset.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(rset.getInt("idCategoria"));
                categoria.setNome(rset.getString("nomeCategoria"));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public List<String> listarString() {
        return List.of();
    }
}
