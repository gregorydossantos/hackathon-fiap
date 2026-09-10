package com.fiap.gregory.hackathon.config;

import jakarta.jms.ConnectionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class JmsConfigTest {

    private final JmsConfig jmsConfig = new JmsConfig();

    @Test
    @DisplayName("should be create a container factory for JMS service")
    void shouldCreateJmsListenerContainerFactory() {
        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

        DefaultJmsListenerContainerFactoryConfigurer configurer =
                mock(DefaultJmsListenerContainerFactoryConfigurer.class);

        JmsListenerContainerFactory<?> result = jmsConfig.myFactory(connectionFactory, configurer);

        assertNotNull(result);
        assertInstanceOf(DefaultJmsListenerContainerFactory.class, result);

        verify(configurer).configure(
                any(DefaultJmsListenerContainerFactory.class),
                same(connectionFactory)
        );
    }
}