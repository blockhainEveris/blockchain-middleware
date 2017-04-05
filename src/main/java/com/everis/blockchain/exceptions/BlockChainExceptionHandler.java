package com.everis.blockchain.exceptions;

import com.everis.blockchain.validation.WSErrorResponse;
import com.everis.blockchain.validation.WSResponseHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BlockChainExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BlockChainExceptionHandler.class);
    private static final String SERVICE_ERROR = "Service error: {}";

    private Properties errorTranslator = new Properties();

    @PostConstruct
    public void init() throws IOException {
        String filename = "dbe-error-translator.properties";
        InputStream input = BlockChainExceptionHandler.class.getClassLoader().getResourceAsStream(filename);
        errorTranslator.load(input);
    }

    /**
     * @param e       Exception
     * @param request Web request
     * @return Handle internal exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(final Exception e, final WebRequest request) {
        LOG.error("Internal server error not controlled", e);

        HttpHeaders headers = new HttpHeaders();

        BlockChainException exception = new BlockChainException(e);

        return handleExceptionInternal(e, "Exception: " + exception.getDescription(), headers,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(BlockChainException.class)
    public ResponseEntity<Object> handleDBEException(final BlockChainException e, final WebRequest request) {

        WSErrorResponse errorResponse = WSResponseHelper.buildDBEErrorResponse(e, errorTranslator);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Gson gson = new Gson();
        LOG.error(SERVICE_ERROR, gson.toJson(e.getDescription()));

        return handleExceptionInternal(e, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * @param e       DBEValidationException
     * @param request WebRequest
     * @return Internat handle exception
     */
    @ExceptionHandler(BlockChainValidationException.class)
    public ResponseEntity<Object> handleDBEValidationException(final BlockChainValidationException e, final WebRequest request) {

        WSErrorResponse errorResponse = WSResponseHelper.buildValidationErrorResponse(e, errorTranslator);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Gson gson = new Gson();
        LOG.error(SERVICE_ERROR, gson.toJson(errorResponse));

        return handleExceptionInternal(e, errorResponse, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }


}
