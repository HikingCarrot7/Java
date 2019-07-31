/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class Pruebas2
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("Inserte un número");
        int i = in.nextInt();

        assert (i < 10);

    }

}
