package org.example.apssemestre2.service;

import org.example.apssemestre2.model.ContaLuz;
import org.example.apssemestre2.repository.ContaLuzRepository;

import java.util.List;

public class ContaLuzService extends BaseService<ContaLuz> {
    ContaLuzRepository repository;

    public ContaLuzService() {
        repository = new ContaLuzRepository();
    }

    @Override
    public boolean cadastrar(ContaLuz contaLuz){
        return repository.cadastrar(contaLuz);
    }

    @Override
    public boolean atualizar(ContaLuz antigo, ContaLuz novo) {
        novo.setId(antigo.getId());

        if (repository.atualizar(novo)) {
            antigo.setBandeira(antigo.getBandeira());
            antigo.setConsumo(antigo.getConsumo());
            antigo.setValor(antigo.getValor());
            antigo.setReferencia(antigo.getReferencia());
            antigo.setVencimento(antigo.getVencimento());
            antigo.setAno(antigo.getAno());

            return true;
        }

        return false;
    }

    @Override
    public List<ContaLuz> listar(){
        var lista = repository.listar();

        return lista;
    }

    @Override
    public List<String> listarString(){
        var lista = repository.listarString();

        return lista;
    }

    @Override
    public boolean excluir(ContaLuz contaLuz) {
        return repository.excluir(contaLuz);
    }


    @Override
    public ContaLuz buscar() {
        return null;
    }

    @Override
    public boolean salvar(ContaLuz model) {
        return false;
    }
}
