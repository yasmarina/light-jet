package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static servlets.Localization.setLocale;

@WebListener
public class LocaleInit implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        setLocale(se.getSession(), "en");
    }
}
