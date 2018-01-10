package com.lue.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lue
 */
public class SqLiteConnect {

    static String path = "";

    public static Connection connect() {
        Connection conn = null;
        try {
            SqLiteConnect ob = new SqLiteConnect();
            ob.display();
            String url = "jdbc:sqlite:" + path;
            conn = DriverManager.getConnection(url);
            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException ex) {
            Logger.getLogger(SqLiteConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }

    public void display() throws IOException {
        path = new File("movementdynamics.db").getCanonicalPath();
        File file = new File(getClass().getClassLoader().getResource("movementdynamics.db").getFile());
    }
}
