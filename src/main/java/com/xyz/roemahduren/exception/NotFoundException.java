package com.xyz.roemahduren.exception;

public class NotFoundException extends RuntimeException {

    public static final String BRANCH_NOT_FOUND = "Cabang tidak ditemukan";
    public static final String CATEGORY_NOT_FOUND = "Kategori tidak ditemukan";
    public static final String PRODUCT_PRICE_NOT_FOUND = "Produk tidak ditemukan";
    public static final String PRODUCT_NOT_FOUND = "Produk tidak ditemukan";
    public static final String PRODUCT_ALREADY_INACTIVATED = "Produk sudah dinonaktifkan";
    public static final String STATUS_ORDER_NOT_FOUND = "Status Order tidak ditemukan";
    public static final String ORDER_NOT_FOUND = "Order tidak ditemukan";
    public static final String CUSTOMER_NOT_FOUND = "Customer tidak ditemukan";
    public static final String SUPPLIER_NOT_FOUND = "Pemasok tidak ditemukan";
    public static final String SUPPLIER_PRODUCT_NOT_FOUND = "Produk Pemasok tidak ditemukan";

    public NotFoundException(String message) {
        super(message);
    }
}
