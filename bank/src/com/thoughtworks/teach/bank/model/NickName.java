package com.thoughtworks.teach.bank.model;

import java.util.regex.Pattern;

public class NickName {
    private final String nickName;

    public NickName(String nickName) {
        checkValidity(nickName);
        this.nickName = nickName;
    }

    public String toString() {
        return nickName;
    }

    public boolean equals(Object o) {
        if (!(o instanceof NickName)) {
            return false;
        }
        NickName otherNickName = (NickName) o;
        return nickName.equals(otherNickName.nickName);
    }

    public int hashCode() {
        return (nickName != null ? nickName.hashCode() : 0);
    }

    private void checkValidity(String nickName) {
        if (nickName == null) {
            throw new IllegalArgumentException("NickName cannot be null");
        }
        if (!isJustLowerCaseLettersAndNumbers(nickName)) {
            throw new IllegalArgumentException("Nicknames must consist of just numbers and lowercase letters. This is not on: " + nickName);
        }
    }

    private boolean isJustLowerCaseLettersAndNumbers(String name) {
        return Pattern.matches("[a-z0-9]+", name);
    }
}
