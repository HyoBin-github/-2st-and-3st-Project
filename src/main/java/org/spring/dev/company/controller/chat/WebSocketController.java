package org.spring.dev.company.controller.chat;


import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value = "/messenger")
public class WebSocketController {

    // 요청 사용자 정보가 Session 객체를 담아둔 clientinfo 있는지 확인
    private static Set<Session> clientinfo = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session){
        System.out.println("session: " + session.toString());

        if(!clientinfo.contains(session)){
            // session 이 없으면 session 설정
            clientinfo.add(session);
            System.out.println("session Open: " + session.toString());
        } else {
            // 세션이 이미 있는 경우
            System.out.println("session이 이미 존재합니다.");
        }
    }

    // client 메세지를 받고 -> 응답 메세지 전송 -> client
    @OnMessage
    public void onMessage(String message, Session session) throws Exception{
        for(Session session1 : clientinfo){
            //client 메세지 -> 전송 메세지
            session1.getBasicRemote().sendText(message);
        }
    }

    @OnClose // 접속해제시 접속자 해제 설정
    public void onClose(Session session){
        System.out.println("Session Close: "+ session);
        clientinfo.remove(session); // 세션 삭제
    }

    @OnError //에러 발생시
    public void handleError(Throwable throwable){
        System.out.println("Websocket Error!");
        throwable.printStackTrace();
    }

}
