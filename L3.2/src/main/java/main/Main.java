package main;

import model.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.account.AccountService;
import service.db.DBService;
import servlets.SessionsServlet;
import servlets.UsersServlet;

import java.util.HashMap;

/**
 * @author ilYa
 */
public class Main {

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBService();
        dbService.printConnectInfo();

        AccountService accountService = new AccountService(dbService, new HashMap<>());
//        Long adminUserId = accountService.createUser(new UserProfile("admin"));
//        Long testUserId = accountService.createUser(new UserProfile("test"));
//        System.out.println("Added user id: " + adminUserId);
//        UserProfile dataSet = dbService.getUserProfileById(adminUserId);
//        System.out.println("User data set: " + dataSet);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/signup");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/signin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
