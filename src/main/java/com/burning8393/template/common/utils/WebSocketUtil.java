package com.burning8393.template.common.utils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketUtil class
 *
 * @author : Pangxw
 * @date : 2019/2/26 16:46
 * @description :
 */
public class WebSocketUtil {
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

    public static void addSession(String userNick, Session session) {
        ONLINE_SESSION.put(userNick, session);
    }

    public static void removeSession(String userNick) {
        ONLINE_SESSION.remove(userNick);
    }

    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return ;
        }

        RemoteEndpoint.Async asyncRemote = session.getAsyncRemote();
        asyncRemote.sendText(message);
    }

    public static void sendMessageForAll(String message) {
        ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
    }
}
