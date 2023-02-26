package com.esgi.groupe5.architrademe.kernel;

public interface Command {
    default String name() {
        return this.getClass().getSimpleName();
    }
}
