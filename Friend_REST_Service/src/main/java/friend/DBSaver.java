package friend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.util.Date;

/*
 ONLY USED FOR INITIALIZING THE DATABASE ONCE! 
*/

public class DBSaver
{
    private Properties settings;
    
    private DBSaver() throws ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        importDatabaseSettings();
        clearDatabase();
        
        insertIntoDatabase(new Friend(0, "Baran Lundgren", "Barr", new Date(2000 - 1900, 2, 4), "08-984934", "0911-6656594", "042-6794086"));
        insertIntoDatabase(new Friend(1, "Nataliä Bengtsson", "Natt", new Date(1976 - 1900, 2, 23), "042-6794086"));
        insertIntoDatabase(new Friend(2, "Michel Svensson", "Micke", new Date(1985 - 1900, 3, 16), "0140-7737207"));
        insertIntoDatabase(new Friend(3, "Zeinab Lund", "Zeb", new Date(1999 - 1900, 2, 28), "0911-6656594", "042-6794086"));
        insertIntoDatabase(new Friend(4, "Gottfrid Olsson", "Gotte", new Date(1978 - 1900, 10, 25), "0922-8572039"));
        
        System.out.println("Wrote friends to database.");
    }
    
    private void importDatabaseSettings()
    {
        try
        {
            settings = new Properties();
            settings.load(new FileInputStream("C:\\Users\\emil\\Documents\\Netbeans\\system_integration_course\\Friend_REST_Service\\Settings.properties"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Settings file not found!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void clearDatabase()
    {
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM friend WHERE id LIKE '%'");
            statement.executeUpdate("ALTER TABLE friend AUTO_INCREMENT = 1");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void insertIntoDatabase(Friend friend)
    {
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO friend (name, nickname, birthday, phonenumber) VALUES (?, ?, ?, ?)");
            statement.setString(1, friend.getName());
            statement.setString(2, friend.getNickname());
            statement.setDate(3, new java.sql.Date(friend.getBirthday().getTime()));
            statement.setString(4, friend.getPhonenumber());
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            new DBSaver();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
