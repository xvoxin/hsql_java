package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Instrument;

public interface InstrumentManager {
	
	public int addInstrument(Instrument inst);
	public void deleteInstrument(long id);
	public List<Instrument> getInstrument();
	public Instrument updateInstrument(long id);

}
