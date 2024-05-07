package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.example.apssemestre2.model.ContaLuz;

public class ContaLuzDao {
    public void cadastrarConta(ContaLuz contaluz){
        String sql = "INSERT INTO CONTALUZ (BANDEIRA,REFERENCIA,VENCIMENTO,CONSUMO,VALOR) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, contaluz.getBandeira());
            ps.setString(2, contaluz.getReferencia());
            ps.setString(3, contaluz.getVencimento());
            ps.setString(4, contaluz.getConsumo());
            ps.setString(5, contaluz.getValor());

            ps.execute();
            ps.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
