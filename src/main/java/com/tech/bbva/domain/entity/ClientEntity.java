package com.tech.bbva.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String name;
    private String lastName;
    private String street;
    private Integer idAddress;
    private Integer pc;
    private String telephone;
    private String mobilPhone;
    private String tClient;

    @ManyToOne
    @JoinColumn(name = "bank_service_id")
    private BankServiceEntity bankService;

}
