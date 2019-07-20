/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minichat;

/**
 *
 * @author HikingCarrot
 */
public class PruebaChat 
{
    public static void main(String[] args) 
    {
        Servidor s = new Servidor(9999);
        
        new Chat(s, 1);
        new Chat(s , 2);
    }
    
}
