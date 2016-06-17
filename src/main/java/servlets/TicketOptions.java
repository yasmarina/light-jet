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

import static listeners.DbInit.FLIGHT_DAO;

@Log4j
@WebServlet({"/ticketOptions"})
public class TicketOptions extends HttpServlet {
    private FlightDao flightDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        flightDao = (FlightDao) config.getServletContext().getAttribute(FLIGHT_DAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int flightId = Integer.parseInt((request.getParameter("flightId")));
        Flight flight = flightDao.getFlightById(flightId).orElseThrow(RuntimeException::new);
        request.getSession(true).setAttribute("flight", flight);
        log.info("Flight added to session");
        request.getRequestDispatcher("/ticket/index.jsp").forward(request, response);
    }
}
