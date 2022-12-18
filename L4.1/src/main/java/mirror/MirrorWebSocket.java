package mirror;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * @author ilYa
 */
@WebSocket
public class MirrorWebSocket {

    private final MirrorService mirrorService;

    private Session session;

    public MirrorWebSocket(MirrorService mirrorService) {
        this.mirrorService = mirrorService;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        mirrorService.setWebSocket(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        mirrorService.sendMessage(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
