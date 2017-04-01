package com.everis.blockchain.utils;

import com.everis.blockchain.exceptions.RestTemplateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestTemplateGet extends RestTemplateBase {

    public <T> ResponseEntity<T> getRest(final String uri,
                                         final Class<T> responseType,
                                         final String username,
                                         final String password,
                                         final Map<String, List<String>> headersInput,
                                         final Map<String, String> params) throws RestTemplateException {
        return baseGet(uri, responseType, createHeaders(headersInput, username, password), params);
    }

    public <T> ResponseEntity<T> getRest(final String uri,
                                         final Class<T> responseType,
                                         final Map<String, List<String>> headersInput,
                                         final Map<String, String> params) throws RestTemplateException {
        return baseGet(uri, responseType, createHeaders(headersInput), params);
    }

    public <T> ResponseEntity<T> getRest(final String uri,
                                         final Class<T> responseType,
                                         final String username,
                                         final String password,
                                         final Map<String, String> params) throws RestTemplateException {
        return baseGet(uri, responseType, createHeaders(username, password), params);
    }

    public <T> ResponseEntity<T> getRest(final String uri,
                                         final Class<T> responseType,
                                         final String username,
                                         final String password
    ) throws RestTemplateException {
        return baseGet(uri, responseType, createHeaders(username, password), new HashMap<>());
    }
}
