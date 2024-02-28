package com.ficohsa.item.config.exception;

public enum SPError {
    ITEMS_NOT_FOUND(1001, "No encontro ningun item por ese criterio de busqueda"),

    PARAM_SEARCH_NOT_VALID(1002, "Parametro de busqueda no valido"),

    PARAM_SEARCH_NOT_EMPTY(1003, "Parametro de busqueda no puede estar vacio"),

    ITEM_NOT_FOUND(1004, "El valor no se ha encontrado"),


    INVALID_DATE_FORMAT(1005, "La fecha tiene un formato invalido dd/MM/yyyy"),

    INVALID_PARAMETERS(1006, "Algunos parametros son invalidos");

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
