package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByUsernameQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.kernel.CommandBus;
import com.esgi.groupe5.architrademe.kernel.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/person")
public class PersonController {
        private final CommandBus commandBus;

        private final QueryBus queryBus;

        @Autowired
        public PersonController(CommandBus commandBus, QueryBus queryBus) {
                this.commandBus = commandBus;
                this.queryBus = queryBus;
        }

        @PutMapping(value = "/{person_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> update(@PathVariable(value = "person_id") String person_id,
                        @Valid @RequestBody PersonRequest personRequest, Errors errors) {

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
                                .post(new FindByIdPersonQuery(person_id));

                if (person == null) {
                        map.put("message", "User not found");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                Person p = (Person) queryBus
                                .post(new FindByEmailQuery(personRequest.email));
                if (p != null)
                        if (!p.getPersonId().getValue().equals(person.getPersonId().getValue())) {
                                map.put("message", "User with this email exist already");

                                return new ResponseEntity<>(
                                                new MessageResponse(HttpStatus.FOUND.value(),
                                                                map),
                                                HttpStatus.FOUND);
                        }

                Person pU = (Person) queryBus
                                .post(new FindByUsernameQuery(personRequest.username));

                if (pU != null)
                        if ((!pU.getPersonId().getValue().equals(person.getPersonId().getValue()))) {
                                map.put("message", "User with this username exist already");

                                return new ResponseEntity<>(
                                                new MessageResponse(HttpStatus.FOUND.value(),
                                                                map),
                                                HttpStatus.FOUND);
                        }

                person.setUsername(personRequest.username);
                person.setEmail(personRequest.email);
                person.setLastName(personRequest.lastName);
                person.setFirstName(personRequest.firstName);
                person.setPhone(personRequest.phone);

                commandBus.post(new RegisterPersonCommand(person));

                String link = "http://localhost:8080/api/person/" + person.getPersonId().value();

                map.put("message", "Success to update");
                map.put("data", person);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getById(@PathVariable String id) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                Person person = (Person) queryBus
                                .post(new FindByIdPersonQuery(id));

                map.put("data", person);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(),
                                                map),
                                HttpStatus.OK);
        }

}
