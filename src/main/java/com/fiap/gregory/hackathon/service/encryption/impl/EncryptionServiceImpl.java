package com.fiap.gregory.hackathon.service.encryption.impl;

import com.fiap.gregory.hackathon.service.encryption.IEncryptionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor(force = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EncryptionServiceImpl implements IEncryptionService {

    StrongTextEncryptor encryptor;

    @Value("${encryptor.code}")
    String code;

    @Bean
    private void startEncryption() {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(code);
    }

    @Override
    public String encrypt(String str) {
        return encryptor.encrypt(str);
    }

    @Override
    public String decrypt(String str) {
        return encryptor.decrypt(str);
    }
}
