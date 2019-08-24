package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author lbojor
 */
public class DAOCliente extends DAOGeneral<Cliente>
{

    public DAOCliente()
    {
    }

    @Override
    public int agregar(Cliente e) throws SQLException
    {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "INSERT INTO clientes (id_clientes, nombre, fechaIngreso, activo)"
                + "VALUES (" + e.getClaveCliente() + ",'" + e.getNombre() + "','"
                + e.getFechaIngreso().toString() + "'," + e.isActivo() + ")";

        try (Statement sentencia = con.createStatement())
        {
            numFilas = sentencia.executeUpdate(orden);
        }
        
        cerrarConeccion(con);
        
        return numFilas;
    }

    @Override
    public int eliminar(String condicion) throws SQLException
    {
        int numFilas = 0;
        Connection con = getConeccion();

        String orden = "DELETE FROM clientes WHERE " + condicion;

        try (Statement sentencia = con.createStatement())
        {
            numFilas = sentencia.executeUpdate(orden);
        }
        
        cerrarConeccion(con);
        
        return numFilas;
    }

    @Override
    public int modificar(Cliente e, String condicion) throws SQLException
    {
        int numFilas;
        Connection con = getConeccion();

        String orden = "UPDATE clientes SET "
                + " nombre='" + e.getNombre() + "',"
                + "fechaIngreso " + e.getFechaIngreso().toString() + "',"
                + " activo = " + e.isActivo()
                + " WHERE " + condicion;

        try (Statement sentencia = con.createStatement())
        {
            numFilas = sentencia.executeUpdate(orden);
        }
        
        cerrarConeccion(con);
        
        return numFilas;
    }

    @Override
    public ArrayList<Cliente> consultar(String condicion) throws SQLException
    {
        ArrayList<Cliente> lista = new ArrayList<>();
        Cliente e;
        Connection con = getConeccion();
        String orden = "SELECT * FROM Clientes "
                + (condicion == null || condicion.length() == 0 ? "" : "WHERE " + condicion);
        
        try (Statement sentencia = con.createStatement())
        {
            ResultSet rs = sentencia.executeQuery(orden);
            
            while (rs.next())
            {
                e = new Cliente(rs.getInt("id_clientes"), rs.getString("nombre"), rs.getDate("fechaIngreso"), rs.getBoolean("activo"));
                lista.add(e);
            }
        }
        cerrarConeccion(con);
        return lista;
    }
}
