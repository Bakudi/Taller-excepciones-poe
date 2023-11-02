
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener, ItemListener{

    private ArrayList<Platos> menu;
    private ArrayList<Platos> carrito;

    private JComboBox<String> platosEntradasBox;
    private JComboBox<String> platosBebidasBox;
    private JComboBox<String> platosFuertesBox;
    private JTextArea platoInfoTextArea;
    private JTextField cantidadTextField;
    private JTextArea facturaTextArea;

    public App(){ //apratado para implemtar la interfaz GUI
        menu = new ArrayList<>();
        carrito = new ArrayList<>(); 

        // Crear arreglos para cada tipo de plato
        ArrayList<Platos> entradas = new ArrayList<>();
        ArrayList<Platos> bebidas = new ArrayList<>();
        ArrayList<Platos> platos_Fuertes = new ArrayList<>();
        //añadimos todos los platos a el menu 
        
        menu.add(new Platos("Aros de cebolla", "Aros hechos con cebolla .__.", Tipoplato.ENTRADA, 15000, 10));
        menu.add(new Platos("Tostadas", "Tostadas de platano con ají", Tipoplato.ENTRADA, 20000, 15));
        menu.add(new Platos("Jugo de mango", "bebida de mango ._.", Tipoplato.BEBIDA, 20000, 10));
        menu.add(new Platos("Limonada cerezada", "Limonada con sabor a cereza", Tipoplato.BEBIDA, 25000, 10));
        menu.add(new Platos("Parrillada", "Picada de varias carnes (pollo, res y cerdo) acompañado de papa y ensalada", Tipoplato.PLATO_FURTE, 60000, 25));
        menu.add(new Platos("Bandeja paisa", "plato de arroz, frijoles, chorizo, huevo, aguacate y arepa", Tipoplato.PLATO_FURTE, 90000, 35));
        
        // Recorrer el arreglo menu y separar los objetos por tipo de plato
        for (Platos plato : menu) {
            if (plato.getTipo() == Tipoplato.ENTRADA) {
                entradas.add(plato);
            } else if (plato.getTipo() == Tipoplato.BEBIDA) {
                bebidas.add(plato);
            } else if (plato.getTipo() == Tipoplato.PLATO_FURTE) {
                platos_Fuertes.add(plato);
            }
        }

        //interfaz
        setTitle("Restaurante");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        platosEntradasBox = new JComboBox<>();
        for (Platos plato : entradas) {
            platosEntradasBox.addItem(plato.getNombre());
        }
        platosBebidasBox = new JComboBox<>();
        for (Platos plato : bebidas) {
            platosBebidasBox.addItem(plato.getNombre());
        }
        platosFuertesBox = new JComboBox<>();
        for (Platos plato : platos_Fuertes) {
            platosFuertesBox.addItem(plato.getNombre());
        }
        platoInfoTextArea = new JTextArea(5, 20);
        cantidadTextField = new JTextField(5);
        JButton agregarButton = new JButton("Agregar al Carrito");
        facturaTextArea = new JTextArea(10, 30);
        JButton pagarButton = new JButton("Pagar");

        panel.add(new JLabel("Platos de entrada:"));
        panel.add(platosEntradasBox);
        panel.add(new JLabel("Bebidas:"));
        panel.add(platosBebidasBox);
        panel.add(new JLabel("Platos fuertes:"));
        panel.add(platosFuertesBox);
        panel.add(new JLabel("Información del Plato:"));
        panel.add(platoInfoTextArea);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadTextField);
        panel.add(new JLabel("Factura:"));
        panel.add(facturaTextArea);
        panel.add(agregarButton);
        panel.add(pagarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        platosEntradasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        platosBebidasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        platosFuertesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}