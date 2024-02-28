package com.ficohsa.item.domain.constants;

public enum StateAccount {
    ACTIVE("ACTIVE"),

    INACTIVE("INACTIVE"),

    CANCELLED("Cancelled");

    private final String stateAccount;


    StateAccount(String stateAccount) {
        this.stateAccount = stateAccount;
    }

    public String getStateAccount() {
        return stateAccount;
    }
}
