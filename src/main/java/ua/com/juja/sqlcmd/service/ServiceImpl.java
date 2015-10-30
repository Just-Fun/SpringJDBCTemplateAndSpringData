package ua.com.juja.sqlcmd.service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleksandr.baglai on 30.10.2015.
 */
public class ServiceImpl implements Service {

    @Override
    public List<String> commandsList() {
        return Arrays.asList("help", "menu", "connect");
    }

}
