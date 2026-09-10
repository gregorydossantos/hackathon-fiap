package com.fiap.gregory.hackathon.useful;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.gregory.hackathon.useful.StringUseful.nonNullOrEmpty;
import static org.junit.jupiter.api.Assertions.*;

class StringUsefulTest {

    @Test
    @DisplayName("should be validate a STRING")
    void should_Return_True_Or_False_After_Validate_String() {
        assertTrue(nonNullOrEmpty("Test"));
        assertFalse(nonNullOrEmpty(" "));
        assertFalse(nonNullOrEmpty(null));
    }
}