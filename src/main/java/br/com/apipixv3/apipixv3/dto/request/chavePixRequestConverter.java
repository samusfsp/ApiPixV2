package br.com.apipixv3.apipixv3.dto.request;


import br.com.apipixv3.apipixv3.domain.ChavePix;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class chavePixRequestConverter {

    public ChavePix chavePixRequestConverter(ChavePixRequest chavePixRequest) {
        ChavePix chavePix = new ChavePix();
        chavePix.setId(chavePixRequest.getId());
        chavePix.setTipo(chavePixRequest.getTipo());
        chavePix.setValor(chavePixRequest.getValor());
        chavePix.setCliente(chavePixRequest.getCliente());
        chavePix.setAtivo(chavePixRequest.isAtivo());

        return chavePix;
    }

    public ChavePixRequest cadastrar(ChavePix chavePix) {
        ChavePixRequest chavePixRequest = new ChavePixRequest();
        chavePixRequest.setDataEhora(String.valueOf(chavePix.getDataEhora()));
        chavePixRequest.setId(chavePix.getId());
        chavePixRequest.setMenssagem("Chave cadastrada com sucesso");

        return chavePixRequest;
    }




    public List<ChavePixRequest> listaRequestConverter
            (List<ChavePix> listaChavePix) {
        List<ChavePixRequest> listaRequest = new ArrayList<>();

        for (ChavePix chavePix : listaChavePix) {
            ChavePixRequest chavePixRequest = new ChavePixRequest();
            chavePixRequest.setId(chavePix.getId());
            chavePixRequest.setTipo(chavePix.getTipo());
            chavePixRequest.setValor(chavePix.getValor());
            chavePixRequest.setCliente(chavePix.getCliente());
            chavePixRequest.setAtivo(chavePix.isAtivo());

            listaRequest.add(chavePixRequest);
        }

        return listaRequest;
    }


    public ChavePixRequest convertToChavePixRequest(ChavePix chavePix) {
        ChavePixRequest chavePixRequest = new ChavePixRequest();
        chavePixRequest.setId(chavePix.getId());
        chavePixRequest.setTipo(chavePix.getTipo());
        chavePixRequest.setValor(chavePix.getValor());
        chavePixRequest.setCliente(chavePix.getCliente());
        chavePixRequest.setAtivo(chavePix.isAtivo());

        return chavePixRequest;
    }





}
