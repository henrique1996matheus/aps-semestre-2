package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.apssemestre2.model.Categoria;

public class CategoriaRepository extends BaseRepository<Categoria> {
    private final String TABELA = "categoria";

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
    public List<Categoria> listar() {

        String sql = "SELECT * FROM " + TABELA;

        List<Categoria> categorias = new ArrayList<Categoria>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();

            while (rset.next()) {

                Categoria categoria = new Categoria();

                categoria.setId(rset.getInt("id"));
                categoria.setNome(rset.getString("nome"));

                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
