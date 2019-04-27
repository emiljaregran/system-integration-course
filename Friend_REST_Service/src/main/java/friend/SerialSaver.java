package friend;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/*
 ONLY USED FOR INITIALIZING THE SER-FILE ONCE! 
*/

public class SerialSaver
{   
    private SerialSaver() throws Exception
    {
        List<Friend> friends = new ArrayList<>();
        
        friends.add(new Friend(0, "Baran Lundgren", "Barr", new Date(1988 - 1900, 1, 12), "08-984934", "0911-6656594", "042-6794086"));
        friends.add(new Friend(1, "Nataliä Bengtsson", "Natt", new Date(1974 - 1900, 2, 23), "042-6794086"));
        friends.add(new Friend(2, "Michel Svensson", "Micke", new Date(2001 - 1900, 3, 16), "0140-7737207"));
        friends.add(new Friend(3, "Zeinab Lund", "Zeb", new Date(1969 - 1900, 2, 28), "0911-6656594", "042-6794086"));
        friends.add(new Friend(4, "Gottfrid Olsson", "Gotte", new Date(1983 - 1900, 6, 12), "0922-8572039"));
        
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("friends.ser"));
        output.writeObject(friends);
        output.flush();
        System.out.println("Wrote friends to friends.ser");
    }
    
    public static void main(String[] args) throws Exception
    {
        new SerialSaver();
    }
}
