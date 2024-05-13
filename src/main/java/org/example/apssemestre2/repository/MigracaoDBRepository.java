package org.example.apssemestre2.repository;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MigracaoDBRepository {

    private static MigracaoDBRepository instancia;

    private MigracaoDBRepository() {
    }

    public static MigracaoDBRepository getInstancia() {
        if (instancia == null) {
            instancia = new MigracaoDBRepository();
        }

        return instancia;
    }

    public void migrar(String sql) {
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
