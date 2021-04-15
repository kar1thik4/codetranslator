package com.jkr.codetranslator.resource;

import com.jkr.codetranslator.clients.JaxRsTranslatorClient;
import com.jkr.codetranslator.model.Detection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetectionResource {

    @Autowired
    JaxRsTranslatorClient jaxClient;

    @PostMapping("/detection")
    public List<List<Detection>> detectLanguage(@RequestParam("textBody") String textBody){
        return jaxClient.detectLanguage(textBody);
    }
}
