package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.shdemo.domain.Instrument;

public interface InstrumentManager {

    void addInstrument(Instrument inst);

    void addAllInstrument(ArrayList<Instrument> inst);

    void deleteInstrument(Instrument inst);

    void deleteAllInstruments(ArrayList<Instrument> inst);

    Instrument getInstrument(Long id);

    Instrument getInstrumentByName(String name);

    List<Instrument> getAllInstrument();

    void updateInstrument(Instrument inst);

}
