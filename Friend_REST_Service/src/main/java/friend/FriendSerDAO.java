package friend;

import java.io.*;
import java.util.Date;
import java.util.List;

public class FriendSerDAO implements IFriendDAO
{
    private List<Friend> friends;
    private final String FILENAME = "C:\\Users\\emil\\Documents\\Netbeans\\system_integration_course\\Friend_REST_Service\\friends.ser";
    
    public FriendSerDAO()
    {
        readSerFile();
    }
    
    @Override
    public List<Friend> getAllFriends()
    {
        return friends;
    }
    
    @Override
    public Friend getFriendById(int id)
    {
        for (Friend friend : friends)
        {
            if (friend.getId() == id)
            {
                return friend;
            }
        }
        
        return null;
    }
    
    @Override
    public void add(Friend friend)
    {
        friends.add(friend);
        saveSerFile();
    }
    
    @Override
    public void addFriend(String name, String nickname, Date birthday, String phonenumber)
    {
        int id = getHighestId() + 1;
        
        friends.add(new Friend(id, name, nickname, birthday, phonenumber));
    }
    
    @Override
    public void updateFriendById(Friend friend)
    {
        int index = -1;
        
        for (int i = 0; i < friends.size(); i++)
        {
            if (friends.get(i).getId() == friend.getId())
            {
                friends.get(i).setName(friend.getName());
                friends.get(i).setNickname(friend.getNickname());
                friends.get(i).setBirthday(friend.getBirthday());
            }
        }
        
        saveSerFile();
    }
    
    @Override
    public void deleteFriendById(int id)
    {
        for (int i = 0; i < friends.size(); i++)
        {
            if (friends.get(i).getId() == id)
            {
                friends.remove(i);
            }
        }
        
        saveSerFile();
    }
    
    private int getHighestId()
    {
        int highestId = 0;
        
        for (Friend friend : friends)
        {
            if (friend.getId() > highestId)
            {
                highestId = friend.getId();
            }
        }
        
        return highestId;
    }
    
    private void saveSerFile()
    {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILENAME)))
        {
            output.writeObject(friends);
            output.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void readSerFile()
    {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILENAME)))
        {
            friends = (List<Friend>)input.readObject();
        }
        
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
