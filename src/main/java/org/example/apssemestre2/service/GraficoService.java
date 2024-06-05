package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.model.Categoria;
import org.example.apssemestre2.model.Consumo;
import org.example.apssemestre2.model.GraficoDados;
import org.example.apssemestre2.repository.AparelhoRepository;
import org.example.apssemestre2.repository.ConsumoRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class GraficoService {
    private final ConsumoRepository consumoRepository;
    private final AparelhoRepository aparelhoRepository;

    public GraficoService() {
        consumoRepository = new ConsumoRepository();
        aparelhoRepository = new AparelhoRepository();
    }

    public GraficoDados inicial(LocalDate dataFiltro) {
        var consumos = consumoRepository.listarPorData(dataFiltro);
        var aparelhos = aparelhoRepository.listar(new Aparelho());

        LocalDate dataInicial = dataFiltro.withDayOfMonth(1);

        List<LocalDate> todosDias = new ArrayList<>();
        List<Float> valores = new ArrayList<>();

        gerarDiasEValores(dataFiltro, dataInicial, valores, todosDias);
        preencherValores(todosDias, aparelhos, consumos, valores);

        return gerarGraficoDadosPorDia(todosDias, valores);
    }

    public GraficoDados dias(LocalDate dataInicial, LocalDate dataFinal) {
        var consumos = consumoRepository.listarPorData(dataInicial, dataFinal);
        var aparelhos = aparelhoRepository.listar(new Aparelho());

        long diasDiferenca = ChronoUnit.DAYS.between(dataInicial, dataFinal);

        if (diasDiferenca < 0 || diasDiferenca > 400) {
            return new GraficoDados(new String[0], new String[0]);
        }

        List<LocalDate> todosDias = new ArrayList<>();
        List<Float> valores = new ArrayList<>();

        gerarDiasEValores(dataFinal, dataInicial, valores, todosDias);
        preencherValores(todosDias, aparelhos, consumos, valores);

        return gerarGraficoDadosPorDia(todosDias, valores);
    }

    public GraficoDados mes(LocalDate mesInicialFiltro, LocalDate mesFinalFiltro) {
        var dataInicial = mesInicialFiltro.withDayOfMonth(1);
        var dataFinal = mesFinalFiltro.with(TemporalAdjusters.lastDayOfMonth());

        long diasDiferenca = ChronoUnit.DAYS.between(dataInicial, dataFinal);

        if (diasDiferenca < 0 || diasDiferenca > 400) {
            return new GraficoDados(new String[0], new String[0]);
        }

        var consumos = consumoRepository.listarPorData(dataInicial, dataFinal);
        var aparelhos = aparelhoRepository.listar(new Aparelho());

        List<LocalDate> todosDias = new ArrayList<>();
        List<Float> valoresDias = new ArrayList<>();

        gerarDiasEValores(dataFinal, dataInicial, valoresDias, todosDias);
        preencherValores(todosDias, aparelhos, consumos, valoresDias);

        List<String> todosMeses = new ArrayList<>();
        List<Float> valoresMeses = new ArrayList<>();

        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("MM/yy");

        calcularPorMes(todosDias, formatacao, todosMeses, valoresMeses, valoresDias);

        return gerarGraficoDados(todosMeses, valoresMeses);
    }

    public GraficoDados categorias(List<Categoria> categorias, int anoFiltro) {
        var aparelhos = aparelhoRepository.listarPorCategorias(categorias);

        LocalDate dataInicial = LocalDate.of(anoFiltro, 1, 1);
        LocalDate dataFinal = LocalDate.of(anoFiltro, 12, 31);

        float[][] valoresPorCategoria = new float[categorias.size()][12];

        for (int i = 0; i < categorias.size(); i++) {
            for (int j = 0; j < 12; j++) {
                valoresPorCategoria[i][j] = 0f;
            }
        }

        for (int i = 0; i < categorias.size(); i++) {
            var categoria = categorias.get(i);
            var consumos = consumoRepository.listarPorCategorias(categoria, anoFiltro);

            List<LocalDate> todosDias = new ArrayList<>();
            List<Float> valoresDias = new ArrayList<>();

            gerarDiasEValores(dataFinal, dataInicial, valoresDias, todosDias);
            preencherValores(todosDias, aparelhos, consumos, valoresDias);

            List<String> todosMeses = new ArrayList<>();
            List<Float> valoresMeses = new ArrayList<>();

            DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("MM/yy");

            for (Month mes : Month.values()) {
                LocalDate data = LocalDate.of(anoFiltro, mes, 1);
                String mesFormatado = data.format(formatacao);

                todosMeses.add(mesFormatado);
                valoresMeses.add(0f);
            }

            calcularPorMes(todosDias, formatacao, todosMeses, valoresMeses, valoresDias);

            for (int j = 0; j < 12; j++) {
                valoresPorCategoria[i][j] += valoresMeses.get(j);
            }
        }

        return gerarGraficoDados(categorias, valoresPorCategoria);
    }

    private static GraficoDados gerarGraficoDados(List<Categoria> categorias, float[][] valoresPorCategoria) {
        String[] nomesCategorias = categorias.stream()
                .map(Categoria::getNome)
                .toArray(String[]::new);

        String[][] consumosCategorias = new String[nomesCategorias.length][12];
        for (int i = 0; i < nomesCategorias.length; i++) {
            for (int j = 0; j < 12; j++) {
                consumosCategorias[i][j] = String.valueOf(valoresPorCategoria[i][j]);
            }
        }

        return new GraficoDados(nomesCategorias, consumosCategorias);
    }

    private static void calcularPorMes(List<LocalDate> todosDias, DateTimeFormatter formatacao, List<String> todosMeses, List<Float> valoresMeses, List<Float> valoresDias) {
        for (int i = 0; i < todosDias.size(); ++i) {
            LocalDate data = todosDias.get(i);
            String mes = data.format(formatacao);

            int posicao = todosMeses.indexOf(mes);

            if (posicao == -1) {
                todosMeses.add(mes);
                valoresMeses.add(0f);

                posicao = todosMeses.size() - 1;
            }

            var valorMes = valoresMeses.get(posicao);
            valoresMeses.set(posicao, valorMes + valoresDias.get(i));
        }
    }

    private void preencherValores(List<LocalDate> todosDias, List<Aparelho> aparelhos, List<Consumo> consumos, List<Float> valores) {
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
    }

    private GraficoDados gerarGraficoDadosPorDia(List<LocalDate> todosDias, List<Float> valores) {
        String[] diasConvertidos = new String[todosDias.size()];
        String[] valoresConvertidos = new String[todosDias.size()];

        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM");

        for (int i = 0; i < todosDias.size(); ++i) {
            diasConvertidos[i] = todosDias.get(i).format(formatacao);
            valoresConvertidos[i] = String.valueOf(valores.get(i));
        }

        return new GraficoDados(diasConvertidos, valoresConvertidos);
    }

    private void gerarDiasEValores(LocalDate dataFinal, LocalDate dataInicial, List<Float> valores, List<LocalDate> todosDias) {
        LocalDate data = dataInicial;
        while (!data.isAfter(dataFinal)) {
            valores.add(0f);
            todosDias.add(data);

            data = data.plusDays(1);
        }
    }

    private static GraficoDados gerarGraficoDados(List<String> todosMeses, List<Float> valoresMeses) {
        String[] mesesConvertidos = new String[todosMeses.size()];
        String[] valoresConvertidos = new String[todosMeses.size()];

        for (int i = 0; i < todosMeses.size(); ++i) {
            mesesConvertidos[i] = todosMeses.get(i);
            valoresConvertidos[i] = String.valueOf(valoresMeses.get(i));
        }

        return new GraficoDados(mesesConvertidos, valoresConvertidos);
    }
}