package com.example.carrental.service;

import com.example.carrental.model.Translatable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for class {@link TranslateService}
 */
@ExtendWith(MockitoExtension.class)
public class TranslateServiceTest {

    @Mock
    private ResourceBundle messagesBundle;

    @InjectMocks
    private TranslateService translateService;

    @Test
    public void testTranslate_WithTranslatable() {
        // Arrange
        Translatable translatable = new Translatable() {
            @Override
            public String getMessageCode() {
                return "test.message.code";
            }
        };
        String expectedTranslation = "Test message";

        // Act
        String translation = translateService.translate(translatable);

        // Assert
        assertEquals(expectedTranslation, translation);
    }

    @Test
    public void testTranslate_WithMessageCode() {
        // Arrange
        String messageCode = "test.message.code";
        String expectedTranslation = "Test message";

        // Act
        String translation = translateService.translate(messageCode);

        // Assert
        assertEquals(expectedTranslation, translation);
    }
}