package example;

import accountServer.AccountServer;
import accountServer.AccountServerController;
import accountServer.AccountServerControllerMBean;
import accountServer.IAccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resourceServer.IResourceServer;
import resourceServer.ResourceServer;
import resourceServer.ResourceServerController;
import resourceServer.ResourceServerControllerMBean;
import servlets.AdminServlet;
import servlets.HomePageServlet;
import servlets.ResourceServlet;

import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.OperationsException;
import java.lang.management.ManagementFactory;

/**
 * @author ilYa
 */
public class Main {

    static final Logger logger = LogManager.getLogger(Main.class.getName());

    private static final int DEFAULT_PORT = 8080;
    private static final int USERS_LIMIT = 10;

    public static void main(String[] args) throws Exception {

        // Handle program arguments.
        int port = DEFAULT_PORT;
        if (args.length == 1) {
            String portString = args[0];
            try {
                port = Integer.parseInt(portString);
            } catch (NumberFormatException exc) {
                logger.error("Use port as the first argument", exc);
                System.exit(1);
            }
        }

        startServer(port);
    }

    /**
     * Configuring and starting servlet container.
     *
     * @param port
     * @throws Exception
     */
    private static void startServer(int port) throws Exception {

        IAccountServer accountServer = configureAccountService();
        IResourceServer resourceServer = configureResourceService();

        logger.info("Starting at http://127.0.0.1:".concat(String.valueOf(port)));
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new AdminServlet(accountServer)), AdminServlet.PAGE_URL);
        context.addServlet(new ServletHolder(new ResourceServlet(resourceServer)), ResourceServlet.PAGE_URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        logger.info("Server started");

        server.join();
    }

    /**
     * Configuring account service.
     *
     * @return
     * @throws OperationsException
     * @throws MBeanException
     */
    private static IAccountServer configureAccountService() throws OperationsException, MBeanException {

        logger.info("Configuring account service");
        IAccountServer accountServer = new AccountServer(USERS_LIMIT);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStatistics, name);

        return accountServer;
    }

    /**
     * Configuring resource service.
     *
     * @return
     * @throws OperationsException
     * @throws MBeanException
     */
    private static IResourceServer configureResourceService() throws OperationsException, MBeanException {

        logger.info("Configuring resource service");
        IResourceServer resourceServer = new ResourceServer();

        ResourceServerControllerMBean resourceAccess = new ResourceServerController(resourceServer);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resourceAccess, name);

        return resourceServer;
    }
}
