package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import java.util.List;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Offer;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;

public interface OfferPort {

    void save(Offer offer);

    List<Offer> findByEnable(Boolean enable);

    List<Offer> findByPerson(Person person);

    Offer findById(String id);

    void remove(Offer offer);
}
