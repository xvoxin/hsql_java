package com.example.shdemo.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "client.all", query = "Select c from Client c"),
        @NamedQuery(name = "client.byName", query = "Select c from Client c where c.name = :name")
})
public class Client {

    private Long id;
    private String name = "unknown";
    private String surname = "unknown";
    private Date registrationDate = new Date();
    private List<Instrument> instruments = new ArrayList<Instrument>();

    public Client(String name, String surname, Date date, List<Instrument> instruments){
        this.name = name;
        this.surname = surname;
        this.registrationDate = date;
        this.instruments = instruments;
    }

    public Client(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Client(){ }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }
}
