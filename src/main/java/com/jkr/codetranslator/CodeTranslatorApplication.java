package com.jkr.codetranslator;

import com.jkr.codetranslator.clients.JaxRsTranslatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CodeTranslatorApplication {

    @Autowired
    JaxRsTranslatorClient jaxClient;

    public static void main(String[] args) {

        SpringApplication.run(CodeTranslatorApplication.class);
    }

   /* @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println(jaxClient.getSupportedLanguages("en"));

        try {
            System.out.println(mapper.writeValueAsString(jaxClient.detectLanguage("This is a test. Can it translate this to english? We will see.")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(mapper.writeValueAsString(jaxClient.translate("Hola, como estas?", "en", null)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(mapper.writeValueAsString(jaxClient.translate("Hola, como estas?", "pt", "es")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/
}
