package com.tech.bbva.utils;

import com.tech.bbva.domain.dto.BankServiceDto;
import com.tech.bbva.domain.dto.ClientDto;
import com.tech.bbva.domain.entity.BankServiceEntity;
import com.tech.bbva.domain.entity.ClientEntity;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class Mapper {


    public static ClientEntity clientDtoToEntity(ClientDto dto) {
        ClientEntity.ClientEntityBuilder builder = ClientEntity.builder()
                .clientId(dto.getClientId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .street(dto.getStreet())
                .idAddress(dto.getIdAddress())
                .pc(dto.getPc())
                .telephone(dto.getTelephone())
                .mobilPhone(dto.getMobilPhone())
                .tClient(dto.getTClient());

        if (Objects.nonNull(dto.getService())) {
            BankServiceEntity bankService = buildBankServiceEntityFromId(dto.getService().getId());
            builder.bankServiceId(bankService);
        }

        return builder.build();
    }


    private static BankServiceEntity buildBankServiceEntityFromId(Long id) {
        return BankServiceEntity.builder()
                .bankServiceId(id)
                .build();
    }

    private static BankServiceDto buildBankServiceDtoFromEntity(Long id, String name){
        return BankServiceDto.builder()
                .id(id)
                .name(name)
                .build();
    }


    public static ClientDto clientEntityToDto(ClientEntity entity){
        ClientDto.ClientDtoBuilder builder = ClientDto.builder()
                .clientId(entity.getClientId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .street(entity.getStreet())
                .idAddress(entity.getIdAddress())
                .pc(entity.getPc())
                .telephone(entity.getTelephone())
                .mobilPhone(entity.getMobilPhone())
                .tClient(Objects.nonNull(entity.getTClient()) ? entity.getTClient() : Strings.EMPTY);

        if (Objects.nonNull(entity.getBankServiceId())) {
            BankServiceDto bankService = buildBankServiceDtoFromEntity(entity.getBankServiceId().getBankServiceId(), entity.getBankServiceId().getName());
            builder.service(bankService);
        }

        return builder.build();

    }

}
