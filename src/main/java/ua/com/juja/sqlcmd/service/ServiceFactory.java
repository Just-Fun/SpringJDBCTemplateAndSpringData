package ua.com.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by oleksandr.baglai on 13.11.2015.
 */
public class ServiceFactory {

    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
