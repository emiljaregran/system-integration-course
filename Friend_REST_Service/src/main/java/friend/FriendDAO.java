package friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class FriendDAO implements IFriendDAO
{
    private final List<Friend> friends = new ArrayList<>();
    
    public FriendDAO()
    {
        friends.add(new Friend(0, "Baran Lundgren", "Barr", new Date(1988 - 1900, 1, 12), "08-984934", "0911-6656594", "042-6794086"));
        friends.add(new Friend(1, "Nataliä Bengtsson", "Natt", new Date(1974 - 1900, 2, 23), "042-6794086"));
        friends.add(new Friend(2, "Michel Svensson", "Micke", new Date(2001 - 1900, 3, 16), "0140-7737207"));
        friends.add(new Friend(3, "Zeinab Lund", "Zeb", new Date(1969 - 1900, 2, 28), "0911-6656594", "042-6794086"));
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
    
    @Override
    public void add(Friend friend)
    {
        friends.add(friend);
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
    }
}
