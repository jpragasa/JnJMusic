/**
 * The PostgresConnectionUtil class extends the ConnectionUtils abstract class.
 * before accessing it's constructor, it adds the appropriate driver for accessing
 * SQL Databases from the DriverManager Class.
 *
 * Constructor:
 *
 * PostgresConnectionUtil(): sets the appropriate credential information and database url to the connectionUtils
 * abstract Class to be used.
 * @params String url, String username, String password, String schema, String instrumentTable
 *
 * functions:
 *
 * getConnection(): returns a Connection Object where the url, username, and password that was set by
 * the PostgresConnectionUtil constructor is passed in.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil extends ConnectionUtils
{
    // Ensures there is an appropriate driver to be used to access the database.
    static
    {
        try
        {
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    // Sets the appropriate credentials to be used to access the database.
    public PostgresConnectionUtil()
    {
        this.url = System.getenv("url");
        this.username = System.getenv("name");
        this.password = System.getenv("password");
        //System.out.println(String.format("url: %s \nname:%s \npass:%s",url,username,password));
    }


    // Returns a Connection object that has the credentials (url, username, password) passed in that was set by
    // the PostgresConnectionUtil.
    @Override
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }
}
