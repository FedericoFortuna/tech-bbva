package com.tech.bbva.service;

import com.tech.bbva.domain.dto.ClientDto;
import com.tech.bbva.domain.entity.ClientEntity;
import com.tech.bbva.service.repository.ClientRepository;
import com.tech.bbva.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tech.bbva.domain.MicroserviceConstants.PREVIOUS_ID_UNAVAILABLE;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankService bankService;

    public void saveClient(ClientDto client){
        ClientEntity entity = Mapper.clientDtoToEntity(client);
        Long previousServiceBankId = getPreviousServiceBankId(entity);
        clientRepository.save(entity);
        updateBankService(entity, previousServiceBankId);
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


    private void updateBankService(ClientEntity entity, Long previousServiceBankId){
        bankService.updateBankService(previousServiceBankId, entity);
    }

    private Long getPreviousServiceBankId(ClientEntity entity){
        Optional<ClientEntity> client = clientRepository.findById(entity.getClientId());
        Long previousServiceBankId = PREVIOUS_ID_UNAVAILABLE;
        if(client.isPresent()){
            if(Objects.nonNull(client.get().getBankServiceId())){
                previousServiceBankId = client.get().getBankServiceId().getBankServiceId();
            }
        }

        return previousServiceBankId;
    }
}
