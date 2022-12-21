package servlets;

import accountServer.AccountServerI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ilYa
 */
public class AdminServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(AdminServlet.class.getName());

    public static final String PAGE_URL = "/admin";

    private final AccountServerI accountServer;

    public AdminServlet(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");

        int limit = accountServer.getUsersLimit();
        logger.info("limit: ".concat(String.valueOf(limit)));

        response.getWriter().print(limit);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
