package com.xyz.roemahduren.constant;

import java.net.URL;

public class NotificationResource {
    public static final String SUCCESS = "Success";
    public static final String FAIL = "Error";
    public static final String WARNING = "Warning";
    public static final String INFO = "Info";

    public static final URL SUCCESS_IMAGE = NotificationResource.class.getResource("/images/Success.png");
    public static final URL FAIL_IMAGE = NotificationResource.class.getResource("/images/Danger.png");
    public static final URL WARNING_IMAGE = NotificationResource.class.getResource("/images/Warning.png");
    public static final URL INFO_IMAGE = NotificationResource.class.getResource("/images/Info.png");

}
