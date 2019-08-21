package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Suma;

public class EjemploController implements Initializable
{

    @FXML
    private Button btnBoton;
    @FXML
    private TextField numero1;
    @FXML
    private TextField numero2;
    @FXML
    private TextField resultado;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    @FXML
    private void click(ActionEvent e)
    {
        try
        {
            double n1 = Double.parseDouble(numero1.getText());
            double n2 = Double.parseDouble(numero2.getText());

            resultado.setText(new Suma(n1, n2).suma() + "");

        } catch (NumberFormatException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }
}
