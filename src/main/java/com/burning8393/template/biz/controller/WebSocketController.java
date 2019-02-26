package com.burning8393.template.biz.controller;

import com.burning8393.template.common.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * WebSocketController class
 *
 * @author : Pangxw
 * @date : 2019/2/26 16:42
 * @description :
 */
@Component
@ServerEndpoint("/my-chat/{nickName}")
@Slf4j
public class WebSocketController {

    @OnOpen
    public void onOpen(@PathParam("nickName") String userNickname, Session session) {
        String message = "有新游客[" + userNickname + "]加入聊天室！";
        log.info(message);
        WebSocketUtil.addSession(userNickname, session);
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnClose
    public void onClose(@PathParam("nickName") String userNick, Session session) {
        String message = "有新游客[" + userNick + "]退出聊天室！";
        log.info(message);
        WebSocketUtil.removeSession(userNick);
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("nickName") String userNick, String message) {
        String info = "游客[" + userNick + "]: " + message;
        log.info(info);
        WebSocketUtil.sendMessageForAll(message);
    }

    public void onError(Session session, Throwable throwable) {
        log.error("异常： ", throwable);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

}
