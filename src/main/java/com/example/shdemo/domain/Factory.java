package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "factory.all", query = "Select f from Factory f"),
        @NamedQuery(name = "factory.byName", query = "Select f from Factory f where f.name = :name")
})

public class Factory
{
    private Long id;
    private String name;
    private String country;

    public Factory() { }

    public Factory(String name, String country){
        this.name = name;
        this.country = country;
    }
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

    @Column(unique = true)
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
