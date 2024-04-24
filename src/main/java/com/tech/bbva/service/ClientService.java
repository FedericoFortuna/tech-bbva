package com.tech.bbva.service;

import com.tech.bbva.domain.dto.ClientDto;
import com.tech.bbva.domain.entity.ClientEntity;
import com.tech.bbva.service.repository.ClientRepository;
import com.tech.bbva.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    public void saveClient(ClientDto client){
        ClientEntity entity = Mapper.clientDtoToEntity(client);
        clientRepository.save(entity);
    }


    public void saveClients(List<ClientDto> clients){
        for (ClientDto dto : clients) {
            ClientEntity entity = Mapper.clientDtoToEntity(dto);
            clientRepository.save(entity);
        }
    }

    public List<ClientDto> getClients(){
        return clientRepository.findAll().stream().map(Mapper::clientEntityToDto).collect(Collectors.toList());
    }

    public ClientDto updateClientPhone(String id, String phone){

        Optional<ClientEntity> entityOptional = clientRepository.findById(Long.parseLong(id));

        if(!entityOptional.isPresent()){
            //TODO
        }
        ClientEntity entity = entityOptional.get();
        entity.setMobilPhone(phone);

        clientRepository.save(entity);

        return Mapper.clientEntityToDto(entity);

    }

    public List<ClientDto> getClientByServiceId(String id){
        Optional<List<ClientEntity>> optionalClientEntities = clientRepository.findByBankServiceId_BankServiceId(Long.parseLong(id));
        if(!optionalClientEntities.isPresent()){
            //TODO
        }
        List<ClientEntity> clientEntities = optionalClientEntities.get();
        return clientEntities.stream().map(Mapper::clientEntityToDto).collect(Collectors.toList());
    }
}
