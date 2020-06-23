package data;


import models.Users;
import org.apache.log4j.Logger;
import utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserRepo implements Repository<Users, String> {
    /**
     * The UserRepo queries the database and obtains instrument information.
     *
     * functions:
     * findById(String i) : finds instrument by user
     * findAll(): gets all available instruments
     * update(): adds new instrument
     * delete(): deletes instrument
     *
     */

    final static Logger logger = Logger.getLogger(UserRepo.class);
    private ConnectionUtils connectionUtils;

    public UserRepo(ConnectionUtils connectionUtils)
    {
        if(connectionUtils != null)
        {
            this.connectionUtils = connectionUtils;
        }
    }
    @Override
    // Attempts to query the database to find the instrument with the specified identification number.
    public Users findById(String i)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("select * from  Users where email = '%s'", i);
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next())
            {
                String email = rs.getString("email");
                String passkey = rs.getString("passphrase");
                String phone = rs.getString("phone");
                String primaryIn = rs.getString("primaryIn");
                Boolean admin = rs.getBoolean("admin_rights");
                return new Users(email, passkey, phone, primaryIn, admin);
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
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    // Queries the database, gets all the instruments, and stores it within the List Object.
    @Override
    public List<Users> findAll()
    {
        Connection connection = null;
        List<Users> users = new ArrayList<Users>();
        try
        {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String instrumentTable = connectionUtils.getInstrumentTable();
            String sql = String.format("select * from users");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                String passphrase = rs.getString("passphrase");
                String primaryIn = rs.getString("primaryIn");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Boolean admin_rights = rs.getBoolean("admin_rights");
                users.add(new Users(email,passphrase, phone, primaryIn, admin_rights));
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
                    e.printStackTrace();
                }
            }
        }
        return users;
    }


    // Queries the database to allow a new instrument to be added.
    @Override
    public void update(String p) {
        return;
//        Connection connection = null;
//        try
//        {
//            connection = connectionUtils.getConnection();
//            String sql = String.format("update set available  false where upc = %i"+p);
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(sql);
//        }
//        catch(SQLException e)
//        {
//            //e.printStackTrace();
//            // Will throw an exception anyway due to being a void method.
//        }
//        finally
//        {
//            if(connection != null)
//            {
//                try
//                {
//                    connection.close();
//                } catch (SQLException e)
//                {
//                    //e.printStackTrace();
//                    // Will throw an exception anyway due to being a void method.
//                }
//            }
//        }
//    }
    }


    @Override
    public void save(Users obj)
    {

        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("insert into Users (email, passphrase,phone,primaryIn," +
                            "admin_rights) values ('%s', '%s','%s' ,'%s' ,false)",
                    obj.getEmail(), obj.getPassword(), obj.getPhone(), obj.getPrimaryIn());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
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
                    e.printStackTrace();
                    // Will throw an exception anyway due to being a void method.
                }
            }
        }
    }

    // Queries the database to allow the removal of an instrument.
    @Override
    public void delete(String email)
    {
        Connection connection = null;
        try
        {
            connection = connectionUtils.getConnection();
            String sql = String.format("delete from users where email = '%s'",email);
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
}

