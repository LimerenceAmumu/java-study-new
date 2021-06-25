package com.lhp.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Amumu
 * @create 2021/3/31 15:53
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://121.40.165.18:8800")) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }
                @Override
                public void onMessage(String s) {

                }
                @Override
                public void onClose(int i, String s, boolean b) {

                }
                @Override
                public void onError(Exception e) {
                }
            };
            //readyState always is NOT_YET_CONNECTED
            ReadyState readyState = webSocketClient.getReadyState();
            webSocketClient.connect();

            webSocketClient.send("test!");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
