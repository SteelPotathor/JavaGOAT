package com.example.javagoat.Model;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Objects;

/**
 * Loads images without depending on the working directory.
 * <p>
 * UI icons come from the classpath. Profile pictures are stored as a reference string in the data files:
 * either {@link #DEFAULT_PROFILE_IMAGE}, a legacy "file:src/main/resources/.../DefaultImage.png" path, or the
 * URL of a picture chosen by the user. Anything that cannot be loaded falls back to the default picture.
 */
public final class ImageLoader {

    /** Reference stored in a profile that has no custom picture. */
    public static final String DEFAULT_PROFILE_IMAGE = "default";

    private static final String RESOURCE_ROOT = "/com/example/javagoat/";
    private static Image defaultProfileImage;

    private ImageLoader() {
    }

    /** Loads an image shipped in src/main/resources/com/example/javagoat (e.g. "GlobalImages/aimer.png"). */
    public static Image resource(String relativePath) {
        URL url = Objects.requireNonNull(ImageLoader.class.getResource(RESOURCE_ROOT + relativePath),
                "Missing resource: " + RESOURCE_ROOT + relativePath);
        return new Image(url.toExternalForm());
    }

    public static boolean isDefaultReference(String reference) {
        return reference == null || reference.isBlank()
                || DEFAULT_PROFILE_IMAGE.equals(reference)
                || reference.endsWith("GlobalImages/DefaultImage.png");
    }

    /** Loads a profile picture, or the default picture if the reference is empty, unknown or unreadable. */
    public static Image profileImage(String reference) {
        if (isDefaultReference(reference)) {
            return defaultProfileImage();
        }
        try {
            Image image = new Image(reference);
            if (!image.isError()) {
                return image;
            }
            System.err.println("[JavaGOAT] Cannot load profile picture '" + reference + "': " + image.getException());
        } catch (IllegalArgumentException e) {
            System.err.println("[JavaGOAT] Invalid profile picture reference '" + reference + "': " + e.getMessage());
        }
        return defaultProfileImage();
    }

    private static Image defaultProfileImage() {
        if (defaultProfileImage == null) {
            defaultProfileImage = resource("GlobalImages/DefaultImage.png");
        }
        return defaultProfileImage;
    }
}
