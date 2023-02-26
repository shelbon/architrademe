package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.JwtUtils;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.PersonDetailsPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameRoleQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.PersonDetailsImpl;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByPersonForConfTokQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.GetTokenQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveConfirmationTokenCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SetConfirmedAtCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.UpdateTokenCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.BuildEmailCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.BuildEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.EnablePersonCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.ExistsByEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.ExistsByUsernameQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByUsernameQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ConfirmationToken;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMRole;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Person;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.PersonId;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Role;
import com.esgi.groupe5.architrademe.kernel.CommandBus;
import com.esgi.groupe5.architrademe.kernel.QueryBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
        @Autowired
        AuthenticationManager authenticationManager;

        private final CommandBus commandBus;

        private final QueryBus queryBus;

        @Autowired
        private AuthController(CommandBus commandBus, QueryBus queryBus) {
                this.commandBus = commandBus;
                this.queryBus = queryBus;
        }

        @Autowired
        PasswordEncoder encoder;

        @Autowired
        JwtUtils jwtUtils;

        @Autowired
        PersonDetailsPersistenceAdapter userDetailsService;

        @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> authenticatePerson(@Valid @RequestBody LoginRequest loginRequest, Errors errors)
                        throws Exception {

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

                try {
                        Authentication authentication = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                        loginRequest.getPassword()));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        String jwt = jwtUtils.generateJwtToken(authentication);
                        PersonDetailsImpl personDetails = (PersonDetailsImpl) authentication.getPrincipal();
                        if (!personDetails.getEnable()) {
                                Map<String, Object> map = new LinkedHashMap<String, Object>();

                                map.put("message", "A new mail would send! Please confirm your mail");
                                String tokenForNewUser = UUID.randomUUID().toString();

                                Person personUser = (Person) queryBus
                                                .post(new FindByUsernameQuery(personDetails.getUsername()));

                                if (personUser == null) {
                                        map.put("message", "User not found!");

                                        return new ResponseEntity<>(
                                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                                        map),
                                                        HttpStatus.NOT_FOUND);
                                }

                                ConfirmationToken confiTok = (ConfirmationToken) queryBus
                                                .post(new FindByPersonForConfTokQuery(personUser));
                                if (confiTok == null) {
                                        map.put("message", "Token not found!");

                                        return new ResponseEntity<>(
                                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                                        map),
                                                        HttpStatus.NOT_FOUND);
                                }

                                commandBus.post(new UpdateTokenCommand(confiTok, tokenForNewUser));

                                String link = "http://localhost:8080/api/auth/signup/confirm?token=" +
                                                tokenForNewUser;

                                String email = (String) queryBus
                                                .post(new BuildEmailQuery(personDetails.getFirstName(), link));

                                commandBus.post(new BuildEmailCommand(personDetails.getEmail(), email));

                                map.put("link", link);
                                return new ResponseEntity<>(
                                                new MessageResponse(HttpStatus.FORBIDDEN.value(),
                                                                map),
                                                HttpStatus.FORBIDDEN);

                        }

                        List<String> roles = personDetails.getAuthorities().stream()
                                        .map(item -> item.getAuthority())
                                        .collect(Collectors.toList());

                        return new ResponseEntity<>(new MessageResponse(
                                        HttpStatus.OK.value(),
                                        new JwtResponse(personDetails.getId(),
                                                        personDetails.getUsername(),
                                                        personDetails.getEmail(),
                                                        roles,
                                                        jwt)),
                                        HttpStatus.OK);
                } catch (DisabledException e) {

                        throw new Exception("USER_DISABLED", e);
                } catch (BadCredentialsException e) {

                        throw new Exception("INVALID_CREDENTIALS", e);
                }

        }

        @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> registerPerson(@Valid @RequestBody SignupRequest signUpRequest, Errors errors) {
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

                if ((boolean) queryBus
                                .post(new ExistsByUsernameQuery(signUpRequest.username))) {
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                                                        "Error: Username is already taken!"),
                                        HttpStatus.BAD_REQUEST);
                }

                if ((boolean) queryBus
                                .post(new ExistsByEmailQuery(signUpRequest.username))) {
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                                                        "Error: Email is already in use!"),
                                        HttpStatus.BAD_REQUEST);
                }

                // Create new person's account
                Person person = new Person(PersonId.of(UUID.randomUUID()), signUpRequest.username,
                                signUpRequest.lastName,
                                signUpRequest.firstName,
                                signUpRequest.email,
                                signUpRequest.phone,
                                encoder.encode(signUpRequest.password));

                Set<String> strRoles = signUpRequest.role;
                Set<Role> roles = new HashSet<Role>();

                if (strRoles == null) {
                        Role personRole = (Role) queryBus
                                        .post(new FindByNameRoleQuery(ENUMRole.ROLE_CLIENT));

                        roles.add(personRole);
                } else {
                        strRoles.forEach(role -> {
                                if (role.equals("consultant")) {
                                        Role modRole = (Role) queryBus
                                                .post(new FindByNameRoleQuery(ENUMRole.ROLE_CONSULTANT));
                                        roles.add(modRole);
                                } else {
                                        Role personRole = (Role) queryBus
                                                .post(new FindByNameRoleQuery(ENUMRole.ROLE_CLIENT));

                                        roles.add(personRole);
                                }
                        });
                }

                person.setRoles(roles);
                commandBus.post(new RegisterPersonCommand(person));

                String tokenForNewUser = UUID.randomUUID().toString();

                commandBus.post(new SaveConfirmationTokenCommand(person, tokenForNewUser));

                String link = "http://localhost:8080/api/auth/signup/confirm?token=" + tokenForNewUser;
                String email = (String) queryBus
                                .post(new BuildEmailQuery(person.getFirstName(), link));

                commandBus.post(new BuildEmailCommand(person.getEmail(), email));

                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("message",
                                "Votre compte a été créé. Veuillez consulter votre mail afin de confirmer votre compte");
                map.put("confirm", link);

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.CREATED.value(), map),
                                HttpStatus.CREATED);
        }

        @GetMapping(path = "/signup/confirm", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> confirm(@RequestParam("token") String token) {

                ConfirmationToken confirmToken = (ConfirmationToken) queryBus.post(new GetTokenQuery(token));
                Map<String, Object> map = new LinkedHashMap<String, Object>();

                if (confirmToken == null) {
                        map.put("message", "Token not found!");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.NOT_FOUND.value(),
                                                        map),
                                        HttpStatus.NOT_FOUND);
                }

                if (confirmToken.getConfirmedAt() != null) {
                        map.put("message", "Email is already confirmed");

                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.FORBIDDEN.value(),
                                                        map),
                                        HttpStatus.FORBIDDEN);
                }

                LocalDateTime expiresAt = confirmToken.getExpiresAt();

                if (expiresAt.isBefore(LocalDateTime.now())) {

                        map.put("message", "Token is already expired! A new mail would send!");

                        String tokenForNewUser = UUID.randomUUID().toString();

                        commandBus.post(new UpdateTokenCommand(confirmToken, tokenForNewUser));

                        String link = "http://localhost:8080/api/auth/signup/confirm?token=" + tokenForNewUser;

                        String email = (String) queryBus
                                        .post(new BuildEmailQuery(confirmToken.getPerson().getFirstName(), link));

                        commandBus.post(new BuildEmailCommand(confirmToken.getPerson().getEmail(), email));

                        map.put("confirm", link);
                        return new ResponseEntity<>(
                                        new MessageResponse(HttpStatus.FORBIDDEN.value(),
                                                        map),
                                        HttpStatus.FORBIDDEN);
                }

                commandBus.post(new EnablePersonCommand(confirmToken.getPerson()));

                commandBus.post(new SetConfirmedAtCommand(confirmToken));

                map.put("message", "Your email is confirmed. Thank you for using our service!");

                return new ResponseEntity<>(
                                new MessageResponse(HttpStatus.OK.value(),
                                                map),
                                HttpStatus.OK);
        }
}