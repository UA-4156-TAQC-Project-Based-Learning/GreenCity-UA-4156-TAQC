package com.greencity.utils;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class FileNameNormalizer {

    // Regex to match invalid characters for Windows filenames
    // Includes < > : " / \ | ? * and control characters (0-31)
    private static final Pattern ILLEGAL_CHARACTERS = Pattern.compile("[<>:\"/\\\\|?*\\x00-\\x1F]");

    // Reserved Windows filenames (case-insensitive)
    private static final String[] RESERVED_NAMES = {
            "CON", "PRN", "AUX", "NUL",
            "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9",
            "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"
    };

    /**
     * Normalizes a string to be a safe filename for Windows.
     *
     * @param originalFileName The original string.
     * @return A sanitized filename.
     */
    public static String normalizeFileName(String originalFileName) {
        if (originalFileName == null || originalFileName.trim().isEmpty()) {
            return "untitled_file"; // Provide a default name
        }

        // 1. Unicode Normalization (Optional but good practice for robustness)
        // This converts characters to a canonical form (e.g., accented characters)
        // NFKC is often good for compatibility.
        String normalized = Normalizer.normalize(originalFileName, Normalizer.Form.NFKC);

        // 2. Replace invalid characters with an underscore
        String sanitized = ILLEGAL_CHARACTERS.matcher(normalized).replaceAll("_");

        // 3. Trim leading/trailing whitespace
        sanitized = sanitized.trim();

        // 4. Remove trailing periods and spaces (Windows specific)
        while (sanitized.endsWith(".") || sanitized.endsWith(" ")) {
            sanitized = sanitized.substring(0, sanitized.length() - 1);
        }

        // 5. Handle reserved names
        String upperCaseSanitized = sanitized.toUpperCase();
        for (String reservedName : RESERVED_NAMES) {
            if (upperCaseSanitized.equals(reservedName) || upperCaseSanitized.startsWith(reservedName + ".")) {
                sanitized = "_" + sanitized; // Prepend to avoid collision
                break;
            }
        }

        // 6. Ensure it's not empty after sanitization
        if (sanitized.isEmpty()) {
            return "unnamed_file";
        }

        // 7. Optional: Truncate length
        // Windows MAX_PATH is 260 characters, but filename itself should be shorter.
        // A common practical limit for filenames is 255 bytes (not characters, due to UTF-8 encoding)
        // or a simpler character limit. Let's use a character limit for simplicity here.
        int maxFileNameLength = 200; // Leaving room for path and potential extension
        if (sanitized.length() > maxFileNameLength) {
            // Keep a part of the original name and potentially the extension
            int dotIndex = sanitized.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex > sanitized.length() - (maxFileNameLength / 4)) { // Don't cut off very long extensions
                String namePart = sanitized.substring(0, maxFileNameLength - (sanitized.length() - dotIndex));
                String extensionPart = sanitized.substring(dotIndex);
                sanitized = namePart + extensionPart;
            } else {
                sanitized = sanitized.substring(0, maxFileNameLength);
            }
        }

        return sanitized;
    }
}