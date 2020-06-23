
package files;

// Imports appropriate Classes for File I/O, ArrayList, and Scanner (for user input)
import app.Application;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManipulation extends Application
{
    /**
     * NOTE: This Class is not implemented in version 1 of the program due to the functionality being
     * better implemented using databases. Implement at your own discretion.
     *
     * The FileManipulation Class serves to mainly read to a file or add to a text file.
     *
     * Constructor: sets the FileReader and BufferReader to null
     *
     * functions:
     *
     * pushToStock(): takes in a String Object with the filePath and adds user input into the text file
     * @params String filename
     *
     * readStock(): takes in a String Object, reads from the text file from the provided filepath.
     * @params String filename
     *
     * getTestFile(): returns a String Object that has a specified filepath.
     */
    private FileReader reader;
    private BufferedReader bReader;
    private FileWriter fw;
    private BufferedWriter bw;
    private String line = "";
    private String testFile = "./resources/testFile.txt";
    private ArrayList<String> fileContent;

    // Sets the FileReader and BufferedReader.
    public FileManipulation()
    {
        this.reader = null;
        this.bReader = null;
    }


    // Adds user input into the text file specified by the passed in String Object fileName.
    public void pushToStock(String fileName) throws IOException
    {
        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            Scanner scanner = super.getScanner();
            if (this.fw == null)
            {
                System.out.println("Current Stock:");
                readStock(this.testFile);
                this.fw = new FileWriter(fileName, true);
                this.bw = new BufferedWriter(this.fw);
                this.fileContent = new ArrayList<String>();
                while((line = bReader.readLine()) != null)
                {
                    this.fileContent.add(line);
                }
                for(String i : this.fileContent)
                {
                    bw.write(i);
                }
                System.out.println("What would you like to add?\n");
                String input = scanner.nextLine();
                bw.newLine();
                bw.write(input);
                bw.close();
            }
            else
            {
                while((line = bReader.readLine()) != null)
                {

                    this.fileContent.add(line);

                }
                for(String i : this.fileContent)
                {
                    bw.write(i);
                }
                System.out.println("Current Stock:");
                String input = scanner.nextLine();
                bw.newLine();
                bw.write(input);
                bw.close();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    //  Reads the content from the specified filepath in fileName.
    public void readStock(String fileName)
    {
        try
        {
            reader = new FileReader(fileName);
            bReader = new BufferedReader(reader);
            //int index = 0;
            while((line = bReader.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }


    // Returns the filename to be used to access the text file.
    public String getTestFile()
    {
        return this.testFile;
    }
}






