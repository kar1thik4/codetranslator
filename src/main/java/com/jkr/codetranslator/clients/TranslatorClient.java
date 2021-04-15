package com.jkr.codetranslator.clients;

import com.jkr.codetranslator.model.Detection;
import com.jkr.codetranslator.model.Language;
import com.jkr.codetranslator.model.Translation;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

public abstract class TranslatorClient {

    @Value("${google.endpoint.base}")
    protected String endpointBase;
    @Value("${google.endpoint.languages}")
    protected String languagesEndpoint;

    @Value("${google.endpoint.detect}")
    protected String detectEndpoint;

    @Value("${google.endpoint.translate}")
    protected String translateEndpoint;

    @Value("${com.rapidapi.rapid_api_key}")
    protected String rapidApiKey;

    @Value("${com.rapidapi.rapid_api_key_value}")
    protected String rapidApiKeyValue;

    @Value("${com.rapidapi.rapid_api_host}")
    protected String rapidApiHost;

    @Value("${com.rapidapi.rapid_api_host_value}")
    protected String rapidApiHostValue;

    /**
     * Get supported languages from Google API V2
     *
     * @param targetLanguage
     * @return
     */
    public abstract List<Language> getSupportedLanguages(String targetLanguage);

    /**
     * Use Google API V2 to detect language used on a string
     *
     * @param inputString
     * @return
     */
    public abstract List<List<Detection>> detectLanguage(String inputString);

    /**
     * Use Google API V2 to translate from a language to another language
     *
     * @param sourceLanguageCode
     * @return
     */
    public abstract List<Translation> translate(String inputString, String targetLanguage, String sourceLanguage);

    public MultivaluedMap<String,Object> createRapidApiHeaders() {
        MultivaluedMap<String, Object> myHeaders = new MultivaluedHashMap<String, Object>();
        myHeaders.add(this.rapidApiHost, this.rapidApiHostValue);
        myHeaders.add(this.rapidApiKey, this.rapidApiKeyValue);
        return myHeaders;
    }

}
