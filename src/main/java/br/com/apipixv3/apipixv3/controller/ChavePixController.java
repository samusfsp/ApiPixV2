package br.com.apipixv3.apipixv3.controller;


import br.com.apipixv3.apipixv3.domain.ChavePix;
import br.com.apipixv3.apipixv3.dto.request.ChavePixRequest;
import br.com.apipixv3.apipixv3.dto.request.chavePixRequestConverter;
import br.com.apipixv3.apipixv3.dto.response.ChavePixDto;
import br.com.apipixv3.apipixv3.mapper.ChavePixsMapper;
import br.com.apipixv3.apipixv3.service.ChavePixService;
import br.com.apipixv3.apipixv3.service.ChaveServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ChavePixController {

    @Autowired
    private ChavePixService service;
    @Autowired
    private chavePixRequestConverter converter;
@Autowired
private ChavePixsMapper mapper;




    @PostMapping("/chave")
    public ResponseEntity<ChavePixRequest> cadastrarChave(@RequestBody ChavePix chavePix) {
        ChavePix novaChavePix = service.cadastrar(chavePix);
        ChavePixRequest chavePixDto = converter.cadastrar(novaChavePix);
        return ResponseEntity.ok(chavePixDto);
    }




    @GetMapping("/chave")
    public ResponseEntity<List<ChavePixDto>> recuperarTodas() {
        List<ChavePix> list = service.recuperarTodos();
        List<ChavePixDto> listDto = mapper.recuperarTodos(list);


        return ResponseEntity.ok(listDto);
    }


//    @GetMapping("/chave")
//    public ResponseEntity<List<ChavePixDto>> recuperarTodas() {
//        List<ChavePixRequest> chavesPix = service.recuperarTodos();
//
//        RequestMapper requestMapper = new RequestMapper();
//        List<ChavePixDto> chavePixDtos = requestMapper.recuperarTodos(chavesPix);
//
//        return ResponseEntity.ok(chavePixDtos);
//    }


    //REFACT03
    //    @GetMapping("/chave")
//    public ResponseEntity<List<ChavePixDto>> recuperarTodas() {
//        List<ChavePix> chavesPix = service.recuperarTodos();
//        List<ChavePixDto> chavePixDtos = new ArrayList<>();//
//        for (ChavePix chavePix : chavesPix) {
//            ChavePixDto chavePixDto = new ChavePixDto();
//            chavePixDto.setId(chavePix.getId());
//            chavePixDto.setTipo(chavePix.getTipo());
//            chavePixDto.setValor(chavePix.getValor());
//            chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
//            chavePixDto.setCliente(chavePix.getCliente());//
//            chavePixDtos.add(chavePixDto);
//        }
//        return ResponseEntity.ok(chavePixDtos);
//    }


    //REFACT 2
//    @GetMapping("/chave")
//    public ResponseEntity<List<OutputChavePixDto>> recuperarTodas() {
//        List<ChavePix> chavesPix = service.recuperarTodos();
//        List<OutputChavePixDto> chavePixDtos = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        for (ChavePix chavePix : chavesPix) {
//            if (chavePix.isAtivo()) { // Verifica se a chave está ativa
//                OutputChavePixDto output = modelMapper.map(chavePix, OutputChavePixDto.class);
//                chavePixDtos.add(output);
//            }
//        }
//        return ResponseEntity.ok(chavePixDtos);
//    }


    // Refact 01
//    @GetMapping("/chave")
//    public ResponseEntity<List<ChavePixDto>> recuperarTodas() {
//        List<ChavePix> chavesPix = service.recuperarTodos();
//        List<ChavePixDto> chavePixDtos = new ArrayList<>();
//
//        for (ChavePix chavePix : chavesPix) {
//            if (chavePix.isAtivo()) { // Verifica se a chave está ativa
//                ChavePixDto chavePixDto = new ChavePixDto();
//                chavePixDto.setId(chavePix.getId());
//                chavePixDto.setTipo(chavePix.getTipo());
//                chavePixDto.setValor(chavePix.getValor());
//                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
//                chavePixDto.setCliente(chavePix.getCliente());
//
//                chavePixDtos.add(chavePixDto);
//            }
//        }
//
//        return ResponseEntity.ok(chavePixDtos);
//    }

//    @GetMapping("/chave/{id}")
//    public ResponseEntity<String> recuperarPorId(@PathVariable UUID id) {
//        String idString = id.toString();
//        Optional<ChavePix> chavePixOptional = service.recuperarChavePorId(id);
//        if (chavePixOptional.isPresent()) {
//            ChavePix chavePix = chavePixOptional.get();
//            if (chavePix.isAtivo()) {
//                ChavePixDto chavePixDto = new ChavePixDto();
//                chavePixDto.setId(UUID.fromString(idString)); // Definindo o ID como String
//                chavePixDto.setTipo(chavePix.getTipo());
//                chavePixDto.setValor(chavePix.getValor());
//                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
//                chavePixDto.setCliente(chavePix.getCliente());
//
//                return  ResponseEntity.ok(chavePixDto);
//            }
//        }
//
//        throw new IllegalArgumentException("Chave não encontrada.");
//    }


    @GetMapping("/chave/{id}")
    public ResponseEntity<ChavePixDto> recuperarPorId(@PathVariable UUID id) {
        Optional<ChavePix> chavePixOptional = service.recuperarChavePorId(id);
        if (chavePixOptional.isPresent()) {
            ChavePix chavePix = chavePixOptional.get();
            if (chavePix.isAtivo()) {
                ChavePixsMapper chavePixMapper = new ChavePixsMapper();
                ChavePixDto chavePixDto = chavePixMapper.recuperarPorId(chavePix);

                return ResponseEntity.ok(chavePixDto);
            }
        }
        return null;
    }

    //REFACT00
//    @GetMapping("/chave/{id}")
//    public ResponseEntity<OutputChavePixDto> recuperarPorId(@PathVariable UUID id) {
//        String idString = id.toString();
//        Optional<ChavePix> chavePixOptional = service.recuperarChavePorId(id);
//        if (chavePixOptional.isPresent()) {
//            ChavePix chavePix = chavePixOptional.get();
//            if (chavePix.isAtivo()) {
//                OutputChavePixDto chavePixDto = new OutputChavePixDto();
//                chavePixDto.setId(UUID.fromString(idString)); // Definindo o ID como String
//                chavePixDto.setTipo(chavePix.getTipo());
//                chavePixDto.setValor(chavePix.getValor());
//                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
//                chavePixDto.setCliente(chavePix.getCliente());
//
//                return ResponseEntity.ok(chavePixDto);
//            }
//        }
//        throw new IllegalArgumentException("Chave não encontrada.");
//
//    }

    @GetMapping("/chave/cliente/{idCliente}")
    public ResponseEntity<List<ChavePixDto>> recuperarChavesPorIdCliente(@PathVariable Long idCliente) {
        List<ChavePix> chavesPix = service.recuperarChavesPorIdCliente(idCliente);

        ChavePixsMapper chavePixMapper = new ChavePixsMapper();
        List<ChavePixDto> chavePixDtos = chavePixMapper.recuperarPorIdCliente(chavesPix);

        return ResponseEntity.ok(chavePixDtos);
    }


    //REFACT00
//    @GetMapping("/chave/cliente/{idCliente}")
//    public ResponseEntity<List<OutputChavePixDto>> recuperarChavesPorIdCliente(@PathVariable Long idCliente) {
//        List<ChavePix> chavesPix = service.recuperarChavesPorIdCliente(idCliente);
//        List<OutputChavePixDto> chavePixDtos = new ArrayList<>();
//
//        for (ChavePix chavePix : chavesPix) { // Renomeada a variável para "chave"
//            if (chavePix.isAtivo()) {
//                OutputChavePixDto chavePixDto = new OutputChavePixDto();
//                chavePixDto.setId(chavePix.getId());
//                chavePixDto.setTipo(chavePix.getTipo());
//                chavePixDto.setValor(chavePix.getValor());
//                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
//                chavePixDto.setCliente(chavePix.getCliente());
//
//                chavePixDtos.add(chavePixDto);
//            }
//        }
//
//        return ResponseEntity.ok(chavePixDtos);
//    }
    @PutMapping("/chave/{id}")
    public ResponseEntity<?> atualizarChave(@PathVariable UUID id, @RequestBody ChavePix chavePix) throws IllegalArgumentException {
        Optional<ChavePix> chavePixOptional = service.recuperarChavePorId(id);
        if (chavePixOptional.isPresent()) {
            ChavePix chaveExistente = chavePixOptional.get();
            // Atualiza os campos necessários da chave existente
            chaveExistente.setTipo(chavePix.getTipo());
            chaveExistente.setValor(chavePix.getValor());
            // Define o cliente da chave existente, se necessário
            chaveExistente.setCliente(chavePix.getCliente());

            ChavePix chaveAtualizada = service.atualizar(chaveExistente);
            return ResponseEntity.ok(chaveAtualizada);
        } else {
            return null;
        }
    }

//    @PutMapping("/chave/{id}")
//    public ResponseEntity<?> atualizarChave(@PathVariable UUID id, @RequestBody ChavePix chavePix) {
//        Optional<ChavePix> chavePixOptional = service.recuperarChavePorId(id);
//        if (chavePixOptional.isPresent()) {
//            ChavePix chaveExistente = chavePixOptional.get();
//            // Atualiza os campos necessários da chave existente
//            chaveExistente.setTipo(chavePix.getTipo());
//            chaveExistente.setValor(chavePix.getValor());
//            // Define o cliente da chave existente, se necessário
//            chaveExistente.setCliente(chavePix.getCliente());
//
//            try {
//                ChavePix chaveAtualizada = service.atualizar(chaveExistente);
//                return ResponseEntity.ok(chaveAtualizada);
//            } catch (IllegalArgumentException e) {
//                return ResponseEntity.badRequest().body(e.getMessage());
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/chave/data/{data}")
    public ResponseEntity<List<ChavePixDto>> recuperarPorData(@PathVariable String data) {
        LocalDate dataEhora = LocalDate.parse(data);
        List<ChavePix> chavesPix = service.recuperarPorData(dataEhora);

        ChavePixsMapper chavePixMapper = new ChavePixsMapper();
        List<ChavePixDto> chavePixDtos = chavePixMapper.recuperarPorData(chavesPix);

        return ResponseEntity.ok(chavePixDtos);
    }

    //REFACT00
//    @GetMapping("/chave/data/{data}")
//    public ResponseEntity<List<OutputChavePixDto>> recuperarPorData(@PathVariable String data) {
//        LocalDate dataEhora = LocalDate.parse(data);
//        List<ChavePix> chavesPix = service.recuperarPorData(dataEhora);
//        List<OutputChavePixDto> chavePixDtos = new ArrayList<>();
//
//
//        for (ChavePix chavePix : chavesPix) {
//            if (chavePix.isAtivo()) {
//                OutputChavePixDto chavePixDto = new OutputChavePixDto();
//                chavePixDto.setId(chavePix.getId());
//                chavePixDto.setTipo(chavePix.getTipo());
//                chavePixDto.setValor(chavePix.getValor());
//                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
//                chavePixDto.setCliente(chavePix.getCliente());
//
//                chavePixDtos.add(chavePixDto);
//            }
//        }
//
//        return ResponseEntity.ok(chavePixDtos);
//    }

    @DeleteMapping("/chave/{id}")
    public ResponseEntity<?> desativarChave(@PathVariable UUID id) {
        Optional<ChavePix> chavePixOptional = service.recuperarChavePorId(id);
        if (chavePixOptional.isPresent()) {
            ChavePix chavePix = chavePixOptional.get();
            // Define o status da chave como desativado
            ChaveServiceImplement ativo = new ChaveServiceImplement();
            ativo.desativarChave(chavePix);

            // Atualiza a chave no serviço
            ChavePix chaveDesativada = service.atualizar(chavePix);

            return ResponseEntity.ok(chaveDesativada);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/chave/nome/{nomeCliente}")
    public ResponseEntity<List<ChavePixDto>> recuperarChavesPorNomeCliente(@PathVariable String nomeCliente) {
        List<ChavePix> chavesPix = service.recuperarPornome(nomeCliente);
        List<ChavePixDto> chavePixDtos = new ArrayList<>();

        for (ChavePix chavePix : chavesPix) {
            if (chavePix.isAtivo()) {
                ChavePixDto chavePixDto = new ChavePixDto();
                chavePixDto.setId(chavePix.getId());
                chavePixDto.setTipo(chavePix.getTipo());
                chavePixDto.setValor(chavePix.getValor());
                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
                chavePixDto.setCliente(chavePix.getCliente());

                chavePixDtos.add(chavePixDto);
            }
        }

        return ResponseEntity.ok(chavePixDtos);
    }

    @GetMapping("/chave/conta/{conta}")
    public ResponseEntity<List<ChavePixDto>> recuperarChavesPorConta(@PathVariable String conta) {
        List<ChavePix> chavesPix = service.recupeConta(conta);
        List<ChavePixDto> chavePixDtos = new ArrayList<>();

        for (ChavePix chavePix : chavesPix) {
            if (chavePix.isAtivo()) {
                ChavePixDto chavePixDto = new ChavePixDto();
                chavePixDto.setId(chavePix.getId());
                chavePixDto.setTipo(chavePix.getTipo());
                chavePixDto.setValor(chavePix.getValor());
                chavePixDto.setDataEhora(String.valueOf(chavePix.getDataEhora()));
                chavePixDto.setCliente(chavePix.getCliente());

                chavePixDtos.add(chavePixDto);
            }
        }

        return ResponseEntity.ok(chavePixDtos);
    }


}
