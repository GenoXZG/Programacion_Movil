import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Tarea 1

// MATERIA: Programacion Movil
// ALUMNO: Zarate Gonzalez Luis David


public class Ventana extends JFrame implements ActionListener {
    int ancho, alto;
    JTextField txtEdad;
    JButton btnAceptar;
    JPanel panelBotones;
    private JLabel lblError;
    public Ventana() {
        ancho = 400;
        alto = 400;
        setTitle("Mi Ventana");
        setSize(ancho, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelBotones = new JPanel();
        txtEdad = new JTextField(5);
        btnAceptar = new JButton("Dame tu edad");
        lblError = new JLabel("");
        btnAceptar.addActionListener(this);

        panelBotones.add(txtEdad);
        panelBotones.add(btnAceptar);
        panelBotones.add(lblError);

        add(panelBotones);
    }

    public static void main(String[] args) {
        Ventana v = new Ventana();
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = txtEdad.getText().trim();

        try {
            int edad = Integer.parseInt(input);
            if (edad >= 18)
                JOptionPane.showMessageDialog(null, "Mayor de Edad");
            else{
                JOptionPane.showMessageDialog(null, "Menor de Edad");
            }
        } catch (NumberFormatException ex) {
            mostrarError("'" + input + "' no es un número entero válido.");
        }
    }

    private void mostrarError(String mensaje) {
        lblError.setText("Error");
        JOptionPane.showMessageDialog(this, mensaje, "Error de Validación", JOptionPane.ERROR_MESSAGE);
        txtEdad.requestFocus();
        txtEdad.selectAll();
    }

}