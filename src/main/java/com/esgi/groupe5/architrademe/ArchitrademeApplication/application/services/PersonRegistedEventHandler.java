package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.events.PersonRegistedEvent;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.Notifications;
import com.esgi.groupe5.architrademe.kernel.EventHandler;

public class PersonRegistedEventHandler implements EventHandler<PersonRegistedEvent> {
    private final Notifications notifications;

    public PersonRegistedEventHandler(Notifications notifications) {
        this.notifications = notifications;
    }

    @Override
    public void handle(PersonRegistedEvent event) {
        notifications
                .notify(String.format("Notification of the account creation %s with name: %s , email: %s and phone: %s",
                        event.getPerson().getPersonId().value(),
                        event.getPerson().getFirstName()+" "+ event.getPerson().getLastName(),
                         event.getPerson().getEmail(),
                        event.getPerson().getPhone()));
    }
}
