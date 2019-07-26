package com.game.src.UI;

import com.game.src.graphics.Drawable;
import com.game.src.input.InputListener;
import com.game.src.main.Main;
import com.game.src.map.Cuadricula;
import com.game.src.map.RandomLayout;
import com.game.src.net.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author HikingCarrot7
 */
public class PlacingShips implements Drawable, InputListener
{

    private final Rectangle[] barcosHorizontales, barcosVerticales;
    private final Cuadricula cuadricula;
    private final Cliente cliente;
    private final int LADOCUADRO = 24;
    private final int ANCHOTABLERO = 20, ALTOTABLERO = 10;
    private final RandomLayout randomLayout;
    private final Rectangle continuar;
    private final boolean[] barcosColocados;
    private Rectangle barcoSeleccionado;
    private int timer = 10, COORDENADAX, COORDENADAY, CONTBARCOSCOLOCADOS = 0;
    private boolean orientacionBarcoActual = false;

    public PlacingShips(RandomLayout randomLayout, Cliente cliente)
    {
        barcosHorizontales = new Rectangle[5];
        barcosVerticales = new Rectangle[3];
        cuadricula = new Cuadricula(25, 105);
        continuar = new Rectangle(670, 5, 70, 25);
        barcosColocados = new boolean[8];
        
        this.cliente = cliente;
        this.randomLayout = randomLayout;
    }

    @Override
    public void tick()
    {
        if (timer > 0)
        {
            timer--;
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        if (timer <= 0)
        {

            dibujarHUD(g);
            colocandoBarcos(g);
            barcoSeleccionado(g);
            barcoSeleccionadoMouse(g);
            confirmarBarcoMientrasArrastre();
        }
    }

    public void colocandoBarcos(Graphics2D g)
    {
        cuadricula.render(g);

        if (barcosHorizontales[0] == null)
        {
            colocarBarcosSeleccion(g, barcosHorizontales, true, 580, 70, LADOCUADRO);
            colocarBarcosSeleccion(g, barcosVerticales, false, 580, 260, LADOCUADRO);

        } else
        {

            for (int i = 0; i < barcosHorizontales.length; i++)
            {
                if (!barcosColocados[i])
                {
                    g.draw(barcosHorizontales[i]);
                }
            }

            for (int i = 0; i < barcosVerticales.length; i++)
            {
                if (!barcosColocados[i + 5])
                {
                    g.draw(barcosVerticales[i]);
                }
            }
        }
    }

    // False para una orientacion vertical, true para una horizontal
    // Coloca los barcos para su seleccion en las parte de la derecha
    public void colocarBarcosSeleccion(Graphics2D g, Rectangle[] barcos, boolean orientacion, int INICIOX, int INICIOY, int LADOCUADRO)
    {

        if (orientacion)
        {
            for (int i = 0; i < barcos.length; i++)
            {
                barcos[i] = new Rectangle(INICIOX, INICIOY + i * (LADOCUADRO + 5), (i + 2) * LADOCUADRO, LADOCUADRO);

                g.draw(barcos[i]);
            }

        } else
        {
            for (int i = barcos.length - 1; i >= 0; i--)
            {
                barcos[i] = new Rectangle(INICIOX + (barcos.length - 1 - i) * (LADOCUADRO + 5), INICIOY + (barcos.length - 1 - i) * LADOCUADRO, LADOCUADRO, (i + barcos.length - 1) * LADOCUADRO);

                g.draw(barcos[i]);
            }
        }
    }

    // Pinta de color azul el barco seleccionado
    public void barcoSeleccionado(Graphics2D g)
    {
        if (barcoSeleccionado != null)
        {
            g.setColor(Color.blue);
            g.drawRect(barcoSeleccionado.x, barcoSeleccionado.y, barcoSeleccionado.width, barcoSeleccionado.height);

        }
    }

    // Pinta el un rectangulo que sigue al mouse mientras se coloca en el tablero
    public void barcoSeleccionadoMouse(Graphics2D g)
    {
        if (barcoSeleccionado != null)
        {
            g.drawRect(COORDENADAX, COORDENADAY, barcoSeleccionado.width, barcoSeleccionado.height);
        }

    }

    public void confirmarBarcoMientrasArrastre()
    {
        if (barcoSeleccionado != null)
        {
            checarTableroAlArrastrar(
                    (COORDENADAY - 105) / LADOCUADRO,
                    (COORDENADAX - 25) / LADOCUADRO,
                    (orientacionBarcoActual ? barcoSeleccionado.width : barcoSeleccionado.height) / LADOCUADRO,
                    orientacionBarcoActual);
        }
    }

    // Checamos el tablero al arrastrar (antes de hacer clic)
    public void checarTableroAlArrastrar(int fila, int columna, int length, boolean horizontal)
    {
        if (fila < ALTOTABLERO && fila >= 0 && columna < ANCHOTABLERO && columna >= 0)
        {

            if (validarUbicacionBarco(orientacionBarcoActual, fila, columna, cuadricula.obtenerTablero(), barcoSeleccionado))
            {
                cuadricula.iniciarTablero();

                pintarBarco(fila, columna, length, 1, orientacionBarcoActual, false);

            } else
            {
                cuadricula.iniciarTablero();

                pintarBarco(fila, columna, length, 3, orientacionBarcoActual, false);
            }

        } else
        {
            cuadricula.iniciarTablero();
        }
    }

    // Checamos el tablero al colocar el barco (cuando se hace click)
    public void checarTableroAlColocar(int fila, int columna)
    {
        if (fila < ALTOTABLERO && fila >= 0 && columna < ANCHOTABLERO && columna >= 0 && barcoSeleccionado != null)
        {

            if (validarUbicacionBarco(orientacionBarcoActual, fila, columna, cuadricula.obtenerTablero(), barcoSeleccionado))
            {
                int length = (orientacionBarcoActual ? barcoSeleccionado.width : barcoSeleccionado.height) / LADOCUADRO;

                pintarBarco(fila, columna, length, 2, orientacionBarcoActual, false);

                barcosColocados[orientacionBarcoActual ? length - 2 : length + 3] = true;

                barcoSeleccionado = null;

                CONTBARCOSCOLOCADOS++;

            }

        }

    }

    // Pintamos el barco de acuerdo al color que le pasemos
    public void pintarBarco(int fila, int columna, int length, int color, boolean orientacionBarcoActual, boolean eliminar)
    {
        for (int i = fila, j = columna; (orientacionBarcoActual ? j : i) < (orientacionBarcoActual ? columna : fila) + length;)
        {
            cuadricula.modificarTablero(i, j, color, eliminar);

            if (orientacionBarcoActual)
            {
                j++;

            } else
            {
                i++;
            }

        }
    }

    // true corresponde para horizontal, false para vertical
    public boolean validarUbicacionBarco(boolean orientacion, int fila, int columna, int[][] tablero, Rectangle barco)
    {
        int length = (orientacion ? barco.width : barco.height) / LADOCUADRO;
        int posicion = orientacion ? columna : fila;
        int limite = orientacion ? ANCHOTABLERO : ALTOTABLERO;
        int[][] tempTablero = new int[12][22];

        tempTablero = randomLayout.copiarTableros(tempTablero, tablero, 0, 1, 0);

        if (posicion + length > limite)
        {
            return false;
        }

        for (int i = fila + 1, j = columna + 1; (orientacion ? j : i) < (orientacion ? columna : fila) + length + 1;)
        {

            if (tempTablero[i][j] == 2 || tempTablero[i][j - 1] == 2 || tempTablero[i][j + 1] == 2 || tempTablero[i + 1][j] == 2 || tempTablero[i - 1][j] == 2)
            {
                return false;
            }

            if (orientacion)
            {
                j++;

            } else
            {
                i++;
            }
        }

        return true;
    }

    public void checarEliminacionBarco(int fila, int columna)
    {
        int[][] tablero = cuadricula.obtenerTablero(), tempTablero = new int[12][22];

        tempTablero = randomLayout.copiarTableros(tempTablero, tablero, 0, 1, 0);

        if (barcoSeleccionado != null)
        {
            barcoSeleccionado = null;

            cuadricula.iniciarTablero();

            return;
        }

        if (tempTablero[fila][columna] == 2)
        {

            if (tempTablero[fila][columna - 1] == 2 || tempTablero[fila][columna + 1] == 2)
            {
                eliminarBarco(true, fila, columna, tempTablero);

            } else
            {
                eliminarBarco(false, fila, columna, tempTablero);

            }

        }

    }

    public void eliminarBarco(boolean orientacion, int fila, int columna, int[][] tempTablero)
    {
        int aux = orientacion ? columna : fila, contBloques = 1;

        while (tempTablero[orientacion ? fila : aux - 1][orientacion ? aux - 1 : columna] != 0)
        {
            aux--;
        }

        int aux2 = aux;

        while (tempTablero[orientacion ? fila : aux + 1][orientacion ? aux + 1 : columna] != 0)
        {
            aux++;
            contBloques++;
        }

        barcosColocados[orientacion ? contBloques - 2 : 3 + contBloques] = false;

        pintarBarco(orientacion ? fila - 1 : aux2 - 1, orientacion ? aux2 - 1 : columna - 1, contBloques, 0, orientacion, true);

        CONTBARCOSCOLOCADOS--;

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int fila = (e.getY() - 105) / LADOCUADRO, columna = (e.getX() - 25) / LADOCUADRO;
        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
        
        if (Main.GAMESTATE.equals(Main.STATE.ColocandoBarcos) && timer <= 0 && e.getButton() == MouseEvent.BUTTON1 && CONTBARCOSCOLOCADOS != 8)
        {

            for (int i = 0; i < barcosHorizontales.length; i++)
            {
                if (r.intersects(barcosHorizontales[i]) && !barcosColocados[i])
                {
                    barcoSeleccionado = barcosHorizontales[i];

                    orientacionBarcoActual = true;

                    return;
                }
            }

            for (int i = 0; i < barcosVerticales.length; i++)
            {
                if (r.intersects(barcosVerticales[i]) && !barcosColocados[i + 5])
                {
                    barcoSeleccionado = barcosVerticales[i];

                    orientacionBarcoActual = false;

                    return;
                }

            }

            checarTableroAlColocar(fila, columna);

        } else if (Main.GAMESTATE.equals(Main.STATE.ColocandoBarcos) && e.getButton() == MouseEvent.BUTTON1 && CONTBARCOSCOLOCADOS == 8 && r.intersects(continuar))
        {
            
            Main.GAMESTATE = Main.STATE.Jugando;
            
            cliente.setBarcos(cuadricula.obtenerTablero());

        } else if (e.getButton() == MouseEvent.BUTTON3)
        {
            checarEliminacionBarco(fila + 1, columna + 1);
        }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        COORDENADAX = e.getX();
        COORDENADAY = e.getY();
    }

    public void dibujarHUD(Graphics2D g)
    {
        Font title = new Font("serif", Font.BOLD, 30), text = new Font("serif", Font.BOLD, 15), instrucciones = new Font("serif", Font.BOLD, 13);

        if (CONTBARCOSCOLOCADOS == 8)
        {
            g.draw(continuar);
        }

        g.setFont(title);
        g.drawString("Coloca tus barcos!", Main.ANCHO / 2 - 170, 30);

        g.setFont(text);
        g.drawString("Barcos horizontales:", Main.ANCHO - 225, 60);
        g.drawString("Barcos verticales: ", Main.ANCHO - 225, 250);

        g.setFont(instrucciones);
        g.drawString("> Da clic izquierdo sobre un barco y colócalo en el tablero.", 15, 55);
        g.drawString("> Da clic derecho sobre un barco para borrarlo del tablero.", 15, 75);

        g.drawString("> Para continuar, debes esperar a que otro jugador se conecte.", 15, 360);

    }

}
