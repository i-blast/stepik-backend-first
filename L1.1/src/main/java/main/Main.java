package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.stepik.servlet.MirrorResponseServlet;

/**
 * @author ilYa
 */
public class Main {

//    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        MirrorResponseServlet mirrorResponseServlet = new MirrorResponseServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorResponseServlet), "/mirror/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
//        logger.debug("Server started");
        System.out.println("Server started");
        server.join();
    }
}
