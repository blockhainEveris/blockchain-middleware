package com.everis.blockchain.utils;

import com.everis.blockchain.message.basic.ChaincodeID;
import com.everis.blockchain.message.basic.CtorMsg;
import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.basic.Params;
import com.everis.blockchain.message.voting.input.AddVotingInput;
import com.everis.blockchain.message.voting.input.VoteInput;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MessageHelperBase {

    private static final String JSON_RPC = "2.0";

    private static final String QUERY_METHOD = "query";
    private static final String QUERY_FUNCTION = "read";
    private static final String QUERY_ARGS = "_votingindex";
    private static final List<String> QUERY_ARGS_LIST = Arrays.asList(QUERY_ARGS);

    private static final String UPDATE_FUNCTION = "update_votings_status";
    private static final List<String> UPDATE_ARGS_LIST = Arrays.asList(QUERY_ARGS);

    private static final String DEPLOY_METHOD = "deploy";
    private static final String DEPLOY_FUNCTION = "init";
    private static final List<String> DEPLOY_ARGS = Arrays.asList("909");

    private static final String INVOKE_METHOD = "invoke";
    private static final String INVOKE_FUNCTION_ADDVOTING = "add_voting";
    private static final String INVOKE_FUNCTION_VOTE = "vote";


    private static Init prepareInitBase(final String username) {
        Init init = new Init();
        init.setJsonrpc(JSON_RPC);
        init.setParams(prepareParams(username));
        return init;
    }

    protected static Init prepareInitInvoke(final String username, final int randomId) {
        Init init = prepareInitBase(username);
        init.setMethod(INVOKE_METHOD);
        init.setId(randomId);
        return init;
    }

    protected static Init prepareInitDeploy(final String username, final int randomId) {
        Init init = prepareInitBase(username);
        init.setMethod(DEPLOY_METHOD);
        init.setId(randomId);
        return init;
    }

    protected static Init prepareInitQuery(final String username, final int randomId) {
        Init init = prepareInitBase(username);
        init.setMethod(QUERY_METHOD);
        init.setId(randomId);
        return init;
    }

    protected static CtorMsg prepareCtorMsgDeploy() {
        CtorMsg msg = new CtorMsg();
        msg.setArgs(DEPLOY_ARGS);
        msg.setFunction(DEPLOY_FUNCTION);
        return msg;
    }


    protected static CtorMsg prepareCtorMsgAddVoting(final AddVotingInput votingInput, final int randomId) {
        CtorMsg msg = new CtorMsg();
        msg.setFunction(INVOKE_FUNCTION_ADDVOTING);
        List<String> sb = new LinkedList<String>();
        sb.add(randomId + "");
        sb.add(votingInput.getDescription());
        sb.add(votingInput.getVotingDeadlineInMinutes() + "");
        sb.addAll(votingInput.getOptions());
        msg.setArgs(sb);
        return msg;
    }

    protected static CtorMsg prepareCtorMsgVote(final VoteInput input) {
        CtorMsg msg = new CtorMsg();
        msg.setFunction(INVOKE_FUNCTION_VOTE);
        List<String> sb = new LinkedList<String>();
        sb.add(input.getVotingId() + "");
        sb.add(input.getOptionId() + "");
        sb.add(input.getJustification());
        sb.add(input.getVoter().getId() + "");
        sb.add(input.getVoter().getName());
        sb.add(input.getVoter().getCategory());
        sb.add(input.getVoter().getOffice());
        sb.add(input.getVoter().getChannel());
        msg.setArgs(sb);

        return msg;
    }

    protected static CtorMsg prepareCtorMsgQuery() {
        CtorMsg msg = new CtorMsg();
        msg.setArgs(QUERY_ARGS_LIST);
        msg.setFunction(QUERY_FUNCTION);
        return msg;
    }

    protected static CtorMsg prepareCtorMsgUpdate() {
        CtorMsg msg = new CtorMsg();
        msg.setArgs(UPDATE_ARGS_LIST);
        msg.setFunction(UPDATE_FUNCTION);
        return msg;
    }

    protected static ChaincodeID prepareChainCodePath(final String path) {
        ChaincodeID chaincodeID = new ChaincodeID();
        chaincodeID.setPath(path);
        return chaincodeID;
    }

    protected static ChaincodeID prepareChaincodeName(final String chainCode) {
        ChaincodeID chaincodeID = new ChaincodeID();
        chaincodeID.setName(chainCode);
        return chaincodeID;
    }

    protected static Params prepareParams(final String username) {
        Params params = new Params();
        params.setType(1);
        params.setSecureContext(username);
        return params;
    }


}
