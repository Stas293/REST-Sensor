package org.springapp.projectrest.utils;

import org.springframework.validation.BindingResult;

import java.util.Optional;

public class ExceptionParser {
    private ExceptionParser() {
    }

    public static Optional<String> parseBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getCode())
                .reduce((s, s2) -> s + ", " + s2);
    }
}
