package com.esgi.groupe5.architrademe.kernel;

public interface EventHandler<E extends Event> {
    void handle(E event);
}
