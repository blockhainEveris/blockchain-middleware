package com.everis.blockchain.utils;

import com.everis.blockchain.exceptions.BlockChainDeployChainException;
import com.everis.blockchain.exceptions.BlockChainException;
import com.everis.blockchain.exceptions.BlockChainRegistrarException;
import com.everis.blockchain.exceptions.BlockChainValidationException;
import com.everis.blockchain.validation.WSErrorResponse;
import com.everis.blockchain.validation.WSResponseHelper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ControllerAdvice
@Slf4j
public class BlockChainExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String SERVICE_ERROR = "Service error: {}";

    private Properties errorTranslator = new Properties();

    @PostConstruct
    public void init() throws IOException {
        String filename = "dbe-error-translator.properties";
        InputStream input = BlockChainExceptionHandler.class.getClassLoader().getResourceAsStream(filename);
        errorTranslator.load(input);
    }

    private static void logError(final String description) {
        log.error(SERVICE_ERROR, new Gson().toJson(description));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(final Exception e, final WebRequest request) {
        log.error("Internal server error not controlled", e);
        BlockChainException exception = new BlockChainException(e);
        return handleExceptionInternal(e, "Exception: " + exception.getDescription(), getHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(BlockChainRegistrarException.class)
    public ResponseEntity<Object> handleBlockChainRegistrarException(final BlockChainRegistrarException e, final WebRequest request) {
        WSErrorResponse errorResponse = WSResponseHelper.buildDBEErrorResponse(e, errorTranslator);
        errorResponse.setErrorDescription(e.getMessage());
        logError(e.getDescription());
        return handleExceptionInternal(e, errorResponse, getHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BlockChainDeployChainException.class)
    public ResponseEntity<Object> handleBlockChainDeployChainException(final BlockChainDeployChainException e, final WebRequest request) {
        WSErrorResponse errorResponse = WSResponseHelper.buildDBEErrorResponse(e, errorTranslator);
        errorResponse.setErrorDescription(e.getMessage());
        logError(e.getDescription());
        return handleExceptionInternal(e, errorResponse, getHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(BlockChainException.class)
    public ResponseEntity<Object> handleDBEException(final BlockChainException e, final WebRequest request) {
        WSErrorResponse errorResponse = WSResponseHelper.buildDBEErrorResponse(e, errorTranslator);
        errorResponse.setErrorDescription(e.getMessage());
        logError(e.getDescription());
        return handleExceptionInternal(e, errorResponse, getHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(BlockChainValidationException.class)
    public ResponseEntity<Object> handleDBEValidationException(final BlockChainValidationException e, final WebRequest request) {
        WSErrorResponse errorResponse = WSResponseHelper.buildValidationErrorResponse(e, errorTranslator);
        logError(e.getDescription());
        return handleExceptionInternal(e, errorResponse, getHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    public static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
