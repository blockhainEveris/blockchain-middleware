package com.everis.blockchain.utils;

import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.voting.input.AddVotingInput;
import com.everis.blockchain.message.voting.input.VoteInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHelper extends MessageHelperBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHelper.class);
    private static final int DEFAULT_ID = 0;

    private static void log(final Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LOGGER.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static Init prepareDeployChain(final String path, final String username, final int randomId) {
        Init init = prepareInitDeploy(username, randomId);
        init.getParams().setChaincodeID(prepareChainCodePath(path));
        init.getParams().setCtorMsg(prepareCtorMsgDeploy());
        log(init);
        return init;
    }

    public static Init prepareAddVotingMessage(final String chainCode, final AddVotingInput votingInput, final String username, final int randomId) {
        Init init = prepareInitInvoke(username, DEFAULT_ID);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgAddVoting(votingInput, randomId));
        log(init);
        return init;
    }

    public static Init prepareQueryMessage(final String chainCode, final String username, final int randomId) {
        Init init = prepareInitQuery(username, randomId);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgQuery());
        log(init);
        return init;
    }

    public static Init prepareUpdateVotingStatusMessage(final String chainCode, final String username, final int randomId) {
        Init init = prepareInitInvoke(username, randomId);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgUpdate());
        log(init);
        return init;
    }

    public static Init prepareVoteMessage(final String chainCode, final VoteInput input, final String username) {
        Init init = prepareInitInvoke(username, DEFAULT_ID);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgVote(input));
        log(init);
        return init;
    }


}
