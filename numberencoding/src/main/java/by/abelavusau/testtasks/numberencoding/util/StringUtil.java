package by.abelavusau.testtasks.numberencoding.util;

import by.abelavusau.testtasks.numberencoding.constants.StringPool;

/**
 * Provides methods for manipulation of strings
 * 
 * @author Aliaksandr Belavusau
 */
public final class StringUtil {
    private StringUtil() {
        throw new AssertionError("Utility class constructor should be private");
    }

    /**
     * Cleans the string. Removes all dash and slash characters.
     * 
     * @param number - string to normalize
     * @return normalized string
     */
    public static String normalizeNumber(String value) {
        return value.replaceAll(StringPool.DASH, StringPool.EMPTY).replaceAll(StringPool.SLASH, StringPool.EMPTY).trim();
    }

    /**
     * Copies the specified string
     * 
     * @param source - original the string to be copied
     * @return a copy of the original string
     */
    public static String copyString(String source) {
        return new String(source);
    }
}
