package com.everis.blockchain.constants;

public class BlockChainConstants {

    private BlockChainConstants() {
    }

    public static final String EVERIS_PROXY_HOST = "10.110.8.42";
    public static final String EVERIS_PROXY_PORT = "8080";
    public static final boolean EVERIS_PROXY = true;

    public static final String ENDPOINT_ADMIN = "/api/v1/admin/blockchain/";
    public static final String ENDPOINT_ADMIN_SETCHAINCODE = "/setchaincode/{chainCodeId}";
    public static final String ENDPOINT_ADMIN_INFO = "/info";
    public static final String ENDPOINT_ADMIN_LOGIN = "/login";
    public static final String ENDPOINT_ADMIN_CHAIN = "/chaincode";

    public static final String ENDPOINT_APPS = "/api/v1/apps/blockchain/";
    public static final String ENDPOINT_APPS_QUERY = "/query";
    public static final String ENDPOINT_APPS_QUERY_VOTING = "/query/{votingId}";
    public static final String ENDPOINT_APPS_ADDVOTING = "/addvoting";
    public static final String ENDPOINT_APPS_UPDATE = "/update";
    public static final String ENDPOINT_APPS_UPDATE_VOTING = "/update/{votingId}";

    public static final String ENDPOINT_APPS_VOTE = "/vote";
    public static final String BLUEMIX_CHAINCODE_URL = "https://github.com/blockhainEveris/blockchain-chaincode";

    public static final String NOT_APPLY = "NA";


}
