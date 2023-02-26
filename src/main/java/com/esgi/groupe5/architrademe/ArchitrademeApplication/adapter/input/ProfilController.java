package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameModalityQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonProfilCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Modality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ProfilId;
import com.esgi.groupe5.architrademe.kernel.CommandBus;
import com.esgi.groupe5.architrademe.kernel.QueryBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profil")
public class ProfilController {
        private final CommandBus commandBus;

        private final QueryBus queryBus;

        public ProfilController(CommandBus commandBus, QueryBus queryBus) {
                this.commandBus = commandBus;
                this.queryBus = queryBus;
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasModality('ROLE_CONSULTANT')")
        public ResponseEntity<?> create(@Valid @RequestBody ProfilRequest profilRequest, Errors errors) {
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
                                .post(new FindByIdPersonQuery(profilRequest.user));
                                if (person == null) {
                                        map.put("message", "User not found!");

                                        return new ResponseEntity<>(
                                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                                        map),
                                                        HttpStatus.NOT_FOUND);
                                }

                if (queryBus
                                .post(new FindByIdPersonProfilQuery(person)) != null) {
                        map.put("message", "User have already profil");
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.FOUND.value(),
                                                        map),
                                        HttpStatus.FOUND);
                }

                Profil profil = new Profil(ProfilId.of(UUID.randomUUID()),
                                profilRequest.disponibility, Math.round(profilRequest.tjm), person);

                Set<String> strModalities = profilRequest.modality;

                Set<Modality> modalities = new HashSet<>();

                if (strModalities == null) {
                        Modality profilModality = (Modality) queryBus
                                        .post(new FindByNameModalityQuery(ENUMModality.VIREMENT));

                        modalities.add(profilModality);
                } else {
                        strModalities.forEach(modality -> {
                                if (modality.equals("CARTE_BANCAIRE")) {
                                        Modality adminModality = (Modality) queryBus
                                                .post(new FindByNameModalityQuery(
                                                        ENUMModality.CARTE_BANCAIRE));
                                        modalities.add(adminModality);
                                } else {
                                        Modality profilModality = (Modality) queryBus
                                                .post(new FindByNameModalityQuery(ENUMModality.VIREMENT));
                                        modalities.add(profilModality);
                                }
                        });
                }
                profil.setModalities(modalities);

                Profil save = (Profil) commandBus.post(new RegisterPersonProfilCommand(profil));

                String link = "http://localhost:8080/api/profil/" + save.getProfilId().value();

                map.put("message", "Success to save");
                map.put("data", save);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.CREATED.value(), map),
                                HttpStatus.CREATED);
        }

        @PutMapping(value = "/{profil_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasModality('ROLE_CONSULTANT')")
        public ResponseEntity<?> update(@PathVariable(value = "profil_id") String profil_id,
                        @Valid @RequestBody ProfilUpdateRequest profilRequest, Errors errors) {
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

                Profil profil = (Profil) queryBus
                                .post(new FindByIdProfilQuery(profil_id));

                if (profil == null) {
                        map.put("message", "Profil Not found");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                profil.setDisponibility(profilRequest.disponibility);
                profil.setTjm(Math.round(profilRequest.tjm));

                Set<String> strModalities = profilRequest.modality;

                Set<Modality> modalities = new HashSet<Modality>();

                if (strModalities == null) {
                        Modality profilModality = (Modality) queryBus
                                        .post(new FindByNameModalityQuery(ENUMModality.VIREMENT));

                        modalities.add(profilModality);
                } else {
                        strModalities.forEach(modality -> {
                                if (modality.equals("CARTE_BANCAIRE")) {
                                        Modality adminModality = (Modality) queryBus
                                                .post(new FindByNameModalityQuery(
                                                        ENUMModality.CARTE_BANCAIRE));
                                        modalities.add(adminModality);
                                } else {
                                        Modality profilModality = (Modality) queryBus
                                                .post(new FindByNameModalityQuery(ENUMModality.VIREMENT));
                                        modalities.add(profilModality);
                                }
                        });
                }

                profil.setModalities(modalities);

                Profil save = (Profil) commandBus.post(new RegisterPersonProfilCommand(profil));

                String link = "http://localhost:8080/api/profil/" + save.getProfilId().value();

                map.put("message", "Success to update");
                map.put("data", save);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getByIdProf(@PathVariable String id) {

                Map<String, Object> map = new LinkedHashMap<String, Object>();

                Profil person = (Profil) queryBus
                                .post(new FindByIdProfilQuery(id));

                if (person == null) {
                        map.put("message", "Profil Not found");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }
                map.put("data", person);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(),
                                                map),
                                HttpStatus.OK);
        }

}
