package servlets;

import dao.interfaces.FlightDao;
import lombok.extern.log4j.Log4j;
import model.Flight;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static listeners.DbInit.FLIGHT_DAO;

@Log4j
@WebServlet({"/flightList"})
public class FlightList extends HttpServlet {
    private FlightDao flightDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        flightDao = (FlightDao) config.getServletContext().getAttribute(FLIGHT_DAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departureCity = request.getParameter("departureCity");
        String arrivalCity = request.getParameter("arrivalCity");
        if (departureCity.equals(arrivalCity))
            response.sendRedirect("/");
        else {
            Collection<Flight> flights = flightDao.getFlightByDirection(departureCity, arrivalCity);
            request.setAttribute("flights", flights);
            log.info("FlightList added to request!");
            request.getRequestDispatcher("/flight/index.jsp").forward(request, response);
        }
    }
}
