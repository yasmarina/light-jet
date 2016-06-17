package filters;

import common.servlets.HttpFilter;
import dao.interfaces.FlightDao;
import lombok.extern.log4j.Log4j;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static listeners.DbInit.FLIGHT_DAO;

@Log4j
@WebFilter({"/index.jsp"})
public class CityList implements HttpFilter {
    private FlightDao flightDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        flightDao = (FlightDao) filterConfig.getServletContext().getAttribute(FLIGHT_DAO);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Collection<String> cities = flightDao.getCityList();
        request.setAttribute("cities", cities);
        log.info("City list added to request");

        chain.doFilter(request, response);
    }
}
