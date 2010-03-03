package com.thoughtworks.teach.bank.util;

public interface Maybe<T> {
    T force();

    boolean hasValue();
}