package mirror;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;
import java.util.concurrent.TimeUnit;

/**
 * @author ilYa
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketMirrorServlet extends WebSocketServlet {

    private final static long LOGOUT_TIME = TimeUnit.MINUTES.toMillis(10L);

    private final MirrorService mirrorService;

    public WebSocketMirrorServlet() {
        this.mirrorService = new MirrorService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new MirrorWebSocket(mirrorService));
    }
}
