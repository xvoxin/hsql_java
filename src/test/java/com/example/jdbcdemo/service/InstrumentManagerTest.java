package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.example.jdbcdemo.domain.Instrument;

public class InstrumentManagerTest {


	InstrumentManagerImpl manager = new InstrumentManagerImpl();


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
	public void checkConnection(){
		assertNotNull(manager.getConnection());
	}

	@Test
	public void checkAddingAndGetting(){

		Instrument instrument = new Instrument(BRAND_1, NAME_1, PRICE_1);

		//manager.clearInstruments();
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

    @Test
	public void checkAllAdding(){
        ArrayList<Instrument> instruments = new ArrayList<>();

        manager.clearInstruments();

        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2));
        instruments.add(new Instrument(BRAND_3, null, PRICE_3));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4));

        manager.addAllInstrument(instruments);
        int size = manager.getAllInstrument().size();

        assertThat(size, either(is(4)).or(is(0)));
	}

	@Test
    public void deleteAllInstruments(){
        ArrayList<Instrument> instruments = new ArrayList<>();

        manager.clearInstruments();

        instruments.add(new Instrument(BRAND_1, NAME_1, PRICE_1));
        instruments.add(new Instrument(BRAND_2, NAME_2, PRICE_2));
        instruments.add(new Instrument(BRAND_3, NAME_3, PRICE_3));
        instruments.add(new Instrument(BRAND_4, NAME_4, PRICE_4));

        manager.addAllInstrument(instruments);

        instruments.get(2).setName(null);

        manager.deleteAllInstruments(instruments);

    }

}
