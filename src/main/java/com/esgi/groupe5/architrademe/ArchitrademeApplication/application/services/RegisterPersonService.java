package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.events.PersonRegistedEvent;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RegisterPersonCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.PersonPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;
import com.esgi.groupe5.architrademe.kernel.Event;
import com.esgi.groupe5.architrademe.kernel.EventDispatcher;

public final class RegisterPersonService implements CommandHandler<RegisterPersonCommand, Void> {
    private final PersonPort personPort;
    private final EventDispatcher<? super Event> eventDispatcher;

    public RegisterPersonService(PersonPort personPort, EventDispatcher<? super Event> eventDispatcher) {
        this.personPort = personPort;
        this.eventDispatcher = eventDispatcher;

    }

    @Override
    public Void handle(RegisterPersonCommand command) {
        personPort.register(command.person);
        eventDispatcher.dispatch(new PersonRegistedEvent(command.person));

        return null;
    }

}
