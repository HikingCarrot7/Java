package sockets.mensaje;

import java.io.Serializable;

/**
 *
 * @author HikingCarrot
 */
public class Mensaje implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String nick, ip, mensaje;
    private boolean control;

    public String getNick()
    {
        return nick;
    }

    public void setNick(String nick)
    {
        this.nick = nick;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getMensaje()
    {
        return mensaje;
    }

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }

    public boolean isControl()
    {
        return control;
    }

    public void setControl(boolean control)
    {
        this.control = control;
    }
}
