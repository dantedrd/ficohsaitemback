package com.ficohsa.item.domain.constants;

public enum TypeTransfer {
    MOVEMENT("MOVEMENT"),

    CONSIGNMENT("CONSIGNMENT"),

    WITHDRAWAL("WITHDRAWAL");

    private final String typeTransfer;


    TypeTransfer(String typeAccount) {
        this.typeTransfer = typeAccount;
    }

    public String getTypeTransfer() {
        return typeTransfer;
    }
}
