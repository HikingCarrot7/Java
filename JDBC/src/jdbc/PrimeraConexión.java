package jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;

/**
 *
 * @author Mohammed
 */
public class PrimeraConexión
{

    private final File file;
    private final Formatter out;
    private final String ruta = "res/Consultas.txt";
    private final String user = "uxiuizeg2rftim08";
    private final String password = "wlxehTl3kegqHF5ztqga";
    private final String name = "bn72yv26h0nlpnscf1tt";
    private final String host = "bn72yv26h0nlpnscf1tt-mysql.services.clever-cloud.com";

    public static void main(String[] args)
    {
        try
        {
            new PrimeraConexión().conexion();

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public PrimeraConexión() throws IOException
    {
        file = new File(ruta);

        if (!file.exists())
            file.createNewFile();

        out = new Formatter(new FileWriter(ruta, true));
    }

    public void conexion()
    {
        try
        {

            String datos, lineSeparator = System.getProperty("line.separator");

            //SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname', 'port')
            //1. Crear la conexión
            //Connection miConexion = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + name + "?serverTimezone=UTC&useSSL=false", user, password);
            Connection miConexion = DriverManager.getConnection("jdbc:mysql://uxiuizeg2rftim08:wlxehTl3kegqHF5ztqga@bn72yv26h0nlpnscf1tt-mysql.services.clever-cloud.com:3306/bn72yv26h0nlpnscf1tt?serverTimezone=UTC&useSSL=false");

            //2. Crear un objeto statement
            Statement miStatement = miConexion.createStatement();

            //3. Ejecutar sql
            ResultSet miResultSet = miStatement.executeQuery("SELECT DISTINCT SECCIÓN, SUM(PRECIO) AS SUMA_PRECIO FROM PRODUCTOS GROUP BY SECCIÓN ORDER BY SECCIÓN ASC");

            //4. Leer el ResultSet
            while (miResultSet.next())
            {
                //System.out.printf("%-25s$%,-25.2f%s\n", miResultSet.getString("NOMBREARTÍCULO"), Float.parseFloat(miResultSet.getString("PRECIO")), miResultSet.getString("SECCIÓN"));

                datos = String.format("| %-28s| $%-13s%-5s%s", miResultSet.getString("SECCIÓN"), miResultSet.getString("SUMA_PRECIO"), "|", lineSeparator);

                for (int i = 0; i < 48; i++)
                    datos += "-";

                datos += lineSeparator;

                System.out.printf(datos);

                out.format("%s", datos);

            }

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());

        } finally
        {
            out.close();
        }
    }

}
