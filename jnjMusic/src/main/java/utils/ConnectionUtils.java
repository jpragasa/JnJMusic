/**
 * The ConnectionUtils abstract class is a template for creating
 * easy access to connecting to the database.
 *
 * functions():
 *
 * getDefaultSchema(): returns the encapsulated & protected schema String Object
 * @return String
 *
 * getInstrumentTable(): returns the encapsulated & protected instrumentTable String Object,
 * This object is to point to the instrument family that the user wants to view/manipulate.
 */
package utils;

// imports the appropriate classes for accessing an SQL Database and it's appropriate Exceptions.
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionUtils
{
    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema;
    protected String instrumentTable;


    // Template for the getConnection() function; defined when implemented.
    public abstract Connection getConnection() throws SQLException;


    // Returns the appropriate schema of the database to be accessed.
    public String getDefaultSchema()
    {
        return this.defaultSchema;
    }


    // Returns the appropriate instrument-family table to be accessed.
    public String getInstrumentTable()
    {
        return this.instrumentTable;
    }
}
