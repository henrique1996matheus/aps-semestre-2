package org.example.apssemestre2.repository;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.enums.VoltagemEnum;

import java.util.List;

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
    public List<Aparelho> listar() {
        List<Aparelho> lista = List.of(
                new Aparelho("TV", VoltagemEnum.v110),
                new Aparelho("Monitor", VoltagemEnum.v220)
        );

        return lista;
    }
}
