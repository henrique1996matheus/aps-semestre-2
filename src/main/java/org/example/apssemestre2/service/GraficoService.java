package org.example.apssemestre2.service;

import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.repository.ConsumoRepository;

import java.time.LocalDate;

public class GraficoService {
    private ConsumoRepository consumoRepository;

    public GraficoService() {
        consumoRepository = new ConsumoRepository();
    }

    public GraficoDados inicial(LocalDate dataAtual) {
        var a = consumoRepository.listarPorData(dataAtual);

        return new GraficoDados();
    }

}
