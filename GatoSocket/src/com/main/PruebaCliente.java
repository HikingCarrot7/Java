package com.main;

import com.cliente.Cliente;
import java.net.InetAddress;
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
            new Cliente(InetAddress.getLocalHost().getHostAddress());

        } catch (UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
