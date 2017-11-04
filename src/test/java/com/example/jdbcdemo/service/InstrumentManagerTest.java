package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Instrument;

public class InstrumentManagerTest {


	InstrumentManagerImpl manager = new InstrumentManagerImpl();


	private final static String BRAND_1 = "Gibson";
	private final static String NAME_1 = "Les Paul";
	private final static double PRICE_1 = 3999.99;


	@Test
	public void checkConnection(){
		assertNotNull(manager.getConnection());
	}

	@Test
	public void checkAddingAndGetting(){

		Instrument instrument = new Instrument(BRAND_1, NAME_1, PRICE_1);

		manager.clearInstruments();
		assertEquals(1, manager.addInstrument(instrument));

		List<Instrument> instruments = manager.getAllInstrument();
		Instrument instrumentRetrieved = instruments.get(0);

		assertEquals(NAME_1, instrumentRetrieved.getName());
		assertEquals(BRAND_1, instrumentRetrieved.getBrand());

        instrumentRetrieved = manager.getInstrument(NAME_1);
        assertEquals(NAME_1, instrumentRetrieved.getName());

	}

	@Test
    public void checkDeleting(){

		Instrument instrument = new Instrument(BRAND_1, NAME_1, PRICE_1);

		manager.clearInstruments();
		manager.addInstrument(instrument);
        Instrument instrumentRetrieved = manager.getInstrument(NAME_1);

	    assertEquals(1, manager.deleteInstrument(instrumentRetrieved.getId()));
    }

    @Test
    public void checkUpdate(){

        Instrument instrument = new Instrument(BRAND_1, NAME_1, PRICE_1);

        manager.clearInstruments();
        manager.addInstrument(instrument);
        Instrument instrumentRetrieved = manager.getInstrument(NAME_1);

        instrumentRetrieved.setBrand("Epiphone");
        assertEquals(1, manager.updateInstrument(instrumentRetrieved));

        instrumentRetrieved = manager.getInstrument(NAME_1);
        assertEquals("Epiphone", instrumentRetrieved.getBrand());
    }

}
