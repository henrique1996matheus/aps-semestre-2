package org.example.apssemestre2.repository;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.enums.VoltagemEnum;

import java.util.List;

public class AparelhoRepository {
    public List<Aparelho> listar() {
        List<Aparelho> lista = List.of(
                new Aparelho("TV", VoltagemEnum.v110),
                new Aparelho("Monitor", VoltagemEnum.v220)
        );

        return lista;
    }
}
