package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Instrument;

import javax.persistence.criteria.CriteriaBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class InstrumentManagerTest {

    @Autowired
    InstrumentManager instrumentManager;

    private final static String BRAND_1 = "Gibson";
    private final static String NAME_1 = "Les Paul";
    private final static double PRICE_1 = 3999.99;

    private final static String BRAND_2 = "Fender";
    private final static String BRAND_3 = "ESP";
    private final static String BRAND_4 = "Jackson";

    private final static String NAME_2 = "Stratocaster";
    private final static String NAME_3 = "V20";
    private final static String NAME_4 = "RR32";

    private final static double PRICE_2 = 4500.00;
    private final static double PRICE_3 = 2600.69;
    private final static double PRICE_4 = 7849.49;

    private final static String CLIENT_NAME_1 = "Janusz";
    private final static String CLIENT_NAME_2 = "Elon";
    private final static String CLIENT_NAME_3 = "Krystian";

    private final static String CLIENT_SURNAME_1 = "Polak";
    private final static String CLIENT_SURNAME_2 = "Musk";
    private final static String CLIENT_SURNAME_3 = "Bathis";

    private final static String FACTORY_NAME_1 = "Shelby Company Limited";
    private final static String FACTORY_NAME_2 = "OldBlood";
    private final static String FACTORY_NAME_3 = "DarkSouls";
    private final static String FACTORY_NAME_4 = "Masterpiece";

    private final static String FACTORY_COUNTRY_1 = "UK";
    private final static String FACTORY_COUNTRY_2 = "USA";

    // Factory //
    @Test
    public void Check_AddOne_GetOne_Factory(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);

        instrumentManager.addFactory(factory);

        Factory addedFactory = instrumentManager.getFactoryByName(FACTORY_NAME_1);

        assertEquals(FACTORY_NAME_1, addedFactory.getName());
        assertEquals(FACTORY_COUNTRY_1, addedFactory.getCountry());
    }

    @Test
    public void Check_AddAll_GetAll_Factory(){

        ArrayList<Factory> factories = new ArrayList<Factory>();
        factories.add(new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_2, FACTORY_COUNTRY_2));
        factories.add(new Factory(FACTORY_NAME_3, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_4, FACTORY_COUNTRY_2));

        instrumentManager.addAllFactories(factories);

        List<Factory> addedFactories = instrumentManager.getAllFactories();

        assertEquals(4, addedFactories.size());

        assertEquals(FACTORY_NAME_1, addedFactories.get(0).getName());
        assertEquals(FACTORY_COUNTRY_1, addedFactories.get(0).getCountry());
        assertEquals(FACTORY_NAME_2, addedFactories.get(1).getName());
        assertEquals(FACTORY_COUNTRY_2, addedFactories.get(1).getCountry());

    }

    @Test
    public void Check_Delete_Factory(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);

        instrumentManager.addFactory(factory);
        instrumentManager.deleteFactory(factory);

        assertEquals(0, instrumentManager.getAllFactories().size());

    }

    @Test
    public void Check_DeleteAll_Factory(){
        ArrayList<Factory> factories = new ArrayList<Factory>();
        factories.add(new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_2, FACTORY_COUNTRY_2));
        factories.add(new Factory(FACTORY_NAME_3, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_4, FACTORY_COUNTRY_2));

        instrumentManager.addAllFactories(factories);
        instrumentManager.deleteAllFactories(factories);

        assertEquals(0, instrumentManager.getAllFactories().size());
    }

    @Test
    public void Check_Update_Factory(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);

        instrumentManager.addFactory(factory);
        factory.setCountry(FACTORY_COUNTRY_2);

        instrumentManager.updateFactory(factory);

        assertEquals(FACTORY_COUNTRY_2, instrumentManager.getFactoryByName(FACTORY_NAME_1).getCountry());
    }

    // Instrument //

    @Test
    public void Check_AddOne_GetOne_Instrument(){
        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        Instrument inst = new Instrument(BRAND_1, NAME_1, PRICE_1, factory);
        instrumentManager.addInstrument(inst);

        Instrument addedInst = instrumentManager.getInstrumentByName(NAME_1);
        assertEquals(NAME_1, addedInst.getName());
        assertEquals(BRAND_1, addedInst.getBrand());
        assertEquals(FACTORY_NAME_1, addedInst.getFactory().getName());
    }

    @Test
    public void Check_AddAll_GetAll_Instrument(){

        ArrayList<Factory> factories = new ArrayList<Factory>();
        factories.add(new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_2, FACTORY_COUNTRY_2));
        factories.add(new Factory(FACTORY_NAME_3, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_4, FACTORY_COUNTRY_2));

        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factories.get(0)));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factories.get(1)));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factories.get(2)));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factories.get(3)));

        instrumentManager.addAllInstruments(instruments);

        List<Instrument> addedInst = instrumentManager.getAllInstruments();
        List<Factory> addedFactory = instrumentManager.getAllFactories();

        assertEquals(4, addedInst.size());
        assertEquals(4, addedFactory.size());
        assertEquals(NAME_1, addedInst.get(0).getName());
        assertEquals(BRAND_1, addedInst.get(0).getBrand());
        assertEquals(FACTORY_NAME_1, addedInst.get(0).getFactory().getName());

        assertEquals(NAME_2, addedInst.get(1).getName());
        assertEquals(BRAND_2, addedInst.get(1).getBrand());
        assertEquals(FACTORY_COUNTRY_2, addedInst.get(1).getFactory().getCountry());

    }

    @Test
    public void Check_Delete_Instrument_LeaveFactory(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        Instrument inst = new Instrument(BRAND_1, NAME_1, PRICE_1, factory);
        instrumentManager.addInstrument(inst);

        instrumentManager.deleteInstrument(inst);

        assertEquals(0, instrumentManager.getAllInstruments().size());
        assertEquals(1, instrumentManager.getAllFactories().size());

    }

    @Test
    public void Check_DeleteFactory_DeleteInstrument(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        Instrument inst = new Instrument(BRAND_1, NAME_1, PRICE_1, factory);
        instrumentManager.addInstrument(inst);

        instrumentManager.deleteFactory(factory);

        assertEquals(0, instrumentManager.getAllInstruments().size());
        assertEquals(0, instrumentManager.getAllFactories().size());

    }

    @Test
    public void Check_DeleteAll_Instrument(){

        ArrayList<Factory> factories = new ArrayList<Factory>();
        factories.add(new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_2, FACTORY_COUNTRY_2));
        factories.add(new Factory(FACTORY_NAME_3, FACTORY_COUNTRY_1));
        factories.add(new Factory(FACTORY_NAME_4, FACTORY_COUNTRY_2));

        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factories.get(0)));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factories.get(1)));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factories.get(2)));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factories.get(3)));

        instrumentManager.addAllInstruments(instruments);
        instrumentManager.deleteAllInstruments(instruments);
        assertEquals(0, instrumentManager.getAllInstruments().size());
    }

    @Test
    public void Check_Update_Instrument(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        Instrument inst = new Instrument(BRAND_1, NAME_1, PRICE_1, factory);

        instrumentManager.addInstrument(inst);
        inst.setBrand(BRAND_2);
        Factory factory2 = new Factory(FACTORY_NAME_2, FACTORY_COUNTRY_2);
        inst.setFactory(factory2);
        instrumentManager.updateInstrument(inst);

        assertEquals(FACTORY_COUNTRY_2, instrumentManager.getFactoryByName(FACTORY_NAME_2).getCountry());
    }

    // Client //
    @Test
    public void Check_AddOne_GetOne_Client(){
        Client client = new Client(CLIENT_NAME_1, CLIENT_SURNAME_1);

        instrumentManager.addClient(client);

        Client addedClient = instrumentManager.getClientByName(CLIENT_NAME_1);
        assertEquals(CLIENT_NAME_1, addedClient.getName());
        assertEquals(CLIENT_SURNAME_1, addedClient.getSurname());

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factory));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factory));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factory));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factory));

        Client client2 = new Client(CLIENT_NAME_2, CLIENT_SURNAME_2, new Date(), instruments);
        instrumentManager.addClient(client2);

        assertEquals(4, instrumentManager.getAllInstruments().size());
    }

    @Test
    public void Check_Delete_Client(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factory));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factory));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factory));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factory));

        Client client = new Client(CLIENT_NAME_2, CLIENT_SURNAME_2, new Date(), instruments);
        instrumentManager.addClient(client);

        instrumentManager.deleteClient(client);

        assertEquals(0, instrumentManager.getAllClients().size());
    }

    @Test
    public void Check_DeleteAll_Client(){
        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factory));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factory));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factory));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factory));

        ArrayList<Client> clients = new ArrayList<Client>();
        clients.add(new Client(CLIENT_NAME_2, CLIENT_SURNAME_2, new Date(), instruments));
        clients.add(new Client(CLIENT_NAME_1, CLIENT_SURNAME_1, new Date(), instruments));
        clients.add(new Client(CLIENT_NAME_3, CLIENT_SURNAME_3, new Date(), instruments));
        clients.add(new Client(CLIENT_NAME_2, CLIENT_SURNAME_3, new Date(), instruments));

        instrumentManager.addAllClients(clients);

        assertEquals(4, instrumentManager.getAllClients().size());

        instrumentManager.deleteAllClients(clients);

        assertEquals(0, instrumentManager.getAllClients().size());
    }


    @Test
    public void Check_Update_Client(){

        Factory factory = new Factory(FACTORY_NAME_1, FACTORY_COUNTRY_1);
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factory));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factory));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factory));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factory));

        Client client = new Client(CLIENT_NAME_2, CLIENT_SURNAME_2, new Date(), instruments);

        instrumentManager.addClient(client);

        client.setName(CLIENT_NAME_1);
        client.setSurname(CLIENT_SURNAME_1);

        instrumentManager.updateClient(client);

        assertEquals(CLIENT_NAME_1, instrumentManager.getClientByName(CLIENT_NAME_1).getName());
        assertEquals(CLIENT_SURNAME_1, instrumentManager.getClientByName(CLIENT_NAME_1).getSurname());
    }
}