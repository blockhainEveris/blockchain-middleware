package com.everis.blockchain.functional;

import com.everis.blockchain.constants.BlockChainConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class FunctionalBase {

    private final Logger log = LoggerFactory.getLogger(FunctionalBase.class);

    public static final int HTTPOK = 200;

    /**
     * Before class.
     */
    @BeforeClass
    public static void beforeClass() {

        RestAssured.port = Integer.valueOf(8081);
        RestAssured.baseURI = "http://34.209.32.187";
        //RestAssured.baseURI = "http://127.0.0.1";

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.urlEncodingEnabled = false;
    }

    @Test
    public void voting() throws Exception {
        info();
        login();
        //int votingId = addVoting();
        //log.info("votingId: " + votingId);
        //TimeUnit.SECONDS.sleep(10);
        //query(votingId);

        int votingId = 1114554197;

        for (int i = 0; i < 500; i++) {
            vote(votingId);
            //TimeUnit.SECONDS.sleep(1);
        }

        log.info("votingId:" + votingId);

    }


    public void info() {
        String path = BlockChainConstants.ENDPOINT_ADMIN + "info";
        given().when().get(path).then().assertThat().statusCode(HTTPOK);
    }

    public void login() throws Exception {
        String path = BlockChainConstants.ENDPOINT_ADMIN + "login";
        given().when().post(path).then().assertThat().statusCode(HTTPOK);
    }

    public int addVoting() throws Exception {
        String path = BlockChainConstants.ENDPOINT_ADMIN + BlockChainConstants.ENDPOINT_APPS_ADDVOTING;
        String file = FunctionalHelper.prepareAddVoting();
        String response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).body(file).when().post(path).asString();
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.getInt("id");
    }


    public void query(int votingId) {
        String path = BlockChainConstants.ENDPOINT_APPS + "query/" + votingId;
        given().when().get(path).then().assertThat().statusCode(HTTPOK);
    }

    public void vote(final int votingId) throws Exception {
        String prepareVoting = FunctionalHelper.prepareVoting(votingId);
        String path = BlockChainConstants.ENDPOINT_APPS + "vote";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON).body(prepareVoting).when().put(path);
                //.then().assertThat().statusCode(HTTPOK);
    }


}
