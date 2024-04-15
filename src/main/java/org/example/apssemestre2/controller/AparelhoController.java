package org.example.apssemestre2.controller;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.repository.AparelhoRepository;

import java.util.List;

public class AparelhoController {
    AparelhoRepository repository;

    public AparelhoController() {
        repository = new AparelhoRepository();
    }

    public List<Aparelho> listar() {
        var lista = repository.listar();

        return lista;
    }
}
