package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Factory;
import com.example.shdemo.domain.Instrument;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Component
@Transactional
public class InstrumentManagerHibernateImpl implements InstrumentManager{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addInstrument(Instrument inst) {
        inst.setId(null);
        sessionFactory.getCurrentSession().persist(inst);
    }

    @Override
    public void addAllInstruments(List<Instrument> inst) {
        for(Instrument instrument : inst){
            instrument.setId(null);
            sessionFactory.getCurrentSession().persist(instrument);
        }
    }

    @Override
    public void deleteInstrument(Instrument inst) {
        sessionFactory.getCurrentSession().delete(inst);
    }

    @Override
    public void deleteAllInstruments(List<Instrument> inst) {
        for(Instrument instrument : inst){
            sessionFactory.getCurrentSession().delete(instrument);
        }
    }

    @Override
    public Instrument getInstrument(Long id) {
        Instrument instrument = (Instrument)
        sessionFactory.getCurrentSession().get(Instrument.class, id);

        return instrument;
    }

    @Override
    public Instrument getInstrumentByName(String name) {
        Instrument instrument = (Instrument)
                sessionFactory.getCurrentSession().getNamedQuery("instrument.byName")
                        .setString("name", name).uniqueResult();

        return instrument;
    }

    @Override
    public List<Instrument> getAllInstruments() {
        return sessionFactory.getCurrentSession().getNamedQuery("instrument.all").list();
    }

    @Override
    public void updateInstrument(Instrument inst) {
        sessionFactory.getCurrentSession().update(inst);
    }

    ///////////////////////////////////////////////////////

    @Override
    public void addClient(Client client) {
        client.setId(null);
        sessionFactory.getCurrentSession().persist(client);
    }

    @Override
    public void addAllClients(List<Client> clients) {
        for(Client client : clients){
            client.setId(null);
            sessionFactory.getCurrentSession().persist(client);
        }
    }

    @Override
    public void deleteClient(Client client) {
        sessionFactory.getCurrentSession().delete(client);
    }

    @Override
    public void deleteAllClients(List<Client> clients) {
        for(Client client : clients){
            sessionFactory.getCurrentSession().delete(client);
        }
    }

    @Override
    public Client getClient(Long id) {
        Client client = (Client)
                sessionFactory.getCurrentSession().get(Client.class, id);

        return client;
    }

    @Override
    public Client getClientByName(String name) {
        Client client = (Client)
                sessionFactory.getCurrentSession().getNamedQuery("client.byName")
                        .setString("name", name).uniqueResult();

        return client;
    }

    @Override
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession().getNamedQuery("client.all").list();
    }

    @Override
    public void updateClient(Client client) {
        sessionFactory.getCurrentSession().update(client);
    }

    @Override
    public List<Instrument> getOwnedInstruments(Client client){

        return client.getInstruments();
    }

    ///////////////////////////////////////////////////////

    @Override
    public void addFactory(Factory factory) {
        factory.setId(null);
        sessionFactory.getCurrentSession().persist(factory);
    }

    @Override
    public void addAllFactories(List<Factory> factories) {
        for(Factory factory : factories){
            factory.setId(null);
            sessionFactory.getCurrentSession().persist(factories);
        }
    }

    @Override
    public void deleteFactory(Factory factory) {
        sessionFactory.getCurrentSession().delete(factory);
    }

    @Override
    public void deleteAllFactories(List<Factory> factories) {
        for(Factory factory : factories){
            sessionFactory.getCurrentSession().delete(factories);
        }
    }

    @Override
    public Factory getFactory(Long id) {
        Factory factory = (Factory)
                sessionFactory.getCurrentSession().get(Factory.class, id);

        return factory;
    }

    @Override
    public Factory getFactoryByName(String name) {
        Factory factory = (Factory)
                sessionFactory.getCurrentSession().getNamedQuery("factory.byName")
                        .setString("name", name).uniqueResult();

        return factory;
    }

    @Override
    public List<Factory> getAllFactories() {
        return sessionFactory.getCurrentSession().getNamedQuery("factory.all").list();
    }

    @Override
    public void updateFactory(Factory factory) {
        sessionFactory.getCurrentSession().update(factory);
    }
}
