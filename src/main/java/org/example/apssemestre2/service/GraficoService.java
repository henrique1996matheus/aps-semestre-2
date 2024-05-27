package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.repository.ConsumoRepository;

import java.time.LocalDate;
import java.util.List;

public class GraficoService {
    private ConsumoRepository consumoRepository;

    public GraficoService() {
        consumoRepository = new ConsumoRepository();
    }

    public GraficoDados inicial(LocalDate dataAtual) {
        // Exemplo de atualização de dados do gráfico
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        String[] consumo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};

        return new GraficoDados(dias, consumo);
    }

    public GraficoDados dias(LocalDate diaInicialFiltro, LocalDate diaFinalFiltro){
        var b = consumoRepository.listarPorData(diaInicialFiltro);

        return new GraficoDados();
    }

    public GraficoDados mes(LocalDate mesInicialFiltro, LocalDate mesFinalFiltro){
        var b = consumoRepository.listarPorData(mesInicialFiltro);

        return new GraficoDados();
    }

    public GraficoDados categorias(List<Categoria> cat){

        return new GraficoDados();
    }



}
