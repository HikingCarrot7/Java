package com.main;

import com.cliente.Cliente;
import static java.lang.System.out;
import java.net.InetAddress;
import static java.net.InetAddress.getLocalHost;
import java.net.UnknownHostException;

/**
 *
 * @author HikingCarrot7
 */
public class PruebaCliente
{

    public static void main(String[] args)
    {

        try
        {
            new Cliente(getLocalHost().getHostAddress());

        } catch (UnknownHostException ex)
        {
            out.println(ex.getMessage());
        }

    }
}
