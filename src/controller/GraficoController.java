package controller;

import repository.mock.GraficoRepository;

import java.util.List;

public class GraficoController {

    GraficoRepository repository;

    public GraficoController() {
        repository = new GraficoRepository();
    }

    public List<Integer> usoPorMes(int mes) {
        var lista = repository.buscarUsoGeral(mes);

        return lista;
    }
}
