package mirror;

/**
 * @author ilYa
 */
public class MirrorService {

    private MirrorWebSocket webSocket;

    public MirrorService() {
    }

    public void sendMessage(String data) {
        try {
            webSocket.sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setWebSocket(MirrorWebSocket webSocket) {
        this.webSocket = webSocket;
    }

}
