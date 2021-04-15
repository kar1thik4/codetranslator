package com.jkr.codetranslator.model;

import lombok.Data;

@Data
public class Detection {

    private Boolean isReliable;
    private String language;
    private Integer confidence;

}
