package com.thoughtworks.teach.bank.util;

public class Just<T> implements Maybe<T> {
    private final T value;

    public Just(T value) {
        this.value = value;
    }

    public T force() {
        return value;
    }

    public boolean hasValue() {
        return true;
    }
}