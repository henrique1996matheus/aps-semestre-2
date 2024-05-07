package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.apssemestre2.model.Categoria;

public class CategoriaDAO {
    public void cadastraraCategoria(Categoria categoria){
        String sql = "INSERT INTO CATEGORIAS (NOME) VALUES (?)";

        PreparedStatement ps = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, categoria.getNome());

            ps.execute();
            ps.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void excluirCategoria(Categoria categoria) {
        String sql = "DELETE FROM CATEGORIAS WHERE idCategoria = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, categoria.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarCategoria(Categoria categoria){
        String sql = "UPDATE CATEGORIAS SET NOME=? WHERE idCategoria=?";

        PreparedStatement ps = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getId());

            ps.execute();
            ps.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Categoria> ListaCategorias(){

        String sql = "SELECT * FROM CATEGORIAS";

        List<Categoria> categorias = new ArrayList<Categoria>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();

            while (rset.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(rset.getInt("idCategoria"));
                categoria.setNome(rset.getString("nome"));

                categorias.add(categoria);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return categorias;
    }
}
