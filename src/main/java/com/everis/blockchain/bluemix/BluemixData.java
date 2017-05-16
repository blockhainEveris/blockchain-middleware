package com.everis.blockchain.bluemix;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
@Slf4j
public class BluemixData {

    @JsonIgnore
    private BluemixCredentials bluemixCredentials;
    private String chainCode;
    private List<BluemixUser> nodeUsers = new LinkedList<BluemixUser>();

    public BluemixData(final BluemixCredentials bluemixCredentialsValue) throws Exception {
        this.bluemixCredentials = bluemixCredentialsValue;
        prepareBlueMixData();
    }

    private void prepareBlueMixData() throws Exception {
        for (BluemixUser user : bluemixCredentials.getUsers()) {
            if (user.getUsername().equalsIgnoreCase("user_type2_0")) {
                user.setPeer(bluemixCredentials.getPeers().get(0).getApiUrl().replace("http", "https"));
                nodeUsers.add(user);
            /*} else if (user.getUsername().equalsIgnoreCase("user_type2_1")) {
                user.setPeer(bluemixCredentials.getPeers().get(1).getApiUrl().replace("http", "https"));
                nodeUsers.add(user);
            } else if (user.getUsername().equalsIgnoreCase("user_type2_2")) {
                user.setPeer(bluemixCredentials.getPeers().get(2).getApiUrl().replace("http", "https"));
                nodeUsers.add(user);
            } else if (user.getUsername().equalsIgnoreCase("user_type2_3")) {
                user.setPeer(bluemixCredentials.getPeers().get(3).getApiUrl().replace("http", "https"));
                nodeUsers.add(user);*/
            }
        }
    }

    public BluemixUser getRandomUser() {
        final int max = 1;
        int random = new Random().nextInt(max);
        log.info("random: " + random);
        return nodeUsers.get(0);
    }
}
