package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.repository.AparelhoRepository;

import java.util.List;

public class AparelhoService {
    AparelhoRepository repository;

    public AparelhoService() {
        repository = new AparelhoRepository();
    }

    public List<Aparelho> listar() {
        var lista = repository.listar();

        return lista;
    }
}
