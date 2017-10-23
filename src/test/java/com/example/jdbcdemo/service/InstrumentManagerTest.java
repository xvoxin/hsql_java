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
		
		Instrument instrument = new Instrument(BRAND_1, NAME_1, PRICE_1);
		
		//inst.clearPersons();
		assertEquals(1, inst.addInstrument(instrument));
		
		List<Instrument> instruments = inst.getInstruments();
		Instrument InstrumentRetrieved = instrument.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(YOB_1, personRetrieved.getYob());
		
	}

}
