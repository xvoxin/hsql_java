package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Instrument;

public interface InstrumentManager {
	
	int addInstrument(Instrument inst);
	int deleteInstrument(long id);
	Instrument getInstrument(String name);
	List<Instrument> getAllInstrument();
	int updateInstrument(Instrument inst);

}
