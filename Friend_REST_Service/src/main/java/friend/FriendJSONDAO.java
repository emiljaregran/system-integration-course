package friend;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.Date;
import java.util.List;

public class FriendJSONDAO implements IFriendDAO
{
    private List<Friend> friends;
    private final Gson gson = new Gson();
    private final String FILENAME = "C:\\Users\\emil\\Documents\\Netbeans\\system_integration_course\\Friend_REST_Service\\friends.json";
    
    public FriendJSONDAO()
    {
        readJsonFile();
    }
    
    @Override
    public List<Friend> getAllFriends()
    {
        System.out.println("friends size: " + friends.size());
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
        saveJsonFile();
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
        
        saveJsonFile();
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
        
        saveJsonFile();
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
    
    private void saveJsonFile()
    {
        String json = gson.toJson(friends);
        
        try (FileWriter writer = new FileWriter(FILENAME))
        {
            writer.write(json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void readJsonFile()
    {   
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME), "UTF-8")))
        {
            friends = gson.fromJson(reader, new TypeToken<List<Friend>>(){}.getType());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
