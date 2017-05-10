package com.everis.blockchain.utils;

import com.everis.blockchain.message.basic.Init;
import com.everis.blockchain.message.voting.input.AddVotingInput;
import com.everis.blockchain.message.voting.input.VoteInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class MessageHelper extends MessageHelperBase {

    private static final int DEFAULT_ID = 0;
    private static final String PATH_TMP = System.getProperty("java.io.tmpdir") + File.separator + "chainCodeId.tmp";

    private static void log(final Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    public static Init prepareDeployChain(final String path, final String username) {
        Init init = prepareInitDeploy(username, 1);
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

    public static Init prepareQueryVotingMessage(final String chainCode, final String username, final int randomId, final int votingId) {
        Init init = prepareInitQuery(username, randomId);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgQueryVoting(votingId));
        log(init);
        return init;
    }

    public static Init prepareUpdateVotingsStatusMessage(final String chainCode, final String username, final int randomId) {
        Init init = prepareInitInvoke(username, randomId);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgUpdate());
        log(init);
        return init;
    }

    public static Init prepareUpdateVotingStatusMessage(final String chainCode, final String username, final int randomId, final int votingId) {
        Init init = prepareInitInvoke(username, randomId);
        init.getParams().setChaincodeID(prepareChaincodeName(chainCode));
        init.getParams().setCtorMsg(prepareCtorMsgUpdateVoting(votingId));
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

    public static void writeChainCodeTmpFile(final String chainCode) throws IOException {
        log.info("java.io.tmpdir: " + System.getProperty("java.io.tmpdir"));
        File tempFile = new File(PATH_TMP);
        FileWriter fileWriter = new FileWriter(tempFile, false);
        System.out.println(tempFile.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(chainCode);
        bw.close();
    }

    public static String getChainCodeTmpFile() {
        try {
            File tempFile = new File(PATH_TMP);
            return Files.readFirstLine(tempFile, Charset.defaultCharset());
        } catch (Exception e) {
            return "";
        }
    }

    public static int getRamdomAbs() {
        final int randomId = (int) System.currentTimeMillis();
        return Math.abs(randomId);
    }

}
