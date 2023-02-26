package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.DeleteSkillCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdSkillAndPersonProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByPersonProfilSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveSkillCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Profil;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Skill;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.SkillId;
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
@RequestMapping("/api/skill")
public class SkillController {
        private final CommandBus commandBus;

        private final QueryBus queryBus;

        public SkillController(CommandBus commandBus, QueryBus queryBus) {
                this.commandBus = commandBus;
                this.queryBus = queryBus;
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasModality('ROLE_CONSULTANT')")
        public ResponseEntity<?> create(@Valid @RequestBody SkillRequest skillRequest, Errors errors) {
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

                Profil person = (Profil) queryBus
                                .post(new FindByIdProfilQuery(skillRequest.profil));
                if (person == null) {
                        map.put("message", "User not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                if (queryBus.post(new FindByNameSkillQuery(skillRequest.name)) != null) {
                        map.put("message", "Skill have already save");
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.FOUND.value(),
                                                        map),
                                        HttpStatus.FOUND);
                }

                Skill skill = new Skill(SkillId.of(UUID.randomUUID()), skillRequest.name, skillRequest.notion);

                commandBus.post(new SaveSkillCommand(skill, person));

                String link = "http://localhost:8080/api/skill/" + skill.getSkillId().value();

                map.put("message", "Success to save");
                map.put("data", skill);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.CREATED.value(), map),
                                HttpStatus.CREATED);
        }

        @PutMapping(value = "/{skill_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @PreAuthorize("hasModality('ROLE_CONSULTANT')")
        public ResponseEntity<?> update(@PathVariable(value = "skill_id") String skill_id,
                        @Valid @RequestBody SkillUpdateRequest skillRequest, Errors errors) {
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

                Skill skil = (Skill) queryBus
                                .post(new FindByIdSkillQuery(skill_id));
                if (skil == null) {
                        map.put("message", "Skill not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                Profil profil = (Profil) queryBus
                                .post(new FindByIdProfilQuery(skillRequest.profil));

                if (profil == null) {
                        map.put("message", "Profil not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                Skill skill = (Skill) queryBus
                                .post(new FindByIdSkillAndPersonProfilQuery(skill_id, profil));
                if (skill == null) {
                        map.put("message", "This Profil have not this Skill !");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                skill.setName(skillRequest.name);
                skill.setNotion(skillRequest.notion);

                commandBus.post(new SaveSkillCommand(skill, profil));

                String link = "http://localhost:8080/api/skill/" + skill.getSkillId().value();

                map.put("message", "Success to update");
                map.put("data", skill);
                map.put("link", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

        @GetMapping(value = "/{id_profil}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getByIdProfil(@PathVariable(value = "id_profil") String id) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                Profil profil = (Profil) queryBus
                                .post(new FindByIdProfilQuery(id));
                if (profil == null) {
                        map.put("message", "Profil not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                List<Skill> skill = (List<Skill>) queryBus
                                .post(new FindByPersonProfilSkillQuery(profil));

                map.put("data", skill);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(),
                                                map),
                                HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ROLE_CONSULTANT') ")
        public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                Skill skill = (Skill) queryBus
                                .post(new FindByIdSkillQuery(id));
                if (skill == null) {
                        map.put("message", "Skill not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(), map),
                                        HttpStatus.NOT_FOUND);
                }

                commandBus.post(new DeleteSkillCommand(skill));

                map.put("message", "Success deleted");

                return new ResponseEntity<>(new MessageResponse(HttpStatus.OK.value(), map),
                                HttpStatus.OK);
        }

}
