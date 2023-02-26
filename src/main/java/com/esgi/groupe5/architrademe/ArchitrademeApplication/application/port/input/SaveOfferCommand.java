package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.input;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Offer;
import com.esgi.groupe5.architrademe.kernel.Command;

public final class SaveOfferCommand implements Command{
    public  final Offer offer;

    public SaveOfferCommand(Offer offer) {
        this.offer = offer;
    }

    
}
