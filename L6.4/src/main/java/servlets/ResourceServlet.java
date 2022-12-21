package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceServer.IResourceServer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author ilYa
 */
public class ResourceServlet extends HttpServlet {

    static final Logger logger = LogManager.getLogger(ResourceServlet.class.getName());

    public static final String PAGE_URL = "/resources";

    private final IResourceServer resourceServer;

    public ResourceServlet(IResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) {

        String resourcePath = request.getParameter("path");

        if (Objects.isNull(resourcePath) || resourcePath.isEmpty()) {
            logger.error("No 'resourcePath' parameter.");

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        logger.info("Resource path: ".concat(resourcePath));
        resourceServer.saveResource(resourcePath);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
