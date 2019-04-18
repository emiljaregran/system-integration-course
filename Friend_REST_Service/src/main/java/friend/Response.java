package friend;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "response")

public class Response implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String message;
    private boolean status;
    
    public Response() {}
    
    public Response(String message, boolean status)
    {
        this.message = message;
        this.status = status;
    }
    
    @XmlElement
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    @XmlElement
    public boolean getStatus()
    {
        return status;
    }
    
    public void setStatus(boolean status)
    {
        this.status = status;
    }
}
