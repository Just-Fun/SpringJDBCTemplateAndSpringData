package ua.com.juja.sqlcmd.service;

import org.springframework.stereotype.Component;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.model.JDBCDatabaseManager;

/**
 * Created by oleksandr.baglai on 13.11.2015.
 */
@Component
public class DatabaseManagerFactoryImpl implements DatabaseManagerFactory {

    @Override
    public DatabaseManager createDatabaseManager() {
        return new JDBCDatabaseManager();
    }
}
