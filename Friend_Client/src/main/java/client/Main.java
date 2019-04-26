package client;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class Main
{
    private final static ClientConfig clientConfig = new DefaultClientConfig();
    private final static Client client = Client.create(clientConfig);
    private final static WebResource service = client.resource(UriBuilder.fromUri("http://localhost:20778/Friend_REST_Service").build());
    
    private Main()
    {
        while (true)
        {
            printMenu();
        
            switch (getMenuChoice())
            {
                case 1:
                    searchFriend();
                    break;

                case 2:
                    addFriend();
                    break;

                case 3:
                    updateFriend();
                    break;

                case 4:
                    deleteFriend();
                    break;

                case 5:
                    listFriends();
                    break;
            }
        }
    }
    
    private void searchFriend()
    {
        int id = getUserInputInt("ID");
        
        String json = service.path("rest/FriendService/friendByIdJSON/" + id).accept(MediaType.APPLICATION_JSON).get(String.class);
        Friend friend = new Gson().fromJson(json, Friend.class);
        
        System.out.println("\nName: " + friend.getName());
        System.out.println("Nickname: "+ friend.getNickname());
        System.out.println("Birthday: " + friend.getBirthday());
        System.out.println("Phone numbers: " + friend.getPhonenumbers());
    }
    
    private void addFriend()
    {
        int id = getUserInputInt("ID");
        String name = getUserInput("name");
        String nickname = getUserInput("nickname");
        String birthday = getUserInput("birthday");
        String phonenumber = getUserInput("phone number");
        
        Friend newFriend = new Friend(id, name, nickname, birthday, phonenumber);
        
        ClientResponse clientResponse = service.path("rest/FriendService/friend/add").accept(MediaType.APPLICATION_XML).post(ClientResponse.class, newFriend);
        Response response = clientResponse.getEntity(Response.class);
        
        System.out.println("\n" + response.getMessage() + ": " + response.getStatus());
    }
    
    private void updateFriend()
    {
        int id = getUserInputInt("ID");
        String name = getUserInput("name");
        String nickname = getUserInput("nickname");
        String birthday = getUserInput("birthday");
        String phonenumber = getUserInput("phone number");
        
        Friend newFriend = new Friend(id, name, nickname, birthday, phonenumber);
        
        ClientResponse clientResponse = service.path("rest/FriendService/friend/upsert").accept(MediaType.APPLICATION_XML).post(ClientResponse.class, newFriend);
        Response response = clientResponse.getEntity(Response.class);
        
        System.out.println("\n" + response.getMessage() + ": " + response.getStatus());
    }
    
    private void deleteFriend()
    {
        int id = getUserInputInt("ID");
        
        String json = service.path("rest/FriendService/friendByIdJSON/" + id + "/delete").accept(MediaType.APPLICATION_JSON).get(String.class);
        Response response = new Gson().fromJson(json, Response.class);
        
        System.out.println("\n" + response.getMessage() + ": " + response.getStatus());
    }
    
    private void listFriends()
    {
        String json = service.path("rest/FriendService/getAllFriendsJSON").accept(MediaType.APPLICATION_JSON).get(String.class);
        FriendList friendList = new Gson().fromJson(json, FriendList.class);
        List<Friend> friends = friendList.getFriends();
        
        for (Friend friend : friends)
        {
            System.out.println("Id: " + friend.getId());
            System.out.println("Name: " + friend.getName());
            System.out.println("Nickname: " + friend.getNickname());
            System.out.println("Birthday: " + friend.getBirthday());
            System.out.println("Phone numbers: " + friend.getPhonenumbers().toString());
            System.out.println("");
        }
    }
    
    private void printMenu()
    {
        System.out.println("\nFriend system 1.0");
        System.out.println("--------------------");
        System.out.println("1. Search for friend");
        System.out.println("2. Add friend");
        System.out.println("3. Update friend");
        System.out.println("4. Delete friend");
        System.out.println("5. List all friends\n");
    }
    
    private String getUserInput(String text)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter " + text + ": ");
        
        return scanner.nextLine();
    }
    
    private int getUserInputInt(String text)
    {
        Scanner scanner = new Scanner(System.in);
        int result;

        do
        {
            System.out.print("\nEnter " + text + ": ");
            while (!scanner.hasNextInt())
            {
                System.out.print("\nEnter " + text + ": ");
                scanner.next();
            }
            
            result = scanner.nextInt();
        } while (result < 0);

        return result;
    }
    
    private int getMenuChoice()
    {
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;
        
        while (menuChoice < 1 || menuChoice > 5)
        {
           try
           {
               System.out.print("Enter your choice: ");
               menuChoice = scanner.nextInt();
           }
           catch (InputMismatchException e)
           {
               System.out.println("Wrong input, please try again.");
               scanner.next();
           }
        }
        
        return menuChoice;
    }
    
    public static void main(String[] args)
    {
        new Main();
    }
}
