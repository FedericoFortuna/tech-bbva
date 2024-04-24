package com.tech.bbva.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class BankServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankServiceId;
    private String name;
    private String sector;
    private Integer qServed;
    private Boolean exAtt;
    private Boolean exTechSupport;

    @OneToMany(mappedBy = "bankServiceId")
    private List<ClientEntity> clients;

}
