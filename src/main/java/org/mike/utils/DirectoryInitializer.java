package org.mike.utils;

import org.mike.common.Constants;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.logging.Logger;

public final class DirectoryInitializer {
    private static final Logger directoryInitLogger = Logger.getLogger("DirectoryInitializerLogger");
    private DirectoryInitializer() {}

    // Use reflection to get fields
    public static void initializeDirectories() {
        final Field[] classFields = Constants.class.getDeclaredFields();

        try {
            for(Field field : classFields) {
                if(!(field.get(field.getName()) instanceof Path)) continue;

                final Path pathToInit = (Path) field.get(field.getName());
                new File(pathToInit.toString()).mkdirs();
                directoryInitLogger.info("Path initialized: " + pathToInit);
            }
        } catch (IllegalAccessException iae) {
            directoryInitLogger.severe("There was a problem with the directory initialization: " + iae.getMessage());
        }
    }
}
