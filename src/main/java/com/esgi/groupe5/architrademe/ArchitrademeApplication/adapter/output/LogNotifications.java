package com.esgi.groupe5.architrademe.ArchitrademeApplication.adapter.output;

import com.esgi.groupe5.architrademe.ArchitrademeApplication.application.port.output.Notifications;

public final class LogNotifications implements Notifications {
    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
