package com.jkr.codetranslator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Data {

    private List<Language> languages = null;
    private List<List<Detection>> detections = null;
    private List<Translation> translations = null;

    public Data(List<List<Detection>>  detection){
        this.detections = detection;
    }

}
