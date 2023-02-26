package com.esgi.groupe5.architrademe.ArchitrademeApplication;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.LogNotifications;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.EmailService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByNameModalityService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindAllOfferService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByIdOfferService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.RemoveOfferService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.SaveOfferService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByIdPersonProfilService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByIdProfilService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.PersonRegistedEventHandler;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.RegisterPersonProfilService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByNameRoleService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ConfirmationTokenPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.EmailServicePersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ModalityPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.OfferPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.PersonPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ProfilPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.RolePersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.SkillPersistenceAdapter;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ConfirmationTokenRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ModalityRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.OfferRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.ProfilRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.PersonRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.RoleRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output.SkillRepository;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByPersonForConfTokService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.GetTokenService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.SaveConfirmationTokenService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.SetConfirmedAtService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.UpdateTokenService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.BuildEmailService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.EnablePersonService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.ExistsByEmailService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.ExistsByUsernameService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByEmailService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByIdPersonService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByUsernameService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.RegisterPersonService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.DeleteSkillService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByIdSkillAndPersonProfilService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByIdSkillService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByNameSkillService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.FindByPersonProfilSkillService;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services.SaveSkillService;
import com.esgi.groupe5.architrademe.kernel.EventDispatcher;
import com.esgi.groupe5.architrademe.kernel.KernelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@Import(KernelConfiguration.class)
public class ArchitrademeConfiguration {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private ModalityRepository modalityRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private EventDispatcher eventDispatcher;

    @Bean
    public ProfilPersistenceAdapter personProfilPersistenceAdapter() {
        return new ProfilPersistenceAdapter(profilRepository);
    }

    @Bean
    public PersonPersistenceAdapter personPersistenceAdapter() {
        return new PersonPersistenceAdapter(personRepository);
    }

    @Bean
    public ConfirmationTokenPersistenceAdapter confirmationTokenPersistenceAdapter() {
        return new ConfirmationTokenPersistenceAdapter(confirmationTokenRepository);
    }

    @Bean
    public EmailServicePersistenceAdapter emailServicePersistenceAdapter() {
        return new EmailServicePersistenceAdapter();
    }

    @Bean
    public EmailService emailService() {
        return new EmailService(emailServicePersistenceAdapter());
    }

    @Bean
    public ModalityPersistenceAdapter modalityPersistenceAdapter() {
        return new ModalityPersistenceAdapter(modalityRepository);
    }

    @Bean
    public OfferPersistenceAdapter offerPersistenceAdapter() {
        return new OfferPersistenceAdapter(offerRepository);
    }

    @Bean
    public RolePersistenceAdapter rolePersistenceAdapter() {
        return new RolePersistenceAdapter(roleRepository);
    }

    @Bean
    public SkillPersistenceAdapter skillPersistenceAdapter() {
        return new SkillPersistenceAdapter(skillRepository);
    }

    @Bean
    public FindByPersonForConfTokService findByPersonForConfTokService() {
        return new FindByPersonForConfTokService(confirmationTokenPersistenceAdapter());
    }

    @Bean
    public GetTokenService getTokenService() {
        return new GetTokenService(confirmationTokenPersistenceAdapter());
    }

    @Bean
    public SaveConfirmationTokenService saveConfirmationTokenService() {
        return new SaveConfirmationTokenService(confirmationTokenPersistenceAdapter());
    }

    @Bean
    public SetConfirmedAtService setConfirmedAtService() {
        return new SetConfirmedAtService(confirmationTokenPersistenceAdapter());
    }

    @Bean
    public UpdateTokenService updateTokenService() {
        return new UpdateTokenService(confirmationTokenPersistenceAdapter());
    }

    @Bean
    public FindByNameModalityService findByNameModalityService() {
        return new FindByNameModalityService(modalityPersistenceAdapter());
    }

    @Bean
    public FindAllOfferService findAllOfferService() {
        return new FindAllOfferService(offerPersistenceAdapter());
    }

    @Bean
    public FindByIdOfferService findByIdOfferService() {
        return new FindByIdOfferService(offerPersistenceAdapter());
    }

    @Bean
    public RemoveOfferService removeOfferService() {
        return new RemoveOfferService(offerPersistenceAdapter());
    }

    @Bean
    public SaveOfferService saveOfferService() {
        return new SaveOfferService(offerPersistenceAdapter());
    }

    @Bean
    public BuildEmailService buildEmailService() {
        return new BuildEmailService(personPersistenceAdapter());
    }

    @Bean
    public EnablePersonService enablePersonService() {
        return new EnablePersonService(personPersistenceAdapter());
    }

    @Bean
    public ExistsByEmailService existsByEmailService() {
        return new ExistsByEmailService(personPersistenceAdapter());
    }

    @Bean
    public ExistsByUsernameService existsByUsernameService() {
        return new ExistsByUsernameService(personPersistenceAdapter());
    }

    @Bean
    public FindByEmailService findByEmailService() {
        return new FindByEmailService(personPersistenceAdapter());
    }

    @Bean
    public FindByIdPersonService findByIdPersonService() {
        return new FindByIdPersonService(personPersistenceAdapter());
    }

    @Bean
    public FindByUsernameService findByUsernameService() {
        return new FindByUsernameService(personPersistenceAdapter());
    }

    @Bean
    public RegisterPersonService registerPersonService() {
        return new RegisterPersonService(personPersistenceAdapter(), eventDispatcher);
    }

    @Bean
    public FindByIdPersonProfilService findByIdPersonProfilService() {
        return new FindByIdPersonProfilService(personProfilPersistenceAdapter());
    }

    @Bean
    public FindByIdProfilService findByIdProfilService() {
        return new FindByIdProfilService(personProfilPersistenceAdapter());
    }

    @Bean
    public RegisterPersonProfilService registerPersonProfilService() {
        return new RegisterPersonProfilService(personProfilPersistenceAdapter());
    }

    @Bean
    public FindByNameRoleService findByNameRoleService() {
        return new FindByNameRoleService(rolePersistenceAdapter());
    }

    @Bean
    public DeleteSkillService deleteSkillService() {
        return new DeleteSkillService(skillPersistenceAdapter());
    }

    @Bean
    public FindByIdSkillService findByIdSkillService() {
        return new FindByIdSkillService(skillPersistenceAdapter());
    }

    @Bean
    public FindByIdSkillAndPersonProfilService findByIdSkillAndPersonProfilService() {
        return new FindByIdSkillAndPersonProfilService(skillPersistenceAdapter());
    }

    @Bean
    public FindByNameSkillService findByNameSkillService() {
        return new FindByNameSkillService(skillPersistenceAdapter());
    }

    @Bean
    public FindByPersonProfilSkillService findByPersonProfilSkillService() {
        return new FindByPersonProfilSkillService(skillPersistenceAdapter());
    }

    @Bean
    public SaveSkillService saveSkillService() {
        return new SaveSkillService(skillPersistenceAdapter());
    }

    @Bean
    public LogNotifications notifications() {
        return new LogNotifications();
    }

    @Bean
    public PersonRegistedEventHandler personRegistedEventHandler() {
        return new PersonRegistedEventHandler(notifications());
    }
}
