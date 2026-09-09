package com.fiap.gregory.hackathon.infra.db.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ExchangeType {
    UNDEFINED(0, "Undefined"),
    IN_PERSON(1, "In Person"),
    EMAIL(2, "Email");

    private final int code;
    private final String description;

    ExchangeType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static String getDescriptionByCode(int code) {
        return Arrays.stream(ExchangeType.values())
                .filter(d -> d.getCode() == code)
                .map(ExchangeType::getDescription)
                .findFirst()
                .orElse(UNDEFINED.getDescription());
    }
}
