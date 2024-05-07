package org.example.apssemestre2.service;

import org.example.apssemestre2.model.Aparelho;
import org.example.apssemestre2.repository.AparelhoExemploRepository;

import java.util.List;

public class AparelhoService {
    AparelhoExemploRepository repository;

    public AparelhoService() {
        repository = new AparelhoExemploRepository();
    }

    public List<Aparelho> listar() {
        var lista = repository.listar();

        return lista;
    }
}
