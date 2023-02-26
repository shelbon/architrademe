package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.OfferPort;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Offer;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.OfferId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OfferPersistenceAdapter implements OfferPort {

    private final OfferRepository offerRepository;

    public OfferPersistenceAdapter(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public void save(Offer offer) {
        var u = new PersonEntity(offer.getPerson().getPersonId().value(),
                offer.getPerson().getUsername(),
                offer.getPerson().getLastName(),
                offer.getPerson().getFirstName(),
                offer.getPerson().getEmail(),
                offer.getPerson().getPhone(),
                offer.getPerson().getPassword());

        var off = new OfferEntity(offer.getOfferId().value(),
                offer.getName(), offer.getDescription(),
                u, offer.getEnDate(), offer.getTypeContract());
        offerRepository.save(off);
    }

    @Override
    public List<Offer> findByEnable(Boolean enable) {
        var offreL = new ArrayList<Offer>();

        var of = offerRepository.findByEnableTrue();
        for (OfferEntity offer : of) {
            var u = new Person(PersonId.of(UUID.fromString(offer.getPerson().getId())),
                    offer.getPerson().getUsername(),
                    offer.getPerson().getLastName(),
                    offer.getPerson().getFirstName(),
                    offer.getPerson().getEmail(),
                    offer.getPerson().getPhone(),
                    offer.getPerson().getPassword());

            offreL.add(new Offer(OfferId.of(UUID.fromString(offer.getId())),
                    offer.getName(), offer.getDescription(),
                    u, offer.getEnDate(), offer.getTypeContract(),
                    offer.getCreatedAt(), offer.getUpdatedAt(), offer.getEnable()));
        }

        return offreL;
    }

    @Override
    public List<Offer> findByPerson(Person person) {
        var offreL = new ArrayList<Offer>();
        var perEn = new PersonEntity(person.getPersonId().value(),
                person.getUsername(), person.getLastName(), person.getFirstName(),
                person.getEmail(), person.getPhone(), person.getPassword());

        var of = offerRepository.findByPerson(perEn);

        for (OfferEntity offer : of) {
            var u = new Person(PersonId.of(UUID.fromString(offer.getPerson().getId())),
                    offer.getPerson().getUsername(),
                    offer.getPerson().getLastName(),
                    offer.getPerson().getFirstName(),
                    offer.getPerson().getEmail(),
                    offer.getPerson().getPhone(),
                    offer.getPerson().getPassword());

            offreL.add(new Offer(OfferId.of(UUID.fromString(offer.getId())),
                    offer.getName(), offer.getDescription(),
                    u, offer.getEnDate(), offer.getTypeContract()));
        }
        return offreL;

    }

    @Override
    public Offer findById(String id) {

        final Optional<OfferEntity> optionalEntity = offerRepository.findById(id);
        if (optionalEntity.isPresent()) {
            var offer = optionalEntity.get();

            var u = new Person(PersonId.of(UUID.fromString(offer.getPerson().getId())),
                    offer.getPerson().getUsername(),
                    offer.getPerson().getLastName(),
                    offer.getPerson().getFirstName(),
                    offer.getPerson().getEmail(),
                    offer.getPerson().getPhone(),
                    offer.getPerson().getPassword());

            return new Offer(OfferId.of(UUID.fromString(offer.getId())),
                    offer.getName(), offer.getDescription(),
                    u, offer.getEnDate(), offer.getTypeContract());

        }
        return null;
    }

    @Override
    public void remove(Offer offer) {
        var u = new PersonEntity(offer.getPerson().getPersonId().value(),
                offer.getPerson().getUsername(),
                offer.getPerson().getLastName(),
                offer.getPerson().getFirstName(),
                offer.getPerson().getEmail(),
                offer.getPerson().getPhone(),
                offer.getPerson().getPassword());

        var of = new OfferEntity(offer.getOfferId().value(),
                offer.getName(), offer.getDescription(),
                u, offer.getEnDate(), offer.getTypeContract(), offer.getCreatedAt(), offer.getUpdatedAt(),
                offer.getEnable());

        offerRepository.delete(of);
    }

}
