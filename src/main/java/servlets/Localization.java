package servlets;

import lombok.extern.log4j.Log4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j
@WebServlet("/l10n")
public class Localization extends HttpServlet {
    private static final String LOCALE = "locale";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter(LOCALE);
        setLocale(request.getSession(true), locale);
//        TODO: Find out how to redirect to the "referer" with its own parameters (session?)
//        response.sendRedirect(request.getHeader("referer"));
        response.sendRedirect("/");
    }

    public static void setLocale(HttpSession session, String locale) {
        String sessionLocale = (String) session.getAttribute(LOCALE);
        if (sessionLocale == null || !sessionLocale.equals(locale)) {
            session.setAttribute(LOCALE, locale);
            log.info("Locale was set to " + locale);
        }
    }
}
