package ua.com.juja.sqlcmd.model;

import java.util.List;

/**
 * Created by oleksandr.baglai on 19.12.2015.
 */
public interface UserActionsDao {
    void log(String userName, String dbName, String action);

    List<UserAction> getAllFor(String userName);
}
