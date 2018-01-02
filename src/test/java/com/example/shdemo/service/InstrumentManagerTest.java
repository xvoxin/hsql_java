package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Instrument;

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

    @Test
    public void addOneInstrumentCheck(){
        Instrument inst = new Instrument(BRAND_1, NAME_1, PRICE_1);
        instrumentManager.addInstrument(inst);

        Instrument addedInst = instrumentManager.getInstrumentByName(NAME_1);
        assertEquals(NAME_1, addedInst.getName());
    }

    @Test
    public void addAllInstrumentCheck(){

    }


}