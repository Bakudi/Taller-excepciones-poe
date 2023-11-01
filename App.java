import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class App extends JFrame implements ActionListener, ItemListener{

    Container contenedor;
    FlowLayout layout;
    JLabel titulo;
    JTextField campo;
    JTextArea area1;
    JScrollPane scroll;
    JButton boton1, boton2;
    JCheckBox caja1, caja2, caja3;

    /*public App(){
        contenedor = getContentPane();
        layout = new FlowLayout();
        contenedor.setLayout(layout);

        titulo = new JLabel("Gestión de restaurante");
        titulo.setFont(new Font("Monospaced", Font.CENTER_BASELINE, 25));        
        contenedor.add(titulo);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setVisible(true);
    }*/

    public static void main(String[] args) throws Exception {
        /*App app = new App();*/
        ArrayList <Platos> carrito = new ArrayList<Platos>();


        /*Platos entrada1 = new Platos("Aros de cebolla", "Aros hechos con cebolla .__.", Tipoplato.entrada, 15000, 10);
        Platos entrada2 = new Platos("Tostadas", "Tostadas de platano con ají", Tipoplato.entrada, 20000, 15);
        Platos bebida1 = new Platos("Jugo de mango", "bebida de mango ._.", Tipoplato.entrada, 20000, 10);
        Platos bebida2 = new Platos("Limonada cerezada", "Limonada con sabor a cereza", Tipoplato.entrada, 25000, 10);
        Platos fuerte1 = new Platos("Parrillada", "Picada de varias carnes (pollo, res y cerdo) acompañado de papa y ensalada", Tipoplato.entrada, 60000, 25);
        Platos fuerte2 = new Platos("Bandeja paisa", "plato de arroz, frijoles, chorizo, huevo, aguacate y arepa", Tipoplato.entrada, 90000, 35);*/

        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}