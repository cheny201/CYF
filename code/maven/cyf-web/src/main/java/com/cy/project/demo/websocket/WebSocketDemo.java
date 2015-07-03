package com.cy.project.demo.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

@Deprecated
public class WebSocketDemo extends WebSocketServlet{
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final long serialVersionUID = 1L;

    private static final String GUEST_PREFIX = "Guest ";

    private final AtomicInteger connectionIds = new AtomicInteger(0);
    private final Set<ChatMessageInbound> connections =
            new CopyOnWriteArraySet<ChatMessageInbound>();

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,
            HttpServletRequest request) {
        return new ChatMessageInbound(connectionIds.incrementAndGet());
    }

    private final class ChatMessageInbound extends MessageInbound {

        private final String nickname;

        private ChatMessageInbound(int id) {
            this.nickname = GUEST_PREFIX + id;
        }

		@Override
        protected void onOpen(WsOutbound outbound) {
            connections.add(this);
            System.out.println(this.nickname+"开启连接");
            String msg = "{'title':'"+(this.nickname+" 进入了聊天室")+"','message':''}";
            broadcast(msg);
        }

        @Override
        protected void onClose(int status) {
            connections.remove(this);
            System.out.println(this.nickname+"关闭连接");
            String msg = "{'title':'"+(this.nickname+" 退出了聊天室")+"','message':''}";
            broadcast(msg);
        }

        @Override
        protected void onBinaryMessage(ByteBuffer message) throws IOException {
            throw new UnsupportedOperationException(
                    "Binary message not supported.");
        }

        @Override
        protected void onTextMessage(CharBuffer message) throws IOException {
            // Never trust the client
        	String msg = message.toString();
        	System.out.println("接收数据["+msg+"]");
        	msg = "{'title':'"+this.nickname+" ["+sdf.format(new Date())+"]','message':'"+message.toString()+"'}";
        	broadcast(msg);
        }

        private void broadcast(String message) {
        	System.out.println(connections.size());
        	System.out.println("发送数据["+message+"]");
            for (ChatMessageInbound connection : connections) {
                try {
                    CharBuffer buffer = CharBuffer.wrap(message);
                    connection.getWsOutbound().writeTextMessage(buffer);
                } catch (IOException ignore) {
                    // Ignore
                }
            }
        }
    }
	
	
	

}
