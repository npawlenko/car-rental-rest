package com.example.carrental.service;

import com.example.carrental.model.Translatable;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Service
public class TranslateService {

    private final ResourceBundle messagesBundle = ResourceBundle.getBundle("messages");

    public String translate(Translatable translatable) {
        String messageCode = translatable.getMessageCode();
        return translate(messageCode);
    }

    public String translate(String messageCode) {
        return messagesBundle.getString(messageCode);
    }
}
