package com.ficohsa.item.config.exception;

public enum SPError {
    ITEMS_NOT_FOUND(1001, "No encontro ningun item por ese criterio de busqueda"),

    PARAM_SEARCH_NOT_VALID(1002, "Parametro de busqueda no valido"),

    PARAM_SEARCH_NOT_EMPTY(1003, "Parametro de busqueda no puede estar vacio"),

    ITEM_NOT_FOUND(1004, "El valor no se ha encontrado"),
    INVALID_CLIENT_HAS_PRODUCTS(1002, "El cliente tiene productos"),

    INVALID_DATE_FORMAT(1003, "La fecha tiene un formato invalido dd/MM/yyyy"),

    INVALID_PARAMETERS(1004, "Algunos parametros son invalidos"),

    INVALID_ACCOUNT_AMOUNT(1005, "la cuenta no puede tener una saldo menor a $0"),

    INVALID_USER_NOT_EXIST(1006, "El usuario no exista"),

    TYPE_ACCOUNT_NOT_VALID(1007, "el tipo de cuenta no es valido"),

    TYPE_TRANSFER_NOT_VALID(1008, "el tipo de cuenta no es valido"),

    STATE_ACCOUNT_NOT_VALID(1009, "el estado de la cuenta no es valido"),

    NOT_FOUND_ACCOUNT(1010, "La cuenta no existe"),

    INVALID_AGE_CLIENT(1010, "La cuenta no existe"),

    ERROR_DELETE_USER(1010, "Ocurrio un error eliminando el cliente"),

    ERROR_DELETE_ACCOUNT(1010, "Ocurrio un error eliminando la cuenta"),

    ACCOUNT_NOT_ACTIVE(1011, "no se puede hacer el procedimiento ya que la cuenta esta inactiva"),
    VALUE_IS_UPPER_TO_VALUE_ACCOUNT(1012, "El valor es superior a lo que tiene la cuenta");

    private final int errorCode;
    private final String errorMessage;

    /**
     * Constructs an SPError enum value with the specified error code and message.
     *
     * @param errorCode    The unique code associated with the error.
     * @param errorMessage The human-readable message describing the error.
     */
    SPError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Retrieves the error code of this error.
     *
     * @return The integer code representing the specific error.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Retrieves the error message of this error.
     *
     * @return The string message associated with the error.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
