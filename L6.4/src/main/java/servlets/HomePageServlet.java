package servlets;

import accountServer.IAccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author a.akbashev
 * @author v.chibrikov
 * <p>
 * Пример кода для курса на https://stepic.org/
 * <p>
 * Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class HomePageServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(HomePageServlet.class.getName());
    public static final String PAGE_URL = "/home";
    private final IAccountServer accountServer;

    public HomePageServlet(IAccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");

        String remove = request.getParameter("remove");

        if (remove != null) {
            accountServer.removeUser();
            response.getWriter().println("Hasta la vista!");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        int limit = accountServer.getUsersLimit();
        int count = accountServer.getUsersCount();

        logger.info("Limit: {}. Count {}", limit, count);

        if (limit > count) {
            logger.info("User pass");
            accountServer.addNewUser();
            response.getWriter().println("Hello, world!");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            logger.info("User were rejected");
            response.getWriter().println("Server is closed for maintenance!");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
