package com.everis.blockchain.utils;

import com.everis.blockchain.exceptions.RestTemplateException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.List;
import java.util.Map;


class RestTemplateBase {

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void initRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(30000);
        factory.setConnectTimeout(30000);
        restTemplate.setRequestFactory(factory);
    }

    protected <T> ResponseEntity<T> baseGet(final String uri,
                                            final Class<T> responseType,
                                            final HttpHeaders headers,
                                            final Map<String, String> paramsInput) throws RestTemplateException {
        return restTemplate.exchange(
                prepareUriParameters(uri, paramsInput),
                HttpMethod.GET, new HttpEntity(headers),
                responseType);
    }

    protected <T> ResponseEntity<T> basePost(final String uri,
                                             final Class<T> responseType,
                                             final HttpHeaders headers,
                                             final Object body,
                                             final Map<String, String> params) throws RestTemplateException {
        return restTemplate.exchange(prepareUriParameters(uri, params), HttpMethod.POST,
                new HttpEntity(body, headers), responseType);
    }


    protected HttpHeaders createHeaders(final String username, final String password) throws RestTemplateException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, encodeBase64AuthBasic(username, password));
        return headers;
    }

    protected HttpHeaders createHeaders(final Map<String, List<String>> headersInput,
                                        final String username, final String password) throws RestTemplateException {
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(headersInput);
        headers.add(HttpHeaders.AUTHORIZATION, encodeBase64AuthBasic(username, password));
        return headers;
    }

    protected HttpHeaders createHeaders(final Map<String, List<String>> headersInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(headersInput);
        return headers;
    }

    private String encodeBase64AuthBasic(final String username, final String password) throws RestTemplateException {
        try {
            String credentials = username + ":" + password;
            final byte[] authBytes = credentials.getBytes("UTF-8");
            return "Basic " + Base64.getEncoder().encodeToString(authBytes);
        } catch (Exception e) {
            throw new RestTemplateException(e.getMessage(), e);
        }

    }

    private String prepareUriParameters(final String uri, final Map<String, String> paramsInput) throws RestTemplateException {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
            for (Map.Entry<String, String> entry : paramsInput.entrySet()) {
                params.add(entry.getKey(), entry.getValue());
            }

            return UriComponentsBuilder.fromUriString(uri).queryParams(params).build().toString();
        } catch (Exception e) {
            throw new RestTemplateException(e.getMessage(), e);
        }
    }


}
