package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.List;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.OfferEntity;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<OfferEntity, String> {

    List<OfferEntity> findByPerson(PersonEntity person);

    List<OfferEntity> findByEnableTrue();

}
