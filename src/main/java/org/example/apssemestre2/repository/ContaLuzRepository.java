package org.example.apssemestre2.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.example.apssemestre2.model.ContaLuz;

public class ContaLuzRepository extends BaseRepository<ContaLuz> {
    private final String TABELA = "conta_luz";

    @Override
    public boolean cadastrar(ContaLuz contaluz) {
        String sql = "INSERT INTO " + TABELA + " (bandeira, referencia, vencimento, consumo, valor) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, contaluz.getBandeira());
            ps.setString(2, contaluz.getReferencia());
            ps.setString(3, contaluz.getVencimento());
            ps.setString(4, contaluz.getConsumo());
            ps.setString(5, contaluz.getValor());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ContaLuz buscar() {
        return null;
    }

    @Override
    public boolean atualizar(ContaLuz model) {
        return false;
    }

    @Override
    public boolean excluir(ContaLuz model) {
        return false;
    }

    @Override
    public boolean salvar() {
        return false;
    }

    @Override
    public List<ContaLuz> listar(ContaLuz filtroModel) {
        return null;
    }
}
