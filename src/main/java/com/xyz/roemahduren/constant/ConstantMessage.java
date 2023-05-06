package com.xyz.roemahduren.constant;

public class ConstantMessage {

    public static final String BRANCH = "Branch";
    public static final String PRODUCT = "Product";
    public static final String CATEGORY = "Category";
    public static final String CUSTOMER = "Customer";
    public static final String ORDER = "Order";
    public static final String ORDER_DETAIL = "Order Detail";
    public static final String USER = "User";

    public static String getSuccessCreatedMessage(String title) {
        return String.format("Successfully create new %s", title);
    }

    public static String getSuccessUpdateMessage(String title) {
        return String.format("Successfully update %s", title);
    }

    public static String getSuccessDeleteMessage(String title) {
        return String.format("Successfully delete %s", title);
    }

    public static String getInternalErrorMessage(String message) {
        return String.format("Internal Server Error: %s", message);
    }

}
