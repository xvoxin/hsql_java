package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Instrument;

public class InstrumentManagerTest {


	InstrumentManagerImpl inst = new InstrumentManagerImpl();


	private final static String BRAND_1 = "Gibson";
	private final static String NAME_1 = "Les Paul";
	private final static double PRICE_1 = 3999.99;


	@Test
	public void checkConnection(){
		assertNotNull(inst.getConnection());
	}

	@Test
	public void checkAdding(){

		Instrument instrument = new Instrument(1, BRAND_1, NAME_1, PRICE_1);

		inst.clearInstruments();
		assertEquals(1, inst.addInstrument(instrument));

		List<Instrument> instruments = inst.getAllInstrument();
		Instrument instrumentRetrieved = instruments.get(0);

		assertEquals(NAME_1, instrumentRetrieved.getName());
		assertEquals(BRAND_1, instrumentRetrieved.getBrand());

        instrumentRetrieved = inst.getInstrument(NAME_1);
        assertEquals(NAME_1, instrumentRetrieved.getName());

	}

	@Test
    public void checkDeleting(){

	    assertEquals(true, inst.deleteInstrument(1));
    }

    @Test
    public void checkUpdate(){

        Instrument instrument = new Instrument(BRAND_1, NAME_1, PRICE_1);
        instrument.setBrand("Epiphone");
        assertEquals(true, inst.updateInstrument(instrument));
    }

}
