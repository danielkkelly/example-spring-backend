package com.danielkkelly.example.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/** Utility Class for argument validation. */
public final class PreconditionsUtil {

    /** Default error message for null Arguments. */
    static final String NULL_MESSAGE = "Argument must not be null.";

    /** Default error message for Not-Empty String Arguments. */
    static final String NOT_EMPTY_MESSAGE = "A non-empty argument is required.";

    /** Private constructor for a utility class. */
    private PreconditionsUtil() {
    }

    /**
     * Convenience for checking that an object is not null with a pre-defined error
     * message.
     * 
     * @param reference the Object to check for null.
     * @param <T>       the checked Object's Type.
     * @return the Object being checked.
     */
    public static <T> T checkNotNull(final T reference) {
        try {
            Preconditions.checkNotNull(reference);
        } catch (NullPointerException npe) {
            throw new NullPointerException(NULL_MESSAGE);
        }
        return reference;
    }

    /**
     * Convenience for checking that an Object is not null.
     * 
     * @param reference the Object to check for null.
     * @param <T>       the Object's Type.
     */
    public static <T> void checkArgument(final T reference) {
        try {
            Preconditions.checkNotNull(reference);
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
    }

    /**
     * Convenience to check an Object for null while allowing for a custom exception
     * message.
     * 
     * @param reference the Object to check for null.
     * @param message   the custom Exception message.
     * @param <T>       the Object's Type.
     */
    public static <T> void checkArgument(final T reference, final String message) {
        try {
            Preconditions.checkNotNull(reference);
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Convenience for checking a single String argument for null or empty. This
     * method will trim the passed String
     * disallowing an argument that contains nothing but white space.
     * 
     * @param argument the String argument to check.
     */
    public static void checkArgument(final String argument) {
        try {
            Preconditions.checkArgument(argument != null && !Strings.isNullOrEmpty(argument.trim()));
        } catch (IllegalArgumentException npe) {
            throw new IllegalArgumentException(NOT_EMPTY_MESSAGE);
        }
    }

    /**
     * Convenience to check a single String argument for Null or Empty which also
     * takes a custom error message. This
     * method will trim the passed String disallowing an argument that contains
     * nothing but white space.
     * 
     * @param argument the String argument to check.
     * @param message  the custom Exception message.
     */
    public static void checkArgument(final String argument, final String message) {
        Preconditions.checkArgument(argument != null && !Strings.isNullOrEmpty(argument.trim()), message);
    }

    /**
     * Verifies that at least one of many arguments is passed.
     * 
     * @param arguments varargs of arguments to check.
     */
    public static void checkArguments(final String... arguments) {
        boolean allMissing = true;
        for (String argument : arguments) {
            if (argument != null && !Strings.isNullOrEmpty(argument.trim())) {
                allMissing = false;
            }
        }
        if (allMissing) {
            throw new IllegalArgumentException(NOT_EMPTY_MESSAGE);
        }
    }
}