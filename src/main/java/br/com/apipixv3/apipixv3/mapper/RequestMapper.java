package br.com.apipixv3.apipixv3.mapper;

import br.com.apipixv3.apipixv3.domain.ChavePix;
import br.com.apipixv3.apipixv3.dto.request.ChavePixRequest;
import br.com.apipixv3.apipixv3.dto.response.ChavePixDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestMapper {

    private ModelMapper modelMapper;

    public RequestMapper() {
        this.modelMapper = new ModelMapper();
    }
    public List<ChavePixDto> recuperarTodos
            (List<ChavePix> chavePix) {
        List<ChavePixDto> chavePixDtos = new ArrayList<>();
        for (ChavePix ChavePix : chavePix) {
            if (ChavePix.isAtivo()) {
                ChavePixDto chavePixDto = modelMapper.map(ChavePix, ChavePixDto.class);
                chavePixDtos.add(chavePixDto);
            }
        }
        return chavePixDtos;
    }


}
