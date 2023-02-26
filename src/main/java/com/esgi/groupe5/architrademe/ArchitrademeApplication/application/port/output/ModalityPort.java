package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ENUMModality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.Modality;
import com.esgi.groupe5.architrademe.ArchitrademeApplication.domain.ModalityId;

public interface ModalityPort {

    ModalityId nextId();

    Modality findByName(ENUMModality name);

    void CreateModality();
}
