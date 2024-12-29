package org.mike.common;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public final class Constants {
    public static final int FRAME_WIDTH_DIVIDE_FACTOR = 2;
    public static final int FRAME_HEIGHT_DIVIDE_FACTOR = 2;
    public static final int FRAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / FRAME_WIDTH_DIVIDE_FACTOR;
    public static final int FRAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / FRAME_HEIGHT_DIVIDE_FACTOR;

    public static final int VIEW_WIDTH_DIVIDE_FACTOR = 4;

    public static final int LEFT_VIEW_WIDTH = FRAME_WIDTH / VIEW_WIDTH_DIVIDE_FACTOR;
    public static final int RIGHT_VIEW_WIDTH = (FRAME_WIDTH / VIEW_WIDTH_DIVIDE_FACTOR) * (VIEW_WIDTH_DIVIDE_FACTOR - 1);
    public static final int LEFT_VIEW_HEIGHT_DIVIDE_FACTOR = 6;
    public static final int RIGHT_VIEW_HEIGHT_DIVIDE_FACTOR = 6;

    public static final String VERSION = "TEST-BUILD 0.0.1.2a";
    public static final Path USER_DIR = Paths.get(System.getProperty("user.home")).normalize();
    public static final Path CHAT_PATH = Paths.get(USER_DIR + "/JavaChat").normalize();
    public static final Path CONTACT_MESSAGES_PATH =  Paths.get(CHAT_PATH + "/Messages/").normalize();
    public static final int COMMUNICATION_PORT = 59840;
    public static final int PICTURE_PORT = 59841;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
}
