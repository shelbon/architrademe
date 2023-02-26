package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindAllOfferQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdOfferQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RemoveOfferCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveOfferCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Offer;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.OfferId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.CommandBus;
import com.esgi.groupe5.architrademe.kernel.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offer")
public class OfferController {
        private final CommandBus commandBus;

        private final QueryBus queryBus;

        public OfferController(CommandBus commandBus, QueryBus queryBus) {
                this.commandBus = commandBus;
                this.queryBus = queryBus;
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasModality('ROLE_CLIENT')")
        public ResponseEntity<?> create(@Valid @RequestBody OfferRequest offerRequest, Errors errors) {
                if (errors.hasErrors()) {
                        final List<String> err = new ArrayList<String>();
                        var i = 0;
                        for (final ObjectError error : errors.getFieldErrors()) {
                                err.add(errors.getFieldErrors().get(i).getField() + ": " + error.getDefaultMessage());
                                i++;
                        }
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                                                        err),
                                        HttpStatus.BAD_REQUEST);
                }
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                Person person = (Person) queryBus
                                .post(new FindByIdPersonQuery(offerRequest.user));
                if (person == null) {
                        map.put("message", "User not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                Offer offer = new Offer(OfferId.of(UUID.randomUUID()), offerRequest.name, offerRequest.description,
                                person, offerRequest.enDate, offerRequest.typeContract);

                commandBus.post(new SaveOfferCommand(offer));

                String link = "http://localhost:8080/api/offer/" + offer.getOfferId().value();

                map.put("message", "Success to save");
                map.put("data", offer);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.CREATED.value(), map),
                                HttpStatus.CREATED);
        }

        @PutMapping(value = "/{offer_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasModality('ROLE_CLIENT')")
        public ResponseEntity<?> update(@PathVariable(value = "offer_id") String offer_id,
                        @Valid @RequestBody OfferUpdateRequest offerRequest, Errors errors) {
                if (errors.hasErrors()) {
                        final List<String> err = new ArrayList<String>();
                        var i = 0;
                        for (final ObjectError error : errors.getFieldErrors()) {
                                err.add(errors.getFieldErrors().get(i).getField() + ": " + error.getDefaultMessage());
                                i++;
                        }
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                                                        err),
                                        HttpStatus.BAD_REQUEST);
                }
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                Offer offer = (Offer) queryBus
                                .post(new FindByIdOfferQuery(offer_id));
                if (offer == null) {
                        map.put("message", "Offer not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                Date d = new Date();

                offer.setName(offerRequest.name);
                offer.setDescription(offerRequest.description);
                offer.setEnDate(offerRequest.enDate);
                offer.setTypeContract(offerRequest.typeContract);
                offer.setEnable(offerRequest.enDate.after(d));
                commandBus.post(new SaveOfferCommand(offer));

                String link = "http://localhost:8080/api/offer/" + offer.getOfferId().value();

                map.put("message", "Success to update");
                map.put("data", offer);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getById(@PathVariable String id) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                Offer offer = (Offer) queryBus
                                .post(new FindByIdOfferQuery(id));

                map.put("data", offer);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(),
                                                map),
                                HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ROLE_CLIENT') ")
        public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                Offer offer = (Offer) queryBus
                                .post(new FindByIdOfferQuery(id));

                commandBus.post(new RemoveOfferCommand(offer));

                map.put("message", "Success deleted");

                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getAll() {
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                List<Offer> offer = (List<Offer>) queryBus
                                .post(new FindAllOfferQuery(true));

                map.put("data", offer);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

}
