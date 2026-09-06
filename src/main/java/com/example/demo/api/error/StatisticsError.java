package com.example.demo.api.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatisticsError implements BackofficeError{
    INVALID_FILTERS(200, "Year from or year to should not be null"),
    INVALID_YEAR(201, "Year should be in the past"),
    INVALID_YEAR_RANGE(202, "Year from should be less than year to");

    private final int code;
    private final String message;

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return "";
    }
}
