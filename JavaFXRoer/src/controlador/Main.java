package controlador;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/EjemploVista.fxml"));
            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) 
        {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
