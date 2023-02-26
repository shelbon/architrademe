package com.esgi.groupe5.architrademe.kernel;

public interface Query {
    default String name() {
        return this.getClass().getSimpleName();
    }
}
