package com.esgi.groupe5.architrademe.kernel;

public interface Event {
    default String name() {
        return getClass().getSimpleName();
    }
}
