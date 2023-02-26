package com.esgi.groupe5.architrademe.ArchitrademeApplication;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.events.PersonRegistedEvent;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameModalityQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindAllOfferQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdOfferQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RemoveOfferCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveOfferCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonProfilCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameRoleQuery;
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
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByEmailQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdPersonQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByUsernameQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.DeleteSkillCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdSkillAndPersonProfilQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByIdSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByNameSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.FindByPersonProfilSkillQuery;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveSkillCommand;
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
import com.esgi.groupe5.architrademe.kernel.CommandBus;
import com.esgi.groupe5.architrademe.kernel.EventDispatcher;
import com.esgi.groupe5.architrademe.kernel.QueryBus;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("all")
public class StartupArchitrademeListener implements ApplicationListener<ContextRefreshedEvent> {

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final FindByPersonForConfTokService findByPersonForConfTokService;
    private final GetTokenService getTokenService;
    private final SaveConfirmationTokenService saveConfirmationTokenService;
    private final SetConfirmedAtService setConfirmedAtService;
    private final UpdateTokenService updateTokenService;
    private final FindByNameModalityService findByNameModalityService;
    private final FindAllOfferService findAllOfferService;
    private final FindByIdOfferService findByIdOfferService;
    private final RemoveOfferService removeOfferService;
    private final SaveOfferService saveOfferService;
    private final BuildEmailService buildEmailService;
    private final EnablePersonService enablePersonService;
    private final ExistsByEmailService existsByEmailService;
    private final ExistsByUsernameService existsByUsernameService;
    private final FindByEmailService findByEmailService;
    private final FindByIdPersonService findByIdPersonService;
    private final FindByUsernameService findByUsernameService;
    private final RegisterPersonService registerPersonService;
    private final FindByIdPersonProfilService findByIdPersonProfilService;
    private final FindByIdProfilService findByIdProfilService;
    private final RegisterPersonProfilService registerPersonProfilService;
    private final FindByNameRoleService findByNameRoleService;
    private final DeleteSkillService deleteSkillService;
    private final FindByIdSkillService findByIdSkillService;
    private final FindByNameSkillService findByNameSkillService;
    private final FindByPersonProfilSkillService findByPersonProfilSkillService;
    private final FindByIdSkillAndPersonProfilService findByIdSkillAndPersonProfilService;
    private final SaveSkillService saveSkillService;
    private final EmailService emailService;

    //event
    private final EventDispatcher eventDispatcher;
    private final PersonRegistedEventHandler personRegistedEventHandler;

  
    public StartupArchitrademeListener(CommandBus commandBus, QueryBus queryBus,
                                       FindByPersonForConfTokService findByPersonForConfTokService, GetTokenService getTokenService,
                                       SaveConfirmationTokenService saveConfirmationTokenService, SetConfirmedAtService setConfirmedAtService,
                                       UpdateTokenService updateTokenService, FindByNameModalityService findByNameModalityService,
                                       FindAllOfferService findAllOfferService, FindByIdOfferService findByIdOfferService,
                                       RemoveOfferService removeOfferService, SaveOfferService saveOfferService,
                                       BuildEmailService buildEmailService, EnablePersonService enablePersonService,
                                       ExistsByEmailService existsByEmailService, ExistsByUsernameService existsByUsernameService,
                                       FindByEmailService findByEmailService, FindByIdPersonService findByIdPersonService,
                                       FindByUsernameService findByUsernameService, RegisterPersonService registerPersonService,
                                       FindByIdPersonProfilService findByIdPersonProfilService, FindByIdProfilService findByIdProfilService,
                                       RegisterPersonProfilService registerPersonProfilService, FindByNameRoleService findByNameRoleService,
                                       DeleteSkillService deleteSkillService, FindByIdSkillService findByIdSkillService,
                                       FindByNameSkillService findByNameSkillService,
                                       FindByPersonProfilSkillService findByPersonProfilSkillService,
                                       FindByIdSkillAndPersonProfilService findByIdSkillAndPersonProfilService, SaveSkillService saveSkillService,
                                       EmailService emailService, EventDispatcher eventDispatcher,
                                       PersonRegistedEventHandler personRegistedEventHandler) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.findByPersonForConfTokService = findByPersonForConfTokService;
        this.getTokenService = getTokenService;
        this.saveConfirmationTokenService = saveConfirmationTokenService;
        this.setConfirmedAtService = setConfirmedAtService;
        this.updateTokenService = updateTokenService;
        this.findByNameModalityService = findByNameModalityService;
        this.findAllOfferService = findAllOfferService;
        this.findByIdOfferService = findByIdOfferService;
        this.removeOfferService = removeOfferService;
        this.saveOfferService = saveOfferService;
        this.buildEmailService = buildEmailService;
        this.enablePersonService = enablePersonService;
        this.existsByEmailService = existsByEmailService;
        this.existsByUsernameService = existsByUsernameService;
        this.findByEmailService = findByEmailService;
        this.findByIdPersonService = findByIdPersonService;
        this.findByUsernameService = findByUsernameService;
        this.registerPersonService = registerPersonService;
        this.findByIdPersonProfilService = findByIdPersonProfilService;
        this.findByIdProfilService = findByIdProfilService;
        this.registerPersonProfilService = registerPersonProfilService;
        this.findByNameRoleService = findByNameRoleService;
        this.deleteSkillService = deleteSkillService;
        this.findByIdSkillService = findByIdSkillService;
        this.findByNameSkillService = findByNameSkillService;
        this.findByPersonProfilSkillService = findByPersonProfilSkillService;
        this.findByIdSkillAndPersonProfilService = findByIdSkillAndPersonProfilService;
        this.saveSkillService = saveSkillService;
        this.emailService = emailService;
        this.eventDispatcher = eventDispatcher;
        this.personRegistedEventHandler = personRegistedEventHandler;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //event dispatcher
        eventDispatcher.register(PersonRegistedEvent.class, personRegistedEventHandler);

        commandBus.register(BuildEmailCommand.class, emailService);
        queryBus.register(FindByPersonForConfTokQuery.class, findByPersonForConfTokService);
        queryBus.register(GetTokenQuery.class, getTokenService);
        commandBus.register(SaveConfirmationTokenCommand.class, saveConfirmationTokenService);
        commandBus.register(SetConfirmedAtCommand.class, setConfirmedAtService);
        commandBus.register(UpdateTokenCommand.class, updateTokenService);
        queryBus.register(FindByNameModalityQuery.class, findByNameModalityService);
        queryBus.register(FindAllOfferQuery.class, findAllOfferService);
        queryBus.register(FindByIdOfferQuery.class, findByIdOfferService);
        commandBus.register(RemoveOfferCommand.class, removeOfferService);
        commandBus.register(SaveOfferCommand.class, saveOfferService);
        queryBus.register(BuildEmailQuery.class, buildEmailService);
        commandBus.register(EnablePersonCommand.class, enablePersonService);
        queryBus.register(ExistsByEmailQuery.class, existsByEmailService);
        queryBus.register(ExistsByUsernameQuery.class, existsByUsernameService);
        queryBus.register(FindByEmailQuery.class, findByEmailService);
        queryBus.register(FindByIdPersonQuery.class, findByIdPersonService);
        queryBus.register(FindByUsernameQuery.class, findByUsernameService);
        commandBus.register(RegisterPersonCommand.class, registerPersonService);
        queryBus.register(FindByIdPersonProfilQuery.class, findByIdPersonProfilService);
        queryBus.register(FindByIdProfilQuery.class, findByIdProfilService);
        commandBus.register(RegisterPersonProfilCommand.class, registerPersonProfilService);
        queryBus.register(FindByNameRoleQuery.class, findByNameRoleService);
        commandBus.register(DeleteSkillCommand.class, deleteSkillService);
        queryBus.register(FindByIdSkillQuery.class, findByIdSkillService);
        queryBus.register(FindByNameSkillQuery.class, findByNameSkillService);
        queryBus.register(FindByPersonProfilSkillQuery.class, findByPersonProfilSkillService);
        queryBus.register(FindByIdSkillAndPersonProfilQuery.class, findByIdSkillAndPersonProfilService);
        commandBus.register(SaveSkillCommand.class, saveSkillService);
    }
}
