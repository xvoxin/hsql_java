package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Instrument;

public interface InstrumentManager {
	
	int addInstrument(Instrument inst);
	boolean deleteInstrument(long id);
	Instrument getInstrument(String name);
	List<Instrument> getAllInstrument();
	boolean updateInstrument(Instrument inst);

}
