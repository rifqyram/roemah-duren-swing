package com.xyz.roemahduren.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomGenerator {

    private static String lastDate = "";
    private static int sequence = 0;

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generateInvoiceNumber() {
        String currentDate = getCurrentDate();

        if (!currentDate.equals(lastDate)) {
            sequence = 0;
            lastDate = currentDate;
        }

        sequence++;
        String formattedSequence = String.format("%03d", sequence); // format sequence menjadi tiga digit dengan leading zero
        return "INV-" + currentDate + "-" + formattedSequence;
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
