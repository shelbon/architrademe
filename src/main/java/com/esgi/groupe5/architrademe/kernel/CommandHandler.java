package com.esgi.groupe5.architrademe.kernel;

public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
