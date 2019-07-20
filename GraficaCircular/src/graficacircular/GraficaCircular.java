/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficacircular;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author HikingCarrot
 */
public class GraficaCircular extends JFrame
{
    private LaminaGrafica laminaGrafica;
    
    public GraficaCircular()
    {
        setSize(new Dimension(600, 500));
        laminaGrafica = new LaminaGrafica(this);
        setLocationRelativeTo(null);
        setTitle("Gráficas!");
        setResizable(false);
        add(laminaGrafica);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        new GraficaCircular();
    }
    
}
