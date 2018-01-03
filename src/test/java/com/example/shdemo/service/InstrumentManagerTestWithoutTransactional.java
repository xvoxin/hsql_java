package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Factory;
import com.example.shdemo.domain.Instrument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class InstrumentManagerTestWithoutTransactional {
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

    @Test
    public void CheckLazyException(){
        Factory factory = new Factory("sztos", "Polska");
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1, factory));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2, factory));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3, factory));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4, factory));

        Client client = new Client("Krystian", "Kowalksi", new Date(), instruments);

        instrumentManager.addClient(client);

        Client addedClient = instrumentManager.getClientByName("Krystian");
        boolean pass = false;

        try{
            System.out.println(addedClient.getInstruments().size());
        } catch (org.hibernate.LazyInitializationException e) {
            e.printStackTrace();
            pass = true;
        }

        if (!pass)
            org.junit.Assert.fail();

        instrumentManager.deleteClient(client);
    }

}
