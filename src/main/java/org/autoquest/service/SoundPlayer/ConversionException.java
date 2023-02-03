package org.autoquest.service.SoundPlayer;

public class ConversionException extends RuntimeException {
    public ConversionException(Throwable cause) {
        super("Failed to convert audio data", cause);
    }
}
