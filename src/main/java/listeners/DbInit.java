package listeners;

import dao.interfaces.Dao;
import dao.mysql.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class DbInit implements ServletContextListener {

    @Resource(name="jdbc/ProdDB")
    private static DataSource ds;

    /*
     * TODO: Move all DAO init and access to separate class.
     * There is no need to put DAOs to servlet context
     */

    public static final String PERSON_DAO = "personDao";
    public static final String FLIGHT_DAO = "flightDao";
    public static final String TICKET_DAO = "ticketDao";

    private static Map <Class, Dao> daoMap = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        final ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute(FLIGHT_DAO, (MySqlFlightDao) ds::getConnection);
        servletContext.setAttribute(PERSON_DAO, (MySqlPersonDao) ds::getConnection);
        servletContext.setAttribute(TICKET_DAO, (MySqlTicketDao) ds::getConnection);
    }

/*    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }*/
}
