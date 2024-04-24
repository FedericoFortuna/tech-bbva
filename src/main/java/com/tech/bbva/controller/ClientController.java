package com.tech.bbva.controller;

import com.tech.bbva.domain.dto.ClientDto;
import com.tech.bbva.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody ClientDto client) {
        service.saveClient(client);
        return new ResponseEntity<>("Client registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/register/list")
    public ResponseEntity<String> registerClients(@RequestBody List<ClientDto> clients) {
        service.saveClients(clients);
        return new ResponseEntity<>("Clients registered successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients(){
        List<ClientDto> list = service.getClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientDto> updatePhone(@PathVariable String clientId, @RequestBody String phone){
        ClientDto dto = service.updateClientPhone(clientId, phone);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{bankId}")
    public ResponseEntity<List<ClientDto>> getClientsByServiceBankId(@PathVariable String bankId){
        List<ClientDto> list = service.getClientByServiceId(bankId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    /*
    TODO
    * Hacer test unitarios
    * Agregar manejo de excepciones
     */



}
