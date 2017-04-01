package com.everis.blockchain.utils;

import com.everis.blockchain.exceptions.RestTemplateException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestTemplatePost extends RestTemplateBase {


    public <T> ResponseEntity<T> postRest(final String uri,
                                          final Class<T> responseType,
                                          final Map<String, List<String>> headersInput,
                                          final Object body,
                                          final Map<String, String> params) throws RestTemplateException {
        return basePost(uri, responseType, createHeaders(headersInput), body, params);
    }

    public <T> ResponseEntity<T> postRest(final String uri,
                                          final Class<T> responseType,
                                          final Object body,
                                          final Map<String, List<String>> headersInput
    ) throws RestTemplateException {
        return basePost(uri, responseType, createHeaders(headersInput), body, new HashMap<>());
    }

    public <T> ResponseEntity<T> postRest(final String uri,
                                          final Class<T> responseType,
                                          final String username,
                                          final String password,
                                          final Map<String, String> params) throws RestTemplateException {
        return basePost(uri, responseType, createHeaders(username, password), null, params);
    }

    public <T> ResponseEntity<T> postRest(final String uri,
                                          final Class<T> responseType,
                                          final String username,
                                          final String password) throws RestTemplateException {
        return basePost(uri, responseType, createHeaders(username, password), null, new HashMap<>());
    }
}
