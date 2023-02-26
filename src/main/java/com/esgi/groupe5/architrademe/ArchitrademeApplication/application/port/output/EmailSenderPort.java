package com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output;

public interface EmailSenderPort {

    void sendEmail(String to, String email);
}