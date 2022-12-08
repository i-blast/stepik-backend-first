package org.stepik.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Задача:
 * Написать сервлет, который будет обрабатывать запросы на /mirror.
 * При получении GET запроса с параметром key=value сервлет должен вернуть в response строку содержащую value.
 * Например, при GET запросе /mirror?key=hello сервер должен вернуть страницу, на которой есть слово "hello".
 * </p>
 *
 * @author ilYa
 */
public class MirrorResponseServlet extends HttpServlet {

    private static final String KEY_QUERY_PARAM = "key";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String value = extractKeyParam(req);

        resp.getWriter().println(value);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private static String extractKeyParam(HttpServletRequest req) {
        String[] keys = req.getParameterMap().get(KEY_QUERY_PARAM);
        return keys.length != 0 ? keys[0] : "";
    }
}
