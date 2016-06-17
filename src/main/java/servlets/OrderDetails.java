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
import static model.Ticket.LUGGAGE_PRICE;
import static model.Ticket.PRIORITY_PRICE;

@Log4j
@WebServlet({"/orderDetails"})
public class OrderDetails extends HttpServlet {
    private FlightDao flightDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        flightDao = (FlightDao) config.getServletContext().getAttribute(FLIGHT_DAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean luggage = Boolean.parseBoolean(request.getParameter("luggage"));
        boolean priority = Boolean.parseBoolean(request.getParameter("priority"));
        Flight flight = (Flight) (request.getSession(true).getAttribute("flight"));
        int price = flight.getPrice();
        price += (luggage) ? LUGGAGE_PRICE : 0;
        price += (priority) ? PRIORITY_PRICE : 0;
        request.getSession(true).setAttribute("luggage", luggage);
        request.getSession(true).setAttribute("priority", priority);
        request.getSession(true).setAttribute("price", price);
        log.info("ticket options (luggage, priority) and ticket price added to session!");
        request.getRequestDispatcher("/order/confirm/index.jsp").forward(request, response);
    }
}