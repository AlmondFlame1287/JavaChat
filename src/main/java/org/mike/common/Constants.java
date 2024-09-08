package org.mike.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {
    public static final String VERSION = "0.0.2a";
    public static final Path USER_DIR = Paths.get(System.getProperty("user.home")).normalize();
    public static final Path CHAT_PATH = Paths.get(USER_DIR + "/JavaChat").normalize();
    public static final Path CONTACT_MESSAGES_PATH =  Paths.get(CHAT_PATH + "/Messages/").normalize();
    public static final int COMMUNICATION_PORT = 59840;
    public static final int PICTURE_PORT = 59841;
}
