/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficacircular;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HikingCarrot
 */
public class LaminaGrafica extends JPanel
{
    private JButton graficar;
    private JTextField[] colores;
    private JLabel[] nomColores;
    private JLabel titulo;
    private boolean pintar = false;
    
    private final GraficaCircular graficaCircular;
    private final String[] nombresColores = {"Rojo", "Verde", "Azul"};
    
    public LaminaGrafica(GraficaCircular graficaCircular)
    {
        this.graficaCircular = graficaCircular;
        
        setLayout(null);
        
        iniciarElementos();
        anadirElementos();
    }
    
    @Override 
    public void paint(Graphics g)
    {
        super.paint(g);
        
        try
        {
            if (pintar) 
            {
                int[] valores = new int[colores.length];
                int total = 0;

                for (int i = 0; i < valores.length; i++) 
                    total += valores[i] = Integer.parseInt(colores[i].getText());
                
                for (int i = 0; i < valores.length; i++) 
                    valores[i] = valores[i] * 360 / total;
                
                g.setColor(Color.red);
                g.fillArc(graficaCircular.getWidth()/2 - 100, 80, 200, 200, 0, valores[0]);
                
                g.setColor(Color.green);
                g.fillArc(graficaCircular.getWidth()/2 - 100, 80, 200, 200, valores[0], valores[1]);
                
                g.setColor(Color.blue);
                g.fillArc(graficaCircular.getWidth()/2 - 100, 80, 200, 200, valores[0] + valores[1], valores[2]);
            }
        
        }catch(NumberFormatException e)
        {
            System.out.println("Alguno de los campos no se rellenó correctamente");
        }
    }
    
    public void iniciarElementos()
    {
        graficar = new JButton(new AbstractAction("Graficar!")
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                pintar = true;
                
                repaint();
            }
        });
        
        colores = new JTextField[3];
        
        for(int i = 0; i < colores.length; i++)
            colores[i] = new JTextField();
        
        nomColores = new JLabel[3];
        
        for (int i = 0; i < nomColores.length; i++) 
            nomColores[i] = new JLabel(nombresColores[i]);
        
        titulo = new JLabel("Gráficas locas!");
    }
    
    private void anadirElementos()
    {
        titulo.setFont(new Font("serif", Font.BOLD, 25));
        titulo.setBounds(graficaCircular.getWidth()/2 - 100, graficaCircular.getHeight()/2 - 250, 200, 40);
        graficar.setBounds(450, 430, 120, 30);
        
        for (int i = 0; i < nomColores.length; i++) 
        {
            nomColores[i].setBounds(50, 350 + i * 25, 80, 25);
            add(nomColores[i]);
        }
        
        for (int i = 0; i < colores.length; i++) 
        {
            colores[i].setBounds(100, 350 + i * 25, 120, 25);
            add(colores[i]);
        }
        
        add(graficar);
        add(titulo);
    }
}