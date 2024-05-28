package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.repository.AparelhoRepository;
import org.example.apssemestre2.repository.ConsumoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GraficoService {
    private final ConsumoRepository consumoRepository;
    private final AparelhoRepository aparelhoRepository;

    public GraficoService() {
        consumoRepository = new ConsumoRepository();
        aparelhoRepository = new AparelhoRepository();
    }

    // CONSUMO (kWh) = potência (W) x horas de uso por dia (h) x dias de uso no mês / 1000.

    public GraficoDados inicial(LocalDate dataFiltro) {
        var consumos = consumoRepository.listarPorData(dataFiltro);
        var aparelhos = aparelhoRepository.listar(new Aparelho());

        LocalDate dataInicial = dataFiltro.withDayOfMonth(1);

        List<LocalDate> todosDias = new ArrayList<>();
        List<Float> valores = new ArrayList<>();

        preencherDiasEValores(dataFiltro, dataInicial, valores, todosDias);

        for (int i = 0; i < todosDias.size(); ++i) {
            for (var aparelhoAtual : aparelhos) {
                var achouConsumoAparelho = false;
                float valorAp = 0;

                for (var consumoAtual : consumos) {
                    var aparelhoId = aparelhoAtual.getId();
                    var aparelhoIdConsumo = consumoAtual.getIdAparelho();

                    var dataAtual = todosDias.get(i);
                    var dataConsumoAtual = consumoAtual.getData();

                    var mesmoAparelho = aparelhoIdConsumo == aparelhoId;
                    var mesmoDia = dataConsumoAtual.equals(dataAtual);

                    if (mesmoAparelho && mesmoDia) {
                        achouConsumoAparelho = true;

                        valorAp = (Float.parseFloat(aparelhoAtual.getPotencia()) * (float) consumoAtual.getGastoHora()) / 1000;
                        break;
                    }
                }

                if (!achouConsumoAparelho) {
                    valorAp = (Float.parseFloat(aparelhoAtual.getPotencia()) * (float) aparelhoAtual.getUsoMedio()) / 1000;
                }

                var valorDia = valores.get(i);
                valores.set(i, valorDia + valorAp);
            }
        }

        String[] diasConvertidos = new String[todosDias.size()];
        String[] valoresConvertidos = new String[todosDias.size()];

        for (int i = 0; i < todosDias.size(); ++i) {
            diasConvertidos[i] = String.valueOf(todosDias.get(i).getDayOfMonth());
            valoresConvertidos[i] = String.valueOf(valores.get(i));
        }

        return new GraficoDados(diasConvertidos, valoresConvertidos);
    }

    private void preencherDiasEValores(LocalDate dataAtual, LocalDate dataInicial, List<Float> valores, List<LocalDate> todosDias) {
        LocalDate data = dataInicial;
        while (!data.isAfter(dataAtual)) {
            valores.add(0f);
            todosDias.add(data);

            data = data.plusDays(1);
        }
    }

    public GraficoDados inicial1(LocalDate dataAtual) {
        var consumos = consumoRepository.listarPorData(dataAtual);

        LocalDate dataInicial = dataAtual.withDayOfMonth(1);

        List<LocalDate> todosDias = new ArrayList<>();
        List<Float> valores = new ArrayList<>();

        preencherDiasEValores(dataAtual, dataInicial, valores, todosDias);

        var aparelhos = new ArrayList<Aparelho>();
        var apAtual = new Aparelho();

        for (var consumo : consumos) {
            var id = consumo.getIdAparelho();

            if (apAtual.getId() != id) {
                var achou = false;

                for (var apPesquisa : aparelhos) {
                    if (apPesquisa.getId() == id) {
                        apAtual = apPesquisa;
                        achou = true;
                        break;
                    }
                }

                if (!achou) {
                    apAtual = aparelhoRepository.buscar(id);
                    aparelhos.add(apAtual);
                }
            }

            var posicao = todosDias.indexOf(consumo.getData());

            var valorDia = valores.get(posicao);
            var valorAp = (Float.parseFloat(apAtual.getPotencia()) * (float) consumo.getGastoHora()) / 1000;

            valores.set(posicao, valorDia + valorAp);
        }

        String[] diasConvertidos = new String[todosDias.size()];
        String[] valoresConvertidos = new String[todosDias.size()];

        for (int i = 0; i < todosDias.size(); ++i) {
            diasConvertidos[i] = String.valueOf(todosDias.get(i).getDayOfMonth());
            valoresConvertidos[i] = String.valueOf(valores.get(i));
        }

        return new GraficoDados(diasConvertidos, valoresConvertidos);
    }

    public GraficoDados dias(LocalDate diaInicialFiltro, LocalDate diaFinalFiltro) {
        var b = consumoRepository.listarPorData(diaInicialFiltro);

        return new GraficoDados();
    }

    public GraficoDados mes(LocalDate mesInicialFiltro, LocalDate mesFinalFiltro) {
        var b = consumoRepository.listarPorData(mesInicialFiltro);

        return new GraficoDados();
    }

    public GraficoDados categorias(List<Categoria> cat) {

        return new GraficoDados();
    }


}