package br.com.apipixv3.apipixv3.mapper;

import br.com.apipixv3.apipixv3.dto.request.ChavePixRequest;
import br.com.apipixv3.apipixv3.dto.response.ChavePixDto;
import br.com.apipixv3.apipixv3.domain.ChavePix;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChavePixsMapper {
    private ModelMapper modelMapper;

    public ChavePixsMapper() {
        this.modelMapper = new ModelMapper();
    }

    public ChavePixDto cadastrar(ChavePix chavePix) {

        ChavePixDto chavePixDto = modelMapper.map(chavePix, ChavePixDto.class);
       chavePixDto.setId(chavePix.getId());
        return chavePixDto;


    }

    public List<ChavePixDto> recuperarTodos(List<ChavePix> chavesPix) {
        List<ChavePixDto> chavePixDtos = new ArrayList<>();
        for (ChavePix chavePix : chavesPix) {
            if (chavePix.isAtivo()) {
                ChavePixDto chavePixDto = modelMapper.map(chavePix, ChavePixDto.class);
                chavePixDtos.add(chavePixDto);
            }
        }
        return chavePixDtos;
    }

    public ChavePixDto recuperarPorId(ChavePix chavePix) {
        ChavePixDto output = modelMapper.map(chavePix, ChavePixDto.class);
        return output;
    }

    public ChavePixDto cadastrarChave(ChavePixRequest chavePixRequest) {
        ChavePixDto output = modelMapper.map(chavePixRequest, ChavePixDto.class);
        return output;

    }

    public List<ChavePixDto> recuperarPorIdCliente(List<ChavePix> chavesPix) {
        List<ChavePixDto> chavePixDtos = new ArrayList<>();
        for (ChavePix chavePix : chavesPix) {
            if (chavePix.isAtivo()) {
                ChavePixDto output = modelMapper.map(chavePix, ChavePixDto.class);
                chavePixDtos.add(output);
            }
        }
        return chavePixDtos;
    }

    public List<ChavePixDto> recuperarPorData(List<ChavePix> chavesPix) {
        List<ChavePixDto> chavePixDtos = new ArrayList<>();
        for (ChavePix chavePix : chavesPix) {
            if (chavePix.isAtivo()) {
                ChavePixDto output = modelMapper.map(chavePix, ChavePixDto.class);
                chavePixDtos.add(output);
            }
        }
        return chavePixDtos;
    }

}



