package javadesdecero;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Formulario extends JFrame
{

    private final JTextField textfield1;
    private final JTextArea jtextarea1;

    public Formulario()
    {
        setLayout(null);
        textfield1 = new JTextField();
        textfield1.setBounds(10, 10, 200, 30);
        add(textfield1);

        jtextarea1 = new JTextArea();
        jtextarea1.setBounds(10, 50, 400, 300);
        add(jtextarea1);

    }

    public static void main(String args[])
    {

        Formulario textarea1 = new Formulario();
        textarea1.setBounds(0, 0, 540, 400);
        textarea1.setVisible(true);
        textarea1.setResizable(false);
        textarea1.setLocationRelativeTo(null);
        textarea1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
