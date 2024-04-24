package com.tech.bbva.service;

import com.tech.bbva.domain.TBankService;
import com.tech.bbva.domain.entity.BankServiceEntity;
import com.tech.bbva.domain.entity.ClientEntity;
import com.tech.bbva.exception.BankServiceNotFoundException;
import com.tech.bbva.service.repository.BankServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    @Mock
    private BankServiceRepository bankServiceRepository;

    @InjectMocks
    private BankService bankService;

    private ClientEntity clientEntity;

    private ClientEntity clientWithoutService;

    private BankServiceEntity bankServiceEntity;

    private BankServiceEntity bankServiceEntityPrevious;

    @BeforeEach
    void setUp() {
        bankServiceEntity = BankServiceEntity.builder()
                .bankServiceId(1L)
                .name(TBankService.TECNOLOGIA.toString())
                .sector("TECNOLOGIA")
                .qServed(50)
                .exAtt(false)
                .exTechSupport(true)
                .clients(new ArrayList<>())
                .build();

        bankServiceEntityPrevious = BankServiceEntity.builder()
                .bankServiceId(2L)
                .name(TBankService.PLAZO_FIJO.toString())
                .sector("BANCARIO")
                .qServed(100)
                .exAtt(false)
                .exTechSupport(true)
                .clients(new ArrayList<>())
                .build();


        clientEntity = ClientEntity.builder()
                .clientId(1L)
                .name("name")
                .lastName("lastName")
                .street("street")
                .idAddress(123)
                .pc(10)
                .telephone("telephone")
                .mobilPhone("mobilPhone")
                .tClient("tClient")
                .bankServiceId(bankServiceEntity)
                .build();

        clientWithoutService = ClientEntity.builder()
                .clientId(1L)
                .name("name")
                .lastName("lastName")
                .street("street")
                .idAddress(123)
                .pc(10)
                .telephone("telephone")
                .mobilPhone("mobilPhone")
                .tClient("tClient")
                .build();


    }

    @Test
    void testUpdateBankServiceWithPreviousServiceId() {
        Long previousServiceBankId = 2L;

        when(bankServiceRepository.findById(anyLong())).thenReturn(Optional.of(bankServiceEntity));
        when(bankServiceRepository.save(any())).thenReturn(bankServiceEntityPrevious);

        bankService.updateBankService(previousServiceBankId, clientEntity);

        verify(bankServiceRepository, times(2)).save(any());
    }

    @Test
    void testUpdateBankServiceWithoutPreviousServiceId() {
        Long previousServiceBankId = -1L;

        when(bankServiceRepository.findById(anyLong())).thenReturn(Optional.of(bankServiceEntity));
        when(bankServiceRepository.save(any())).thenReturn(bankServiceEntityPrevious);

        bankService.updateBankService(previousServiceBankId, clientEntity);

        verify(bankServiceRepository, times(1)).save(any());
    }

    @Test
    void testUpdateBankServiceWithoutServiceId() {
        Long previousServiceBankId = -1L;

        bankService.updateBankService(previousServiceBankId, clientWithoutService);

        verify(bankServiceRepository, times(0)).save(any());
    }

    @Test
    void bankServiceIdNonExistentShoudThrowException(){
        when(bankServiceRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(BankServiceNotFoundException.class, () -> bankService.checkBankServiceId(10L));
    }

}
