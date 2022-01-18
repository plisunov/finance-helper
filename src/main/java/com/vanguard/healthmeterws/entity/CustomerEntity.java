package com.vanguard.healthmeterws.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    public Long clientId;

    @Column(name = "market_performancce")
    public Double marketPerformance;

    @Column(name = "email")
    public String email;

    @Column(name = "phone")
    public String phone;

    @Column(name = "start_date")
    public LocalDate startDate;

    @Column(name = "name")
    public String name;

    @Column(name = "has_advisor_assigned")
    public Boolean hasAdvisorAssigned;

    @Column(name = "date_birth")
    public LocalDate dateBirth;

}
