package com.xyz.roemahduren.exception;

public class NotFoundException extends RuntimeException {

    public static final String BRANCH_NOT_FOUND = "Branch Not Found";
    public static final String CATEGORY_NOT_FOUND = "Category Not Found";
    public static final String PRODUCT_PRICE_NOT_FOUND = "Product Price Not Found";
    public static final String PRODUCT_NOT_FOUND = "Product Not Found";

    public NotFoundException(String message) {
        super(message);
    }
}
