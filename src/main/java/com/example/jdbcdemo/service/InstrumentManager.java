package com.example.jdbcdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Instrument;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

public interface InstrumentManager {
	
	int addInstrument(Instrument inst);

	int addAllInstrument(ArrayList<Instrument> inst);

	int deleteInstrument(long id);

	int deleteAllInstruments(ArrayList<Instrument> inst);

	Instrument getInstrument(String name);

	List<Instrument> getAllInstrument();

	int updateInstrument(Instrument inst);

//	void addUpdateDelete(ArrayList<Instrument> inst);

}
