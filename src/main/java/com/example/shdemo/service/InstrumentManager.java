package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Factory;
import com.example.shdemo.domain.Instrument;

public interface InstrumentManager {

    void addInstrument(Instrument inst);
    void addAllInstruments(List<Instrument> inst);
    void deleteInstrument(Instrument inst);
    void deleteAllInstruments(List<Instrument> inst);
    Instrument getInstrument(Long id);
    Instrument getInstrumentByName(String name);
    List<Instrument> getAllInstruments();
    void updateInstrument(Instrument inst);

    void addClient(Client client);
    void addAllClients(List<Client> clients);
    void deleteClient(Client client);
    void deleteAllClients(List<Client> clients);
    Client getClient(Long id);
    Client getClientByName(String name);
    List<Client> getAllClients();
    void updateClient(Client client);
    List<Instrument> getOwnedInstruments(Client client);

    void addFactory(Factory factory);
    void addAllFactories(List<Factory> factories);
    void deleteFactory(Factory factory);
    void deleteAllFactories(List<Factory> factories);
    Factory getFactory(Long id);
    Factory getFactoryByName(String name);
    List getAllFactories();
    void updateFactory(Factory factory);

}
