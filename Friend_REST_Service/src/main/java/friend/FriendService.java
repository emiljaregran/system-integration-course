package friend;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/FriendService")


// http://localhost:20778/HelloWorldREST/rest/FriendService/getAllFriendsXML
// http://localhost:20778/HelloWorldREST/rest/FriendService/friendByIdJSON/2/delete

public class FriendService
{
    //private final static IFriendDAO friendDAO = new FriendDAO();
    //private final static IFriendDAO friendDAO = new FriendSerDAO();
    //private final static IFriendDAO friendDAO = new FriendJSONDAO();
    private final static IFriendDAO friendDAO = new FriendDBDAO();
    
    @POST
    @Path("/friend/add")
    public Response addFriend(Friend friendObject)
    {
        for (Friend friend : friendDAO.getAllFriends())
        {
            if (friend.getId() == friendObject.getId())
            {
                return new Response("Friend added", false);
            }
        }
        
        friendDAO.add(friendObject);
        return new Response("Friend added", true);
    }
    
    @POST
    @Path("/friend/upsert")
    public Response addOrUpdateFriend(Friend friendObject)
    {
        Response response;
        boolean friendIdExist = false;
        
        for (Friend friend : friendDAO.getAllFriends())
        {
            if (friend.getId() == friendObject.getId())
            {
                friendIdExist = true;
            }
        }
        
        if (friendIdExist)
        {
            friendDAO.updateFriendById(friendObject);
            response = new Response("Friend updated", true);
        }
        else
        {
            friendDAO.add(friendObject);
            response = new Response("Friend added", true);
        }
        
        return response;
    }
    
    @GET
    @Path("/friendByIdXML/{id}/delete")
    @Produces (MediaType.APPLICATION_XML)
    public Response deleteFriendByIdXML(@PathParam("id") int id)
    {
        Response response = new Response("Friend deleted", false);
        int indexToDelete = -1;
        
        for (Friend friend : friendDAO.getAllFriends())
        {
            if (friend.getId() == id)
            {
                indexToDelete = id;
            }
        }
        
        if (indexToDelete != -1)
        {
            friendDAO.deleteFriendById(id);
            response.setStatus(true);
        }
        
        return response;
    }
    
    @GET
    @Path("/friendByIdJSON/{id}/delete")
    @Produces (MediaType.APPLICATION_JSON)
    public Response deleteFriendByIdJSON(@PathParam("id") int id)
    {
        Response response = new Response("Friend deleted", false);
        int indexToDelete = -1;
        
        for (Friend friend : friendDAO.getAllFriends())
        {
            if (friend.getId() == id)
            {
                indexToDelete = id;
            }
        }
        
        if (indexToDelete != -1)
        {
            friendDAO.deleteFriendById(id);
            response.setStatus(true);
        }
        
        return response;
    }
    
    @GET
    @Path("/friendByIdXML/{id}")
    @Produces (MediaType.APPLICATION_XML)
    public Friend getFriendByIdXML(@PathParam("id") int id)
    {
        return friendDAO.getFriendById(id);
    }
    
    @GET
    @Path("/friendByIdJSON/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Friend getFriendByIdXJSON(@PathParam("id") int id)
    {
        return friendDAO.getFriendById(id);
    }
    
    @GET
    @Path("/getAllFriendsXML")
    @Produces (MediaType.APPLICATION_XML)
    public List<Friend> getFriendsXML()
    {
        return friendDAO.getAllFriends();
    }
    
    @GET
    @Path("/getAllFriendsJSON")
    @Produces (MediaType.APPLICATION_JSON)
    public FriendList getFriendsJSON()
    {
        return new FriendList(friendDAO.getAllFriends());
    }
    
    @GET
    @Path("/getAllFriends")
    @Produces (MediaType.TEXT_HTML)
    public String getFriendsHTML()
    {
        List<Friend> friends = friendDAO.getAllFriends();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title></title><meta charset=\"UTF-8\"></head><body>");
        
        for (Friend friend : friends)
        {
            stringBuilder.append(friend.getId());
            stringBuilder.append("<br>");
            stringBuilder.append(friend.getName());
            stringBuilder.append("<br>");
            stringBuilder.append(friend.getNickname());
            stringBuilder.append("<br>");
            stringBuilder.append(friend.getBirthday());
            stringBuilder.append("<br>");
            
            List<String> phonenumbers = friend.getPhonenumbers();
            for (String phonenumber : phonenumbers)
            {
                stringBuilder.append("Tel: ");
                stringBuilder.append(phonenumber);
                stringBuilder.append("<br>");
            }
            
            stringBuilder.append("<br><br>");
        }
        
        stringBuilder.append("</body></html>");
        
        return stringBuilder.toString();
    }
}
