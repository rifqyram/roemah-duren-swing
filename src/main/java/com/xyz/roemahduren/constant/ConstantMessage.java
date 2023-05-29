package com.xyz.roemahduren.constant;

public class ConstantMessage {

    public static final String BRANCH = "Cabang";
    public static final String PRODUCT = "Produk";
    public static final String CATEGORY = "Kategori";
    public static final String CUSTOMER = "Pelanggan";
    public static final String ORDER = "Pesan";
    public static final String ORDER_DETAIL = "Detil Pesanan";
    public static final String USER = "User";
    public static final String LOGIN_SUCCESS_MESSAGE = "Anda berhasil Login";
    public static final String REGISTER = "Anda berhasil Mendaftar";

    public static final String SUCCESS_ADD_ORDER_DETAIL = "Berhasil tambahkan produk baru ke keranjang";
    public static final String SUCCESS_REMOVE_ORDER_DETAIL = "Berhasil menghapus produk di keranjang";

    public static final String BTN_TEXT_SAVE = "Simpan";
    public static final String BTN_TEXT_UPDATE = "Ubah";

    public static String getSuccessCreatedMessage(String title) {
        return String.format("Berhasil membuat %s baru", title);
    }

    public static String getSuccessUpdateMessage(String title) {
        return String.format("Berhasil ubah %s", title);
    }

    public static String getSuccessDeleteMessage(String title) {
        return String.format("Berhasil hapus %s", title);
    }

    public static String getInternalErrorMessage(String message) {
        return String.format("Error: %s", message);
    }

}
