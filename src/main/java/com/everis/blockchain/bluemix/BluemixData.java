package com.everis.blockchain.bluemix;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Data
public class BluemixData {

    @JsonIgnore
    private BluemixCredentials bluemixCredentials;

    private BluemixUser adminUser;

    private List<String> peers = new LinkedList<String>();

    public BluemixData(final BluemixCredentials bluemixCredentialsValue) throws Exception {
        this.bluemixCredentials = bluemixCredentialsValue;
        prepareBlueMixData();
    }

    private void prepareBlueMixData() throws Exception {
        for (BluemixUser user : bluemixCredentials.getUsers()) {
            if (user.getUsername().equalsIgnoreCase("admin")) {
                this.adminUser = user;
                break;
            }
        }

        peers.clear();
        for (BluemixPeer peer : bluemixCredentials.getPeers()) {
            peers.add(peer.getApiUrl());
        }
    }
}
