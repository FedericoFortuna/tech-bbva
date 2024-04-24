package com.tech.bbva.service;

import com.tech.bbva.domain.entity.BankServiceEntity;
import com.tech.bbva.domain.entity.ClientEntity;
import com.tech.bbva.service.repository.BankServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private BankServiceRepository bankServiceRepository;


    public void updateBankService(Long previousServiceBankId, ClientEntity client) {
        if(previousServiceBankId != -1L){
            subtractQServed(previousServiceBankId);
        }

        if(Objects.nonNull(client.getBankServiceId())){
            addQServed(client);
        }
    }


    private BankServiceEntity subtractQServed(Long previousServiceBankId){
        Optional<BankServiceEntity> entity = bankServiceRepository.findById(previousServiceBankId);
        entity.get().setQServed(entity.get().getQServed() - 1);
        return bankServiceRepository.save(entity.get());
    }

    private BankServiceEntity addQServed(ClientEntity client){
        Optional<BankServiceEntity> entityToUpdate = bankServiceRepository.findById(client.getBankServiceId().getBankServiceId());
        entityToUpdate.get().setQServed(entityToUpdate.get().getQServed() + 1);
        return bankServiceRepository.save(entityToUpdate.get());
    }

}
