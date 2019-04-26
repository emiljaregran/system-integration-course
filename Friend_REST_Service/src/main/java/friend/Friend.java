package friend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "friend")

public class Friend implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final List<String> phonenumbers = new ArrayList<>();
    private int id;
    private String name;
    private String nickname;
    private String birthday;
    
    public Friend(){}
    
    public Friend(int id, String name, String nickname, String birthday, String... phonenumbers)
    {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.birthday = birthday;
        
        for (String phonenumber : phonenumbers)
        {
            this.phonenumbers.add(phonenumber);
        }
    }
    
    @XmlElement
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    @XmlElement
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
            
    @XmlElement
    public List<String> getPhonenumbers()
    {
        return phonenumbers;
    }
    
    public String getPhonenumber()
    {
        return this.phonenumbers.get(0);
    }
    
    public void setPhonenumber(String phonenumber)
    {
        this.phonenumbers.add(phonenumber);
    }
    
    @XmlElement
    public String getNickname()
    {
        return nickname;
    }
    
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
    
    @XmlElement
    public String getBirthday()
    {
        return birthday;
    }
    
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }
}
