package com.thoughtworks.teach.bank.util;

public class Nothing<T> implements Maybe<T> {
    public T force() {
        throw new Exception();
    }

    public boolean hasValue() {
        return false;
    }

    @SuppressWarnings({"EmptyClass"})
    public static class Exception extends RuntimeException {
    }
}