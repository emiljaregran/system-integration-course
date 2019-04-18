package friend;

import java.io.*;
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
        
        friends.add(new Friend(0, "Baran Lundgren", "Barr", "12 Januari", "08-984934", "0911-6656594", "042-6794086"));
        friends.add(new Friend(1, "Nataliä Bengtsson", "Natt", "23 Februari", "042-6794086"));
        friends.add(new Friend(2, "Michel Svensson", "Micke", "16 Mars", "0140-7737207"));
        friends.add(new Friend(3, "Zeinab Lund", "Zeb", "28 Februari", "0911-6656594", "042-6794086"));
        friends.add(new Friend(4, "Gottfrid Olsson", "Gotte", "25 Oktober", "0922-8572039"));
        
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
