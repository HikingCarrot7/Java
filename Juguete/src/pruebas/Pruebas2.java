/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Scanner;

import javax.swing.Timer;

/**
 *
 * @author HikingCarrot7
 */
public class Pruebas2
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        new Pruebas2().iniciar();

        try
        {

            System.out.println("Inserte un numero");
            int i = in.nextInt();

            System.out.println("Insertaste el numero: " + i);

        } finally
        {
            in.close();
        }

    }

    private void iniciar()
    {
        Timer timer = new Timer(1000, (e) ->
        {
            System.out.println("Hola desde la clase interna anonima");
        });

        timer.start();
    }

}
