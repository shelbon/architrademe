package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.RemoveOfferCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.OfferPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class RemoveOfferService implements CommandHandler<RemoveOfferCommand, Void> {
    final OfferPort offerPort;

    public RemoveOfferService(OfferPort offerPort) {
        this.offerPort = offerPort;
    }

    @Override
    public Void handle(RemoveOfferCommand command) {
        offerPort.remove(command.offer);
        return null;
    }

}
