// Calculadora con Lambdas
// ALUMNO: Zarate Gonzalez Luis David

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora extends JFrame {
    JTextField n1, n2;
    JButton btnSum, btnRes, btnDiv, btnMul;
    JLabel txtRes;
    Font fuente = new Font("Courier", 1, 60);

    public Calculadora() {
        setTitle("Casio");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        n1 = new JTextField();
        n1.setFont(fuente);
        n2 = new JTextField();
        n2.setFont(fuente);

        JPanel pOpciones = new JPanel(new GridLayout(1, 4));
        btnSum = new JButton("+");
        btnSum.setFont(fuente);
        btnRes = new JButton("-");
        btnRes.setFont(fuente);
        btnMul = new JButton("*");
        btnMul.setFont(fuente);
        btnDiv = new JButton("/");
        btnDiv.setFont(fuente);

        pOpciones.add(btnSum);
        pOpciones.add(btnRes);
        pOpciones.add(btnMul);
        pOpciones.add(btnDiv);

        txtRes = new JLabel("0");
        txtRes.setFont(fuente);


        // Inicio de Lambadas, se cambiaron los listeners para el btnSum, btnMul

        btnSum.addActionListener(e -> calcular("+"));
        btnMul.addActionListener(e -> calcular("*"));
        btnRes.addActionListener(e -> calcular("-"));
        btnDiv.addActionListener(e -> calcular("/"));

        add(n1);
        add(n2);
        add(pOpciones);
        add(txtRes);
    }


    public void calcular(String operador) {
        try {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
            double resultado = 0;

            switch(operador) {
                case "+": resultado = num1 + num2; break;
                case "-": resultado = num1 - num2; break;
                case "*": resultado = num1 * num2; break;
                case "/":
                    if (num2 == 0) {
                        txtRes.setText("Error: Division no valida ");
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }
            txtRes.setText(String.valueOf(resultado));
        } catch (NumberFormatException ex) {
            txtRes.setText("Error");
        }
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        calc.setVisible(true);
    }
}
