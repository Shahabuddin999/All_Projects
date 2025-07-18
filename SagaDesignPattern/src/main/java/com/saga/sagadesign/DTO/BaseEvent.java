package com.saga.sagadesign.DTO;
public abstract class BaseEvent<T> {
    private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
