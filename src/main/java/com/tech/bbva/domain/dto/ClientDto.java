package com.tech.bbva.domain.dto;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ClientDto {

    private Long clientId;
    private String name;
    private String lastName;
    private String street;
    private Integer idAddress;
    private Integer pc;
    private String telephone;
    private String mobilPhone;

    @Nullable
    private String tClient;

    @Nullable
    private BankServiceDto service;

}
