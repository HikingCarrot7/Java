package jdbc;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author HikingCarrot7
 */
public final class MostrarDatos extends JFrame
{

    private File file;
    private JPanel panel;
    private JScrollPane scroll;
    private JTextArea area;

    public MostrarDatos() throws IOException
    {
        iniciarElementos();

        iniciarConexion();

        iniciarVentana();

    }

    public void iniciarElementos() throws IOException
    {
        setLayout(new BorderLayout());
        area = new JTextArea();

        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        panel = new JPanel();
        scroll = new JScrollPane(area);

        panel.setLayout(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);

        file = new File("res/Consulta2.txt");

        if (!file.exists())
            file.createNewFile();

    }

    public void iniciarConexion() throws IOException
    {
        String query = "SELECT * FROM CLIENTES LEFT JOIN PEDIDOS ON CLIENTES.CÓDIGOCLIENTE = PEDIDOS.CÓDIGOCLIENTE WHERE PEDIDOS.NÚMERODEPEDIDO IS NULL";

        try
        {
            String datos, aux = "", lineSeparator = System.getProperty("line.separator");

            ResultSet resultados = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql?serverTimezone=UTC&useSSL=false", "root", "").createStatement().executeQuery(query);

            aux += String.format("%-30s%-20s%-20s%s%5$s%5$s", "EMPRESA", "POBLACIÓN", "TELÉFONO", "RESPONSABLE", lineSeparator);

            area.append(aux);

            while (resultados.next())
            {
                datos = String.format("%-30s%-20s%-20s%s%s", resultados.getString("EMPRESA"),
                        resultados.getString("POBLACIÓN"),
                        resultados.getString("TELÉFONO"),
                        resultados.getString("RESPONSABLE"),
                        lineSeparator);

                aux += datos;

                area.append(datos);

                System.out.println(datos);

            }

            WriteFile(aux);

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void iniciarVentana()
    {

        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void WriteFile(String data) throws IOException
    {
        try (Formatter out = new Formatter(new FileWriter(file, true)))
        {
            out.format("%s", data);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            new MostrarDatos();

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
