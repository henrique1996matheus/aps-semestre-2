package org.example.apssemestre2.service;

import org.example.apssemestre2.model.GraficoDados;

public class GraficoDadosService {

    public GraficoDados graficoDadosInicial(){
       String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16","17","18","19","20","21","22","23","24"};
       int[] Consumo = {2900, 2698, 2600, 2400, 2000, 3200, 3102, 2345, 2735, 2144, 2234, 2343, 3545, 3635, 2342, 5345,3453,4567,7456,1234,6453,1324,8546,1234};

       GraficoDados grafico = new GraficoDados();
       grafico.setData(data);
       grafico.setConsumo(Consumo);

       return grafico;
    }

    public GraficoDados graficoDadosCategoria(){
        String[] data = {"Sala", "Quarto", "Cozinha"};
        int[] Consumo = {2900, 2698, 2600};

        GraficoDados grafico = new GraficoDados();
        grafico.setData(data);
        grafico.setConsumo(Consumo);

        return grafico;
    }

    public GraficoDados graficoDadosMes(){
        String[] data = {"Jan", "Fev", "Mar", "Abr", "Mai"};
        int[] Consumo = {2900, 2698, 2600, 2400, 2000};

        GraficoDados grafico = new GraficoDados();
        grafico.setData(data);
        grafico.setConsumo(Consumo);

        return grafico;
    }

    public GraficoDados graficoDadosDia(){
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int[] Consumo = {2900, 2698, 2600, 2400, 2000, 3200, 3102, 2345, 2735};

        GraficoDados grafico = new GraficoDados();
        grafico.setData(data);
        grafico.setConsumo(Consumo);

        return grafico;
    }
}
