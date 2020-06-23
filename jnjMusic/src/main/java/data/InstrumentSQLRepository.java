
package data;

import app.Application;
import com.sun.org.apache.xpath.internal.operations.Bool;
import models.InstrumentModel;
import org.apache.log4j.Logger;
import utils.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class InstrumentSQLRepository implements Repository<InstrumentModel,String>
{
    /**
     * the InstrumentSQLRepository extends the Application abstract class
     * and implements the Repository Interface.
     * The main use for this class is to query different functions to the database.
     *
     * functions:
     *
     * findById(int i): This function queries the database and looks for the instrument within the database.
     * It will show nothing, if id is not found. It returns an InstrumentModel object.
     * @params takes in an integer
     *
     * findAll(): This function queries the database and stores all the instruments found within the database.
     * The function returns a List object that stores type InstrumentModel.
     *
     * update(): This function allows you to query and add a new instrument to the database.
     *
     * delete(): This function allows you to query and delete an instrument within the database by accessing
     * it's identification number and instrument name.
     *
     * Setter Functions: setId(), setId(int i), setInstrumentName(), setUsed(), setPrice()
     *
     * These functions differ from the InstrumentModel getter and setter methods by allowing the user to
     * input data that is to be inserted into the database. They are then called within some of
     * the main functions that need them (update(), delete()).
     */
    final static Logger logger = Logger.getLogger(InstrumentSQLRepository.class);
    private ConnectionUtils connectionUtils;

    // This constructor tests if there is a connection, if not, it provides a connection.
    public InstrumentSQLRepository(ConnectionUtils connectionUtils)
    {
        if(connectionUtils != null)
        {
            this.connectionUtils = connectionUtils;
        }
    }

    // Attempts to query the database to find the instrument with the specified identification number.
    @Override
    public InstrumentModel findById(String i)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("select UPC,sale, imageURL, available, category_id, " +
                    "cat.category_name, price " +
                    "from instruments join categories as cat on cat.category_id = " +
                    "instruments.category where UPC = '%s' and available != false ", i);
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next())
            {
                String upc = rs.getString("UPC");
                String saleName = rs.getString("sale");
                String imageurl0 = rs.getString("imageURL");
                URL imageurl = null;
                try {
                    imageurl = new URL(imageurl0);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Integer cat = rs.getInt("category_id");
                String category_name = rs.getString("category_name");
                float price = rs.getFloat("price");
                Boolean available = rs.getBoolean("available");
                return new InstrumentModel(upc, saleName, cat,
                        category_name, price, available, imageurl);
            }
        }
        catch(SQLException e)
        {
            logger.error("Somethind dun goofed", e);
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    // Queries the database, gets all the instruments, and stores it within the List Object.
    @Override
    public List<InstrumentModel> findAll()
    {
        Connection connection = null;
        List<InstrumentModel> instruments = new ArrayList<InstrumentModel>();
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format(" select UPC, sale, imageURL, available, " +
                    "category_id, cat.category_name, " +
                    "price from instruments join categories as cat on " +
                    "cat.category_id = instruments.category where available != false");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                String upc = rs.getString("UPC");
                String saleName = rs.getString("sale");
                String imageurl0 = rs.getString("imageURL");
                URL imageurl = null;
                try {
                    imageurl = new URL(imageurl0);
                } catch (MalformedURLException e) {
                   // e.printStackTrace();
                }
                String  category_name = rs.getString("category_name");
                float price = rs.getFloat("price");
                Integer cat = rs.getInt("category_id");
                Boolean available = rs.getBoolean("available");
                instruments.add(new InstrumentModel(upc, saleName, cat,
                       category_name, price, available, imageurl));
            }
        }
        catch(SQLException e)
        {
            logger.error("Something dun goofed ", e);
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    logger.error("Something dun goofed ", e);
                }
            }
        }
        return instruments;
    }


        // Queries the database to allow a new instrument to be added.
    @Override
    public void update(String upc)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("update instruments set available = false where upc = %s" + upc);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
            // Will throw an exception anyway due to being a void method.
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }

    @Override
    public void save(InstrumentModel obj)
    {

        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            try {
                String sql = String.format("insert into categories (category_id, category_name) " +
                        "values (%s,'%s')",obj.getCat(), obj.getCatName());
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
            String sql = String.format("insert into instruments (UPC,sale,category, imageURL, available, price) " +
                    "values (%s,'%s',  %d, '%s', true, %f)",obj.getUPC(),obj.getSale(),obj.getCat(),obj.getImage_url(),obj.getPrice() );
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }

    // Queries the database to allow the removal of an instrument.
    @Override
    public void delete(String upc)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("delete from instruments where upc = %s",upc);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        }
        catch(SQLException e)
        {
            //e.printStackTrace();
            // Will throw an exception anyway due to being a void method.
        }
        finally
        {
            if(connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    //e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }
    public void bindCats()
    {

    }
}
