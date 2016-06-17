package servlets;

import dao.interfaces.PersonDao;
import lombok.extern.log4j.Log4j;
import model.Person;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static listeners.DbInit.PERSON_DAO;
import static model.Person.Role.ClIENT;

@Log4j
@WebServlet("/authorization")
public class Authorization extends HttpServlet {
    private PersonDao personDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        personDao = (PersonDao) config.getServletContext().getAttribute(PERSON_DAO);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (Boolean.parseBoolean(request.getParameter("logout"))) {
            try {
                request.logout();
                log.info("Client logged out");
            } catch (ServletException e) {
                log.error("Logout failed", e);
            }
            response.sendRedirect("/");
        } else if (Boolean.parseBoolean(request.getParameter("login"))) {
            if (request.getUserPrincipal() == null) {
                String j_username = request.getParameter("j_username");
                try {
                    request.login(j_username, request.getParameter("j_password"));
                } catch (ServletException e) {
                    log.error("Login for " + j_username + " failed", e);
                }
                // Redirect to error page if login and/or password is not correct
                if (request.getUserPrincipal() != null) {
                    String referer = request.getHeader("referer");
                    System.out.println("Referer is " + referer);
                    log.info(j_username + "is logged in");
                    response.sendRedirect("/");
                } else {
                    log.info("Login and/or password incorrect. New attempt will follow");
                    response.sendRedirect("/error.jsp");
                }
            }
        } else if (Boolean.parseBoolean(request.getParameter("signUp"))) {

/*            for (Map.Entry<String, String[]> stringEntry : request.getParameterMap().entrySet())
                System.out.println("Parameter name is: " + stringEntry.getKey() + " value is: " + stringEntry.getValue()[0]);*/

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dayOfDob = request.getParameter("day");
            String monthOfDob = request.getParameter("month");
            String yearOfDob = request.getParameter("year");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            boolean isFormDataCorrect = true;

            if (personDao.getPersonByEmail(email).isPresent()) {
                request.setAttribute("emailIsUsed", true);
                isFormDataCorrect = false;
                log.info("Sign-up: " + email + " is already used");
            }
            if (!password.equals(confirmPassword)) {
                request.setAttribute("passwordNotConfirmed", true);
                isFormDataCorrect = false;
                log.info("Sign-up: passwords don't match");
            }

            if (isFormDataCorrect) {
                // new client

                LocalDate dateOfBirth = null;
                if (!(dayOfDob.isEmpty() | monthOfDob.isEmpty() | yearOfDob.isEmpty()))
                    dateOfBirth = LocalDate.parse(yearOfDob + "-" + monthOfDob + "-" + dayOfDob);

                Person person = new Person(firstName, lastName, dateOfBirth, email, password, address, phone);
                personDao.add(person);
                log.info("Added new person: " + person);
                personDao.addRole(email, ClIENT.name());
                log.info("Added role: " + email + " - " + ClIENT.name());
                request.login(email, password);
                log.info(email + " was logged in");

                response.sendRedirect("/");
            } else {
                // form data is(are :) not correct - redirect to the same page
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("day", dayOfDob);
                request.setAttribute("month", monthOfDob);
                request.setAttribute("year", yearOfDob);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.getRequestDispatcher("/sign-up.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
