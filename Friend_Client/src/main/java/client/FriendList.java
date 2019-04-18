package client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FriendList implements Serializable
{
    private static final long serialVersionUID = 1L;
    private List<Friend> friends = new ArrayList<>();
    
    public FriendList() {}
    
    public FriendList(List<Friend> friends)
    {
        this.friends = friends;
    }

    public List<Friend> getFriends()
    {
        return friends;
    }
    
    public void setFriends(List<Friend> friends)
    {
        this.friends = friends;
    }
}
