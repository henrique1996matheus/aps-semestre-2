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

        // Exemplo de atualização de dados do gráfico
        String[] dias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        String[] consumo = {"34534", "22345", "323455", "2344", "5643", "23426", "5347", "6458", "2349", "632410", "12341"};
        return new GraficoDados(dias,consumo);
    }

    public GraficoDados mes(LocalDate mesInicialFiltro, LocalDate mesFinalFiltro){
        var b = consumoRepository.listarPorData(mesInicialFiltro);

        // Exemplo de atualização de dados do gráfico
        String[] meses = {"jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov"};
        String[] consumo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
        return new GraficoDados(meses,consumo);
    }

    public GraficoDados categorias(List<Categoria> cat){


        // Exemplo de atualização de dados do gráfico
        String[] cats = {"sala", "cozinha", "quarto", "escritorio"};
        String[] consumo = {"123121", "24234", "3123", "2344","3123"};
        return new GraficoDados(cats,consumo);
    }



}
