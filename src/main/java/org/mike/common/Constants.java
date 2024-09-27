package org.mike.common;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public final class Constants {
//    public static final int COMMON_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
//    public static final int COMMON_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    public static final int COMMON_WIDTH = (Toolkit.getDefaultToolkit().getScreenSize().width / 3) * 2;
    public static final int COMMON_HEIGHT = (Toolkit.getDefaultToolkit().getScreenSize().height / 3) * 2;

    public static final int WIDTH_DIVIDE_FACTOR = 4;
    public static final int HEIGHT_DIVIDE_FACTOR = 8;
    public static final int RIGHT_COMPONENTS_WIDTH = ((COMMON_WIDTH / WIDTH_DIVIDE_FACTOR) * (WIDTH_DIVIDE_FACTOR - 1)) ;
    public static final int LEFT_COMPONENTS_WIDTH = COMMON_WIDTH / WIDTH_DIVIDE_FACTOR;

    public static final String VERSION = "TEST-BUILD 0.0.1.2a";
    public static final Path USER_DIR = Paths.get(System.getProperty("user.home")).normalize();
    public static final Path CHAT_PATH = Paths.get(USER_DIR + "/JavaChat").normalize();
    public static final Path CONTACT_MESSAGES_PATH =  Paths.get(CHAT_PATH + "/Messages/").normalize();
    public static final int COMMUNICATION_PORT = 59840;
    public static final int PICTURE_PORT = 59841;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
}
