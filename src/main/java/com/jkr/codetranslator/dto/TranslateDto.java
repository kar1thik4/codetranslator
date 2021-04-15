package com.jkr.codetranslator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslateDto {
    private String text;
    private String targetLanguage;
    private String sourceLanguage;
}
