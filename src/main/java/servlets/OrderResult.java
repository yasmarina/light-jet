package servlets;

import dao.interfaces.FlightDao;
import dao.interfaces.PersonDao;
import dao.interfaces.TicketDao;
import lombok.extern.log4j.Log4j;
import model.Flight;
import model.Person;
import model.Ticket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import static listeners.DbInit.FLIGHT_DAO;
import static listeners.DbInit.PERSON_DAO;
import static listeners.DbInit.TICKET_DAO;

@Log4j
@WebServlet({"/orderResult"})
public class OrderResult extends HttpServlet{

    private FlightDao flightDao;
    private PersonDao personDao;
    private TicketDao ticketDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        flightDao = (FlightDao) config.getServletContext().getAttribute(FLIGHT_DAO);
        personDao = (PersonDao) config.getServletContext().getAttribute(PERSON_DAO);
        ticketDao = (TicketDao) config.getServletContext().getAttribute(TICKET_DAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String passport = request.getParameter("passport");
        boolean luggage = (boolean)(request.getSession(true).getAttribute("luggage"));
        boolean priority = (boolean)(request.getSession(true).getAttribute("priority"));
        Principal userPrincipal = request.getUserPrincipal();

        if (userPrincipal != null) {
            Flight flight = (Flight) (request.getSession(true).getAttribute("flight"));
            Person person = personDao.getPersonByEmail(userPrincipal.getName()).orElseThrow(RuntimeException::new);
            Ticket ticket = new Ticket(flight, person, firstName, lastName, passport, luggage, priority, flight.getPrice());
            boolean isTicketAdded = ticketDao.add(ticket);
            log.info(isTicketAdded ? "New ticket added to the db" : "No ticket added");
            Collection<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket);
            request.setAttribute("tickets", tickets);
            log.info("Ticket list added to request");
        }

        request.getRequestDispatcher("/my-orders/index.jsp").forward(request, response);
    }
}
