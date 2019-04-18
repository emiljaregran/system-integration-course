package friend;

import java.util.List;

interface IFriendDAO 
{
    public List<Friend> getAllFriends();
    public Friend getFriendById(int id);
    
    public void add(Friend friend);
    public void addFriend(String name, String nickname, String birthday, String phonenumber);
    
    public void updateFriendById(Friend friend);
    public void deleteFriendById(int id);
}
