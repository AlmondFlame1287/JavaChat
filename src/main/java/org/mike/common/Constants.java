package org.mike.common;

public record Constants() {
    public static final String USER_DIR = System.getProperty("user.home");
    public static final String CHAT_PATH = USER_DIR + "/JavaChat/";
    public static final String CONTACT_MESSAGES_PATH =  CHAT_PATH + "/Messages/";
}
