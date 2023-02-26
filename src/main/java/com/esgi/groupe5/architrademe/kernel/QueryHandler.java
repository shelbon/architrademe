package com.esgi.groupe5.architrademe.kernel;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
