package filters;

import common.servlets.HttpFilter;
import dao.interfaces.TicketDao;
import lombok.extern.log4j.Log4j;
import model.Ticket;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static listeners.DbInit.TICKET_DAO;

@Log4j
@WebFilter({"/my-orders/index.jsp"})
public class TicketList implements HttpFilter {
    private TicketDao ticketDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ticketDao = (TicketDao) filterConfig.getServletContext().getAttribute(TICKET_DAO);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Collection<Ticket> tickets = ticketDao.getTicketsByClientEmail(request.getUserPrincipal().getName());
        request.setAttribute("tickets", tickets);
        log.info("Ticket list added to request");

        chain.doFilter(request, response);
    }
}
