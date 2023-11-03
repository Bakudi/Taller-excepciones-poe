

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

public class App extends JFrame implements ActionListener, ItemListener {

    private ArrayList<Platos> menu;
    private ArrayList<Platos> carrito;
    private double valorcantidadcompra;

    private JComboBox<String> platosEntradasBox;
    private JComboBox<String> platosBebidasBox;
    private JComboBox<String> platosFuertesBox;
    private JTextArea platoInfoTextArea;
    private JTextField cantidadTextField;
    private JTextArea facturaTextArea;


    public App() { // aparatado para implementar la interfaz GUI
        menu = new ArrayList<>();
        carrito = new ArrayList<>();

        // Crear arreglos para cada tipo de plato
        ArrayList<Platos> entradas = new ArrayList<>();
        ArrayList<Platos> bebidas = new ArrayList<>();
        ArrayList<Platos> platos_Fuertes = new ArrayList<>();
        // añadimos todos los platos a el menu

        menu.add(new Platos("Aros de cebolla", "Aros hechos con cebolla .__.", Tipoplato.ENTRADA, 15000, 10));
        menu.add(new Platos("Tostadas", "Tostadas de platano con ají", Tipoplato.ENTRADA, 20000, 15));
        menu.add(new Platos("Jugo de mango", "bebida de mango ._.", Tipoplato.BEBIDA, 20000, 10));
        menu.add(new Platos("Limonada cerezada", "Limonada con sabor a cereza", Tipoplato.BEBIDA, 25000, 10));
        menu.add(new Platos("Parrillada",
                "Picada de varias carnes (pollo, res y cerdo) acompañado de papa y ensalada", Tipoplato.PLATO_FURTE,
                60000, 25));
        menu.add(new Platos("Bandeja paisa", "plato de arroz, frijoles, chorizo, huevo, aguacate y arepa",
                Tipoplato.PLATO_FURTE, 90000, 35));


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
        // interfaz
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
                agregarAlCarrito();
            }
        });

        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    generarFactura();
                } catch (ExcepcionCosto e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        platosEntradasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionPlato(platosEntradasBox.getSelectedItem().toString());
            }
        });

        platosBebidasBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionPlato(platosBebidasBox.getSelectedItem().toString());
            }
        });

        platosFuertesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionPlato(platosFuertesBox.getSelectedItem().toString());
            }
        });

        add(panel);
        setVisible(true);
    }

    private void agregarAlCarrito() {
        String nombrePlato = obtenerPlatoSeleccionado();
        int cantidad = obtenerCantidad();

        if (nombrePlato != null && cantidad > 0) {
            Platos platoSeleccionado = obtenerPlatoDesdeMenu(nombrePlato);

        if (platoSeleccionado != null) {
            if (platoSeleccionado.getTipo() != Tipoplato.BEBIDA) {
                boolean contieneBebida = false;
                for (Platos plato : carrito) {
                    if (plato.getTipo() == Tipoplato.BEBIDA) {
                        contieneBebida = true;
                        break;
                    }
                }
                if (!contieneBebida) {
                    try {
                        throw new Bebedida("Debe pedir al menos una bebida en su pedido.");
                    } catch (Bebedida e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                        return; // No se agrega al carrito si hay una excepción
                    }
                }
            }
                

                if(cantidad >5){
                    try {
                        throw new CantidadExcedida("No puedes comprar más de 5 unidades de un plato.");
                    } catch (CantidadExcedida e){
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }

                }else{
                Platos platoEnCarrito = new Platos(platoSeleccionado.getNombre(), platoSeleccionado.getDescripcion(),
                        platoSeleccionado.getTipo(), platoSeleccionado.getCosto(), platoSeleccionado.getTiempoprep());
                platoEnCarrito.setCantidad(cantidad);

                carrito.add(platoEnCarrito);

                // Actualizar el área del carrito
                actualizarCarrito();
            }}
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un plato y especifique la cantidad.");
        }
    }

    private void generarFactura() throws ExcepcionCosto {
        // Construir la factura con los productos seleccionados
        
        StringBuilder facturaBuilder = new StringBuilder("Factura:\n");
        double total = 0;
        if(total >200000){
            throw new ExcepcionCosto("el valor es demasiado elevado");
        }else{

        for (Platos plato : carrito) {
            double costoPlato = plato.getCosto() * plato.getCantidad();
            facturaBuilder.append(plato.getNombre()).append(" x").append(plato.getCantidad()).append(": $")
                    .append(plato.getCosto()).append("\n");

            total += plato.getCosto();
        }}
        

        facturaBuilder.append("Total: $").append(total);

        // Mostrar la factura en el JTextArea
        facturaTextArea.setText(facturaBuilder.toString());

        // Limpiar el carrito
        carrito.clear();

        // Actualizar el área del carrito
        actualizarCarrito();
    }

    private void mostrarInformacionPlato(String nombrePlato) {
        platoInfoTextArea.setText("");
        Platos plato = obtenerPlatoDesdeMenu(nombrePlato);

        if (plato != null) {
            platoInfoTextArea.setText("Nombre: " + plato.getNombre() + "\nDescripción: " + plato.getDescripcion()
                    + "\nTipo: " + plato.getTipo() + "\nCosto: $" + plato.getCosto() + "\nTiempo de preparación: "
                    + plato.getTiempoprep() + " minutos");
        }
    }

    private void actualizarCarrito() {
        StringBuilder carritoBuilder = new StringBuilder("Carrito:\n");

        for (Platos plato : carrito) {
            carritoBuilder.append(plato.getNombre()).append(" x").append(plato.getCantidad()).append("\n");
        }

        // Mostrar el carrito en el JTextArea
        platoInfoTextArea.setText(carritoBuilder.toString());
    }

    private String obtenerPlatoSeleccionado() {
        if (platosEntradasBox.getSelectedItem() != null) {
            return platosEntradasBox.getSelectedItem().toString();
        } else if (platosBebidasBox.getSelectedItem() != null) {
            return platosBebidasBox.getSelectedItem().toString();
        } else if (platosFuertesBox.getSelectedItem() != null) {
            return platosFuertesBox.getSelectedItem().toString();
        } else {
            return null;
        }
    }

    private int obtenerCantidad() {
        try {
            return Integer.parseInt(cantidadTextField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Platos obtenerPlatoDesdeMenu(String nombrePlato) {
        for (Platos plato : menu) {
            if (plato.getNombre().equals(nombrePlato)) {
                return plato;
            }
        }
        return null;

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
