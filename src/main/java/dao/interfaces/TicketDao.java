package dao.interfaces;

import model.Ticket;

import java.util.Collection;

public interface TicketDao extends Dao {
    boolean add(Ticket ticket);
    Collection<Ticket> getTicketsByClientEmail(String  email);
//    Optional<Ticket> getTicketByClientEmail(String  email);
}
