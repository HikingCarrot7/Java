package snake;

import java.awt.Color;

import javax.swing.JFrame;

public class Main extends JFrame
{

    public Main()
    {
        Gameplay gameplay = new Gameplay();

        setBounds(10, 10, 905, 700);
        setBackground(Color.DARK_GRAY);
        setTitle("Snake");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gameplay);
        addKeyListener(gameplay);
    }

    public static void main(String[] args)
    {
        new Main();
    }

}
