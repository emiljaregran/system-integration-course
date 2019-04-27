package friend;

import java.util.List;
import java.util.Date;

interface IFriendDAO 
{
    public List<Friend> getAllFriends();
    public Friend getFriendById(int id);
    
    public void add(Friend friend);
    public void addFriend(String name, String nickname, Date birthday, String phonenumber);
    
    public void updateFriendById(Friend friend);
    public void deleteFriendById(int id);
}
