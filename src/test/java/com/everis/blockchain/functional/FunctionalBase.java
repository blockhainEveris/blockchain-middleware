package com.everis.blockchain.functional;

import com.everis.blockchain.constants.BlockChainConstants;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class FunctionalBase {

    public static final int HTTPOK = 200;
    public static final int HTTP422 = 422;
    public static final int HTTP400 = 400;
    public static final int HTTP401 = 401;

    /**
     * Before class.
     */
    @BeforeClass
    public static void beforeClass() {

        //RestAssured.proxy(BlockChainConstants.EVERIS_PROXY_HOST, BlockChainConstants.EVERIS_PROXY_PORT);
        //RestAssured.port = Integer.valueOf(443);
        //RestAssured.baseURI = "https://blockchain-middleware-everis.herokuapp.com/";

        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost/";

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.urlEncodingEnabled = false;
    }

    /**
     * Default test.
     */
    @Test
    public void defaultTest() {
        assert (true);
    }

    @Test
    public void info() {
        String pathInfo = "/api/v1/admin/blockchain/info";
        given().when().get(pathInfo).then().assertThat().statusCode(HTTPOK);
    }
}
