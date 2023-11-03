

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener {

    private ArrayList<Platos> menu;
    private ArrayList<Platos> carrito;

    private JComboBox<String> platosBox;
    private JTextArea platoInfoTextArea;
    private JTextField cantidadTextField;
    private JTextArea facturaTextArea;

    public App() {
        menu = new ArrayList<>();
        carrito = new ArrayList<>();

        // Agregar platos al menú
        menu.add(new Platos("Aros de cebolla", "Aros hechos con cebolla .__.", Tipoplato.entrada, 15000, 10, 1));
        menu.add(new Platos("Tostadas", "Tostadas de plátano con ají", Tipoplato.entrada, 20000, 15, 1));
        menu.add(new Platos("Jugo de mango", "Bebida de mango ._.", Tipoplato.bebida, 20000, 10, 1));
        menu.add(new Platos("Limonada cerezada", "Limonada con sabor a cereza", Tipoplato.bebida, 25000, 10, 1));
        menu.add(new Platos("Parrillada", "Picada de varias carnes (pollo, res y cerdo) acompañada de papas y ensalada", Tipoplato.platofuerte, 60000, 25, 1));
        menu.add(new Platos("Bandeja paisa", "Plato de arroz, frijoles, chorizo, huevo, aguacate y arepa", Tipoplato.platofuerte, 90000, 35, 1));

        setTitle("Restaurante");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        platosBox = new JComboBox<>();
        for (Platos plato : menu) {
            platosBox.addItem(plato.getNombre());
        }

        platoInfoTextArea = new JTextArea(5, 20);
        cantidadTextField = new JTextField(5);
        JButton agregarButton = new JButton("Agregar al Carrito");
        facturaTextArea = new JTextArea(10, 30);
        JButton pagarButton = new JButton("Pagar");

        panel.add(new JLabel("Platos disponibles:"));
        panel.add(platosBox);
        panel.add(new JLabel("Información del Plato:"));
        panel.add(platoInfoTextArea);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadTextField);
        panel.add(new JLabel("Factura:"));
        panel.add(facturaTextArea);
        panel.add(agregarButton);
        panel.add(pagarButton);

        platosBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInformacionPlato();
            }
        });

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
                    e1.printStackTrace();
                }
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
                if (cantidad > 5) {
                    JOptionPane.showMessageDialog(this, "No puedes comprar más de 5 unidades de un plato.");
                    return;
                }

                Platos platoEnCarrito = new Platos(platoSeleccionado.getNombre(), platoSeleccionado.getDescripcion(),
                        platoSeleccionado.getTipo(), platoSeleccionado.getCosto(), platoSeleccionado.getTiempoprep(),
                        platoSeleccionado.getCantidad());
                platoEnCarrito.setCantidad(cantidad);

                carrito.add(platoEnCarrito);

                // Actualizar el área del carrito
                actualizarCarrito();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un plato válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un plato y especifique la cantidad.");
        }
    }

    private void generarFactura() throws ExcepcionCosto {
        if (!contieneBebida()) {
            JOptionPane.showMessageDialog(this, "Debe pedir al menos una bebida en su pedido.");
            return;
        }   

        StringBuilder facturaBuilder = new StringBuilder("Factura:\n");
        double total = 0;

        for (Platos plato : carrito) {
            double costoPlato = plato.getCosto() * plato.getCantidad();
            facturaBuilder.append(plato.getNombre()).append(" x").append(plato.getCantidad()).append(": $")
                    .append(plato.getCosto()).append("\n");

            total += costoPlato;
        }

        if (total > 200000) {
            JOptionPane.showMessageDialog(this, "El valor es demasiado elevado. No se puede completar la compra.");
            return;
        }

        facturaBuilder.append("Total: $").append(total);

        facturaTextArea.setText(facturaBuilder.toString());

        carrito.clear();
        actualizarCarrito();
    }

    private void mostrarInformacionPlato() {
        String nombrePlato = obtenerPlatoSeleccionado();
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

        platoInfoTextArea.setText(carritoBuilder.toString());
    }

    private String obtenerPlatoSeleccionado() {
        if (platosBox.getSelectedItem() != null) {
            return platosBox.getSelectedItem().toString();
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

    private boolean contieneBebida() {
        for (Platos plato : carrito) {
            if (plato.getTipo() == Tipoplato.bebida) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        App app = new App();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
