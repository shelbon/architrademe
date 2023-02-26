package com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.exception;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.OfferId;

public class OfferException extends RuntimeException {
    private OfferException(String message) {
        super(message);
    }

    public static OfferException notFoundOfferId(OfferId offerId) {
        return new OfferException(String.format("%s not found.", offerId.getValue()));
    }
}
