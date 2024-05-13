package org.example.apssemestre2.service;

import javafx.scene.image.Image;
import org.example.apssemestre2.repository.MigracaoDBRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class MigracaoDBService {
    private static MigracaoDBService instancia;
    private final MigracaoDBRepository repository;

    private MigracaoDBService() {
        repository = MigracaoDBRepository.getInstancia();
    }

    public static MigracaoDBService getInstancia() {
        if (instancia == null) {
            instancia = new MigracaoDBService();
        }

        return instancia;
    }

    public void migrar() throws IOException {
        String caminhoMigracao = getClass().getResource("/org/example/apssemestre2/migration").getPath();
        File pastaMigracao = new File(caminhoMigracao);

        if (pastaMigracao.isDirectory()) {
            File[] arquivos = pastaMigracao.listFiles();

            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    List<String> linhas = Files.readAllLines(arquivo.toPath(), StandardCharsets.UTF_8);

                    StringBuilder sb = new StringBuilder();
                    for (String linha : linhas) {
                        sb.append(linha).append("\n");
                    }
                    repository.migrar(sb.toString());
                }
            }
        } else {
            System.err.println("Caminho de migração od banco de dados não foi encontrado.");
        }
    }
}
