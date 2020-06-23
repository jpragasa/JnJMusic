
package data;

// Gets necessary Classes for testing and collecting objects within a list
import java.sql.SQLException;
import java.util.List;

public interface Repository<T, ID>
{
    /**
     * The Repository Inteface acts as a general template for the applied models
     * This interface is a generic that takes in a Type of the implementors choice
     * and an Integer Object.
     * functions:
     * T findById(ID id): function to be implemented of type Type (specified by implementor)
     * @params integer value
     * List<T> findAll(): function of a specified type that searches through the list of that type.
     * @params may throw an SQLException
     * update(ID id): function template that updates the repository of the database.
     * delete(ID upc): function template that deletes an item in the repository.
     * NOTE: functions must be defined when implemented
     */
    //T findById(ID id);
    // Template for finding an instrument in the database through it's id
    T findById(ID i);
    // Template for getting all the items within the database
    List<T> findAll() throws SQLException;
    void update(ID id);
    // Template for adding to a database
    void save (T obj);
    //void delete(T obj);
    // Template for deleting from a database
    void delete(ID upc);
}
