package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.apssemestre2.model.ContaLuz;

public class ContaLuzRepository extends BaseRepository<ContaLuz> {
    @Override
    public boolean cadastrar(ContaLuz contaluz) {
        String sql = "INSERT INTO CONTALUZ (BANDEIRA,CONSUMO,VALOR, REFERENCIA,VENCIMENTO,ANO) VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, contaluz.getBandeira());
            ps.setString(2, contaluz.getConsumo());
            ps.setString(3, contaluz.getValor());
            ps.setString(4, contaluz.getReferencia());
            ps.setString(5, contaluz.getVencimento());
            ps.setString(6, contaluz.getAno());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean atualizar(ContaLuz contaluz) {
        String sql = "UPDATE CONTALUZ SET BANDEIRA=?, CONSUMO=?, VALOR=?, REFERENCIA=?, VENCIMENTO=?, ANO=? WHERE idContaluz=?";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, contaluz.getBandeira());
            ps.setString(2, contaluz.getConsumo());
            ps.setString(3, contaluz.getValor());
            ps.setString(4, contaluz.getReferencia());
            ps.setString(5, contaluz.getVencimento());
            ps.setString(6, contaluz.getAno());
            ps.setInt(7, contaluz.getId());

            ps.execute();
            ps.close();

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean excluir(ContaLuz contaluz) {
        String sql = "DELETE FROM CONTALUZ WHERE idContaluz = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, contaluz.getId());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<ContaLuz> listar() {
        String sql = "SELECT * FROM CONTALUZ ORDER BY idContaluz DESC";

        List<ContaLuz> conta = new ArrayList<ContaLuz>();

        PreparedStatement ps = null;
        ResultSet rset = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            rset = ps.executeQuery();

            while (rset.next()) {

                ContaLuz contaLuz = new ContaLuz();

                contaLuz.setId(rset.getInt("idContaluz"));
                contaLuz.setBandeira(rset.getString("bandeira"));
                contaLuz.setConsumo(rset.getString("consumo"));
                contaLuz.setValor(rset.getString("valor"));
                contaLuz.setReferencia(rset.getString("referencia"));
                contaLuz.setVencimento(rset.getString("vencimento"));
                contaLuz.setAno(((ResultSet) rset).getString("ano"));

                conta.add(contaLuz);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return conta;
    }

    @Override
    public List<String> listarString() {
        String sql = "SELECT ano FROM CONTALUZ ORDER BY ano";

        List<String> anos = new ArrayList<>();
        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                anos.add(rs.getString("ano"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return anos;
    }

    @Override
    public ContaLuz buscar() {
        return null;
    }

    @Override
    public boolean salvar() {
        return false;
    }
}
