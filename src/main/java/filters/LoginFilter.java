package filters;

import common.servlets.HttpFilter;
import lombok.extern.log4j.Log4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Log4j
@WebFilter("/*")
public class LoginFilter implements HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal != null) {
            request.setAttribute("isLoggedIn", true);
            request.setAttribute("login", userPrincipal.getName());
            log.info("User login info added to request");
        }
        chain.doFilter(request, response);
    }
}
