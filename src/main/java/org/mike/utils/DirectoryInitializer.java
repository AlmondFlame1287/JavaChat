package org.mike.utils;

import org.mike.common.Constants;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DirectoryInitializer {
    private DirectoryInitializer() {}

    // Use reflection to get fields
    private static ArrayList<String> selectFields() {
        final Pattern pattern = Pattern.compile("[0-9]");
        final Field[] fields = Constants.class.getDeclaredFields();
        final ArrayList<String> result = new ArrayList<>();

        try {
            for(Field field : fields) {
                if(!(field.get(field.getName()) instanceof String)) continue;

                final String dirToProcess = (String) field.get(field.getName());
                final Matcher matcher = pattern.matcher(dirToProcess);

                if(matcher.find()) continue;
                result.add(dirToProcess);
            }
        } catch (IllegalAccessException iae) {
            System.err.println("Problem: " + iae.getMessage());
        }

        return result;
    }

    public static void initializeDirectories() {
        final String[] dirs = selectFields().toArray(new String[0]);

        for(final String dir : dirs) {
            final Path path = Paths.get(dir).normalize();
            new File(String.valueOf(path)).mkdirs();
        }
    }
}
