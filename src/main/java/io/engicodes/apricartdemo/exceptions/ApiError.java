package io.engicodes.apricartdemo.exceptions;

import java.time.LocalDateTime;

public record ApiError(
        int statusCode,
        String responseFailedMessage,
        LocalDateTime localDateTime
) {
}
