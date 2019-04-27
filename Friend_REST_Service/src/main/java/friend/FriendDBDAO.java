package friend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FriendDBDAO implements IFriendDAO
{
    private Properties settings;
    
    public FriendDBDAO()
    {
        importDatabaseSettings();
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
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
    
    @Override
    public List<Friend> getAllFriends()
    {
        List<Friend> friends = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friend");
            ResultSet result = statement.executeQuery();
            
            while (result.next())
            {
                Friend friend = new Friend();
                friend.setId(result.getInt("id"));
                friend.setName(result.getString("name"));
                friend.setNickname(result.getString("nickname"));
                friend.setBirthday(result.getDate("birthday"));
                friend.setPhonenumber(result.getString("phonenumber"));
                
                friends.add(friend);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return friends;
    }
    
    @Override
    public Friend getFriendById(int id)
    {
        Friend friend = new Friend();
        
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM friend WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next())
            {
                friend.setId(result.getInt("id"));
                friend.setName(result.getString("name"));
                friend.setNickname(result.getString("nickname"));
                friend.setBirthday(result.getDate("birthday"));
                friend.setPhonenumber(result.getString("phonenumber"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return friend;
    }
    
    @Override
    public void add(Friend friend)
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
    
    @Override
    public void addFriend(String name, String nickname, Date birthday, String phonenumber)
    {   
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO friend (name, nickname, birthday, phonenumber) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, nickname);
            statement.setDate(3, new java.sql.Date(birthday.getTime()));
            statement.setString(4, phonenumber);
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateFriendById(Friend friend)
    {
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            PreparedStatement statement = connection.prepareStatement("UPDATE friend SET name=?, nickname=?, birthday=?, phonenumber=? WHERE id=?");
            statement.setString(1, friend.getName());
            statement.setString(2, friend.getNickname());
            statement.setDate(3, new java.sql.Date(friend.getBirthday().getTime()));
            statement.setString(4, friend.getPhonenumber());
            statement.setInt(5, friend.getId());
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    @Override
    public void deleteFriendById(int id)
    {
        try (Connection connection = DriverManager.getConnection(settings.getProperty("connectionString"),
                                                                 settings.getProperty("name"),
                                                                 settings.getProperty("password"));)
        {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM friend WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
