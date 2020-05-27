package com.shopping.calculator.exceptions;

public class NoShoppingItemFoundException extends Exception {

    private static final long serialVersionUID = 1563230176159690217L;

    public NoShoppingItemFoundException(String msg) {
        super(msg);
    }

}
