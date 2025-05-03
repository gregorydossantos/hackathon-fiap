package com.fiap.gregory.hackathon.service.encryption.impl;

import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class EncryptionServiceImplTest {

    @Mock
    StrongTextEncryptor encryptor;

    @InjectMocks
    EncryptionServiceImpl encryptionService;

    @Test
    @DisplayName("SERVICE LAYER ::: Encrypt password field from user with successfully")
    void should_Encrypt_Password_When_Call_EncryptionService() {
        when(encryptor.encrypt(anyString())).thenReturn(anyString());

        var response = encryptionService.encrypt("test");
        assertNotNull(response);
    }

    @Test
    @DisplayName("SERVICE LAYER ::: Decrypt password field from user with successfully")
    void should_Decrypt_Password_When_Call_EncryptionService() {
        when(encryptor.decrypt(anyString())).thenReturn(anyString());

        var response = encryptionService.decrypt("test");
        assertNotNull(response);
    }
}