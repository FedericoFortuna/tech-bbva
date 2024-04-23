package com.tech.bbva.controller;

import com.tech.bbva.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }




}
