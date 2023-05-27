package com.xyz.roemahduren.exception;

public class NotFoundException extends RuntimeException {

    public static final String BRANCH_NOT_FOUND = "Cabang tidak ditemukan";
    public static final String CATEGORY_NOT_FOUND = "Kategori tidak ditemukan";
    public static final String PRODUCT_PRICE_NOT_FOUND = "Produk tidak ditemukan";
    public static final String PRODUCT_NOT_FOUND = "Produk tidak ditemukan";
    public static final String PRODUCT_ALREADY_INACTIVATED = "Produk sudah dinonaktifkan";

    public NotFoundException(String message) {
        super(message);
    }
}
