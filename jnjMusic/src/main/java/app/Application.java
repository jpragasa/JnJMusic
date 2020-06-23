package app;

// Imported Classes for scanner and file along with the appropriate exceptions for it.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Application
{
    /**
     * This Application abstract class implements basic method/functions
     * used throughout the program. Mostly used for taking in user input or
     * exiting the program.
     *
     * functions:
     * getScanner() : returns a single scanner object to be used for all occurrences of the scanner object.
     *
     * getScanner(File) : used when implementing file I/O. NOTE: File I/O Classes are not implemented in
     * the current program. It is there for possible implementation if need be.
     * @param File Object
     * @exception throw FileNotFoundException if method is implemented.
     *
     * exitSystem(): Allows for easy exiting of the program when an input from the user is not matched
     * or simply wants to exit when prompted.
     * @exception throw an exception, but highly unlikely.
     *
     * @author Joshua Ragasa
     * @author Jean Adolph II
     *
     * @version v1
     */
    private Scanner scanner = new Scanner(System.in);
    private Scanner fileScanner;

    // Returns the encapsulated scanner
    public Scanner getScanner()
    {
        return this.scanner;
    }

    // Returns the encapsulated scanner, takes in a File Object
    public Scanner getScanner(File file) throws FileNotFoundException
    {
        this.fileScanner = new Scanner(file);
        return this.fileScanner;
    }

    // Allows for easy exiting of the program.
    public void exitSystem()
    {
        try
        {
            System.out.println("Exiting the Instrument Management System.....");
            Thread.sleep(2000);
            System.out.println("You have exited IMS. Come back soon!");
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
