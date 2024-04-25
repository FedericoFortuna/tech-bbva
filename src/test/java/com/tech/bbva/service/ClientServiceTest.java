package com.tech.bbva.service;

import com.tech.bbva.domain.TBankService;
import com.tech.bbva.domain.dto.BankServiceDto;
import com.tech.bbva.domain.dto.ClientDto;
import com.tech.bbva.domain.entity.BankServiceEntity;
import com.tech.bbva.domain.entity.ClientEntity;
import com.tech.bbva.exception.ClientNotFoundException;
import com.tech.bbva.service.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BankService bankService;

    private ClientDto clientDto;

    private ClientDto clientDtoWithoutService;
    private ClientEntity clientEntity;

    private ClientEntity clientWithoutService;

    private final List<ClientEntity> clients = new ArrayList<>();

    private final List<ClientEntity> clientesForServiceId = new ArrayList<>();

    @BeforeEach
    void setUp() {

        BankServiceEntity bankServiceEntity = BankServiceEntity.builder()
                .bankServiceId(1L)
                .name(TBankService.TECNOLOGIA.toString())
                .sector("TECNOLOGIA")
                .qServed(50)
                .exAtt(false)
                .exTechSupport(true)
                .clients(new ArrayList<>())
                .build();

        BankServiceEntity bankServiceEntity_RRHH = BankServiceEntity.builder()
                .bankServiceId(1L)
                .name(TBankService.RRHH.toString())
                .sector("Recursos Humanos")
                .qServed(10)
                .exAtt(false)
                .exTechSupport(true)
                .clients(new ArrayList<>())
                .build();

        clientDto = ClientDto.builder()
                .clientId(1L)
                .name("name")
                .lastName("lastName")
                .street("street")
                .idAddress(1)
                .pc(10)
                .telephone("tele")
                .mobilPhone("mobil")
                .service(BankServiceDto.builder().id(1L).build())
                .build();


        clientDtoWithoutService = ClientDto.builder()
                .clientId(1L)
                .name("name")
                .lastName("lastName")
                .street("street")
                .idAddress(1)
                .pc(10)
                .telephone("tele")
                .mobilPhone("mobil")
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
                .clientId(2L)
                .name("name")
                .lastName("lastName")
                .street("street")
                .idAddress(123)
                .pc(10)
                .telephone("telephone")
                .mobilPhone("mobilPhone")
                .tClient("tClient")
                .build();

        ClientEntity clientId_2 = ClientEntity.builder()
                .clientId(3L)
                .name("name")
                .lastName("lastName")
                .street("street")
                .idAddress(123)
                .pc(10)
                .telephone("telephone")
                .mobilPhone("mobilPhone")
                .tClient("tClient")
                .bankServiceId(bankServiceEntity_RRHH)
                .build();

        clients.add(clientEntity);
        clients.add(clientWithoutService);
        clientesForServiceId.add(clientId_2);

    }

    @Test
    void testSaveClientWithServiceId() {

        when(clientRepository.save(any())).thenReturn(clientEntity);
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientEntity));
        doNothing().when(bankService).updateBankService(anyLong(), any(ClientEntity.class));
        doNothing().when(bankService).checkBankServiceId(anyLong());

        service.saveClient(clientDto);

        verify(clientRepository, times(1)).save(any());
        verify(clientRepository, times(1)).findById(anyLong());
        verify(bankService, times(1)).updateBankService(anyLong(), any(ClientEntity.class));
    }

    @Test
    void testSaveClientWithoutServiceId() {

        when(clientRepository.save(any())).thenReturn(clientWithoutService);
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientWithoutService));
        doNothing().when(bankService).updateBankService(anyLong(), any(ClientEntity.class));

        service.saveClient(clientDtoWithoutService);

        verify(clientRepository, times(1)).save(any());
        verify(clientRepository, times(1)).findById(anyLong());
        verify(bankService, times(1)).updateBankService(anyLong(), any(ClientEntity.class));
    }


    @Test
    void testGetClients() {

        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> clients = service.getClients();

        assertFalse(clients.isEmpty());
    }

    @Test
    void testUpdateClientPhone() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(clientEntity));
        when(clientRepository.save(any())).thenReturn(clientEntity);

        String id = "1";
        String phone = "123456789";
        ClientDto updatedClient = service.updateClientPhone(id, phone);


        assertEquals(phone, updatedClient.getMobilPhone());
    }

    @Test
    void updateClientThatNotExists_ShouldThrowException() {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        String id = "1";
        String phone = "123456789";

        Assertions.assertThrows(ClientNotFoundException.class, () -> service.updateClientPhone(id, phone));
    }


    @Test
    void testGetClientByServiceId() {
        String id = "1";
        when(clientRepository.findByBankServiceId_BankServiceId(anyLong())).thenReturn(Optional.of(clientesForServiceId));

        List<ClientDto> clients = service.getClientByServiceId(id);

        assertFalse(clients.isEmpty());
        assertEquals(1, clients.size());
        for (ClientDto dto:clients) {
            assertEquals(Long.parseLong(id), dto.getService().getId());
        }
    }

}
