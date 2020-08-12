package controlador;

import DAO.DAOCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import vista.VistaCliente;

/**
 *
 * @author lbojor
 */
public class ControlCliente implements ActionListener
{

    private final Cliente modeloCliente;
    private final VistaCliente vistaCliente;

    public ControlCliente(Cliente modeloCliente, VistaCliente vistaCliente)
    {
        this.modeloCliente = modeloCliente;
        this.vistaCliente = vistaCliente;

        this.vistaCliente.getjButton1().addActionListener(this);
        this.vistaCliente.getjButton2().addActionListener(this);
        this.vistaCliente.getjButton3().addActionListener(this);
        this.vistaCliente.getjButton4().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent evento)
    {
        //Insertar Cliente
        if (vistaCliente.getjButton1() == evento.getSource())
        {
            int claveCliente;
            String nombreCliente;
            Date fechaIngreso;
            String activo;

            claveCliente = Integer.parseInt(vistaCliente.getjTextField1().getText());
            nombreCliente = vistaCliente.getjTextField2().getText();
            fechaIngreso = new Date(01 - 10 - 2018);
            activo = vistaCliente.getjCheckBox1().getText();

            modeloCliente.setClaveCliente(claveCliente);
            modeloCliente.setNombre(nombreCliente);
            modeloCliente.setFechaIngreso(fechaIngreso);
            modeloCliente.setActivo(true);

            DAOCliente daoCliente = new DAOCliente();
            try
            {
                daoCliente.agregar(modeloCliente);

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        //Eliminar Cliente
        if (vistaCliente.getjButton2() == evento.getSource())
        {
            int claveCliente;
            String condicion;
            claveCliente = Integer.parseInt(vistaCliente.getjTextField1().getText());
            condicion = " id_clientes = " + claveCliente;

            DAOCliente daoCliente = new DAOCliente();

            try
            {
                daoCliente.eliminar(condicion);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        //Modificar Cliente
        if (vistaCliente.getjButton4() == evento.getSource())
        {
            int claveCliente;
            String nombreCliente;
            Date fechaIngreso;
            String activo;
            String condicion;

            claveCliente = Integer.parseInt(vistaCliente.getjTextField1().getText());
            nombreCliente = vistaCliente.getjTextField2().getText();
            fechaIngreso = new Date(01 - 10 - 2018);
            activo = vistaCliente.getjCheckBox1().getText();

            modeloCliente.setClaveCliente(claveCliente);
            modeloCliente.setNombre(nombreCliente);
            modeloCliente.setFechaIngreso(fechaIngreso);
            modeloCliente.setActivo(true);

            claveCliente = Integer.parseInt(vistaCliente.getjTextField1().getText());
            condicion = " id_clientes = " + claveCliente;

            DAOCliente daoCliente = new DAOCliente();

            try
            {
                daoCliente.modificar(modeloCliente, condicion);

            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        //Consultar Cliente
        if (vistaCliente.getjButton3() == evento.getSource())
        {
            ArrayList<Cliente> listaClientes = new ArrayList<>();
            int claveCliente;
            String condicion;

            claveCliente = Integer.parseInt(vistaCliente.getjTextField1().getText());
            condicion = " id_clientes = " + claveCliente;

            DAOCliente daoCliente = new DAOCliente();

            try
            {
                listaClientes = daoCliente.consultar(condicion);

            } catch (SQLException e)
            {
                e.printStackTrace();
            }

            Cliente cliente = listaClientes.get(0);
            vistaCliente.getjTextField2().setText(cliente.getNombre());
            vistaCliente.getjFormattedTextField1().setText(String.valueOf(cliente.getFechaIngreso()));
            vistaCliente.getjCheckBox1().setSelected(cliente.isActivo());
        }

    }

}
