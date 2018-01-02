package com.example.shdemo.service;

import com.example.shdemo.domain.Instrument;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Component
@Transactional
public class InstrumentManagerHibernateImpl implements InstrumentManager{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addInstrument(Instrument inst) {
        inst.setId(null);
        sessionFactory.getCurrentSession().persist(inst);
    }

    @Override
    public void addAllInstrument(ArrayList<Instrument> inst) {
        for(Instrument instrument : inst){
            instrument.setId(null);
            sessionFactory.getCurrentSession().persist(instrument);
        }
    }

    @Override
    public void deleteInstrument(Instrument inst) {
        sessionFactory.getCurrentSession().delete(inst);
    }

    @Override
    public void deleteAllInstruments(ArrayList<Instrument> inst) {
        for(Instrument instrument : inst){
            sessionFactory.getCurrentSession().delete(instrument);
        }
    }

    @Override
    public Instrument getInstrument(Long id) {
        Instrument instrument = (Instrument)
        sessionFactory.getCurrentSession().get(Instrument.class, id);

        return instrument;
    }

    @Override
    public Instrument getInstrumentByName(String name) {
        Instrument instrument = (Instrument)
                sessionFactory.getCurrentSession().getNamedQuery("instrument.byName");

        return instrument;
    }

    @Override
    public List<Instrument> getAllInstrument() {
        return sessionFactory.getCurrentSession().getNamedQuery("instrument.all").list();
    }

    @Override
    public void updateInstrument(Instrument inst) {
        sessionFactory.getCurrentSession().update(inst);
    }
}
