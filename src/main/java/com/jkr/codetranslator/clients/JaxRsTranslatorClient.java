package com.jkr.codetranslator.clients;

import com.jkr.codetranslator.model.Detection;
import com.jkr.codetranslator.model.GoogleTranslateObject;
import com.jkr.codetranslator.model.Language;
import com.jkr.codetranslator.model.Translation;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

@Component
public class JaxRsTranslatorClient extends TranslatorClient {

    Client client;
    WebTarget baseTarget;

    @PostConstruct
    private void postConstruct() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            // Create a new X509TrustManager
            sslContext.init(null, getTrustManager(), null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw e;
        }
        client = ClientBuilder.newBuilder().hostnameVerifier((s, session) -> true)
                .sslContext(sslContext).build();
        baseTarget = client.target(this.endpointBase);
    }

    private TrustManager[] getTrustManager() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                    }
                }
        };
    }

    @Override
    public List<Language> getSupportedLanguages(String targetLanguage) {
        MultivaluedMap<String, Object> myHeaders = super.createRapidApiHeaders();
        WebTarget languagesTarget = baseTarget.path(this.languagesEndpoint);
        GoogleTranslateObject translateObject = languagesTarget.queryParam("target", targetLanguage)
                .request(MediaType.APPLICATION_JSON).headers(myHeaders).get(GoogleTranslateObject.class);
        return translateObject.getData().getLanguages();
    }

    @Override
    public List<List<Detection>> detectLanguage(String inputString) {
        Form formData = new Form();
        formData.param("q", inputString);
        WebTarget detectTarget = baseTarget.path(this.detectEndpoint);
        GoogleTranslateObject translateObject = detectTarget.request(MediaType.APPLICATION_JSON)
                .headers(super.createRapidApiHeaders()).post(Entity.form(formData), GoogleTranslateObject.class);
        return translateObject.getData().getDetections();
    }

    @Override
    public List<Translation> translate(String inputString, String targetLanguage, String sourceLanguage) {
        Form formData = new Form();
        formData.param("q", inputString);
        formData.param("target", targetLanguage);
        if (Optional.ofNullable(sourceLanguage).isPresent()) {
            formData.param("source", sourceLanguage);
        }
        WebTarget translateTarget = baseTarget.path(this.translateEndpoint);
        GoogleTranslateObject translateObject = translateTarget.request(MediaType.APPLICATION_JSON)
                .headers(super.createRapidApiHeaders()).post(Entity.form(formData), GoogleTranslateObject.class);
        return translateObject.getData().getTranslations();
    }

}
