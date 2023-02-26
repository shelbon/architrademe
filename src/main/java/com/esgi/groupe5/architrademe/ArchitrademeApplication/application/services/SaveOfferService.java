package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.services;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input.SaveOfferCommand;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.OfferPort;
import com.esgi.groupe5.architrademe.kernel.CommandHandler;

public final class SaveOfferService implements CommandHandler<SaveOfferCommand, Void> {
    final OfferPort offerPort;

    public SaveOfferService(OfferPort offerPort) {
        this.offerPort = offerPort;
    }

    @Override
    public Void handle(SaveOfferCommand command) {
        offerPort.save(command.offer);
        return null;
    }

}
