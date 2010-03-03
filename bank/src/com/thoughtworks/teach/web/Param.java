package com.thoughtworks.teach.web;

public class Param {
    public final String name;
    public final Object value;

    public Param(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return name + "=" + value;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Param param = (Param) o;
        return name.equals(param.name) && value.equals(param.value);
    }

    public int hashCode() {
        int result;
        result = name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
