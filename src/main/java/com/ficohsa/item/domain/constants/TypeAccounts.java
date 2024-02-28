package com.ficohsa.item.domain.constants;

public enum TypeAccounts {

    SAVINGS_ACCOUNT("SAVINGS_ACCOUNT"),

    CHECKING_ACCOUNT("CHECKING_ACCOUNT");

    private final String typeAccount;


    TypeAccounts(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getTypeAccount() {
        return typeAccount;
    }
}
