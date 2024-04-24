package com.tech.bbva.utils;

import com.tech.bbva.domain.TBankService;
import com.tech.bbva.domain.dto.BankServiceDto;
import com.tech.bbva.domain.dto.ClientDto;
import com.tech.bbva.domain.entity.BankServiceEntity;
import com.tech.bbva.domain.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tech.bbva.domain.TBankService.PRESTAMO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTest {

        @Test
        void testClientDtoToEntity_WithService() {

            BankServiceDto bankServiceDto = BankServiceDto.builder().id(1L).name(PRESTAMO.toString()).build();
            ClientDto clientDto = ClientDto.builder().clientId(1L).name("Name").service(bankServiceDto).build();


            ClientEntity clientEntity = Mapper.clientDtoToEntity(clientDto);

            assertEquals(clientDto.getClientId(), clientEntity.getClientId());
            assertEquals(clientDto.getName(), clientEntity.getName());
            assertEquals(clientDto.getService().getId(), clientEntity.getBankServiceId().getBankServiceId());
        }

        @Test
        void testClientDtoToEntity_WithoutService() {

            ClientDto clientDto = ClientDto.builder().clientId(1L).name("Name").build();


            ClientEntity clientEntity = Mapper.clientDtoToEntity(clientDto);


            assertEquals(clientDto.getClientId(), clientEntity.getClientId());
            assertEquals(clientDto.getName(), clientEntity.getName());

        }

        @Test
        void testClientEntityToDto_WithService() {

            BankServiceEntity bankServiceEntity = BankServiceEntity.builder().bankServiceId(1L).name(PRESTAMO.toString()).build();
            ClientEntity clientEntity = ClientEntity.builder().clientId(1L).name("Name").bankServiceId(bankServiceEntity).build();


            ClientDto clientDto = Mapper.clientEntityToDto(clientEntity);


            assertEquals(clientEntity.getClientId(), clientDto.getClientId());
            assertEquals(clientEntity.getName(), clientDto.getName());
            assertEquals(clientEntity.getBankServiceId().getBankServiceId(), clientDto.getService().getId());
        }

        @Test
        void testClientEntityToDto_WithoutService() {

            ClientEntity clientEntity = ClientEntity.builder().clientId(1L).name("Name").build();


            ClientDto clientDto = Mapper.clientEntityToDto(clientEntity);


            assertEquals(clientEntity.getClientId(), clientDto.getClientId());
            assertEquals(clientEntity.getName(), clientDto.getName());
        }

}
