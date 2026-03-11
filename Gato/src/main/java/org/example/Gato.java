package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gato extends JFrame implements ActionListener {

    JButton botones[] = new JButton[9];
    JButton btnReiniciar;
    boolean turnoX =  true;
    Font fuente = new Font("Arial",1,60);
    JPanel pJuego, pOpciones;
    String letra;
    public Gato(){
        setTitle("Gato");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pJuego = new JPanel( new GridLayout(3,3));

        for (int i = 0; i < botones.length ; i++) {
            botones[i] = new JButton("");
            botones[i].addActionListener(this);
            pJuego.add(botones[i]);
        }
        add(pJuego, BorderLayout.CENTER);
        btnReiniciar = new JButton("Reiniciar Juego");
        pOpciones = new JPanel();

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < botones.length; i++) {
                    botones[i].setText("");
                    botones[i].setEnabled(true);
                }
                turnoX = true;
            }
        });

        pOpciones.add(btnReiniciar);
        add(pOpciones, BorderLayout.SOUTH);
    }


    public static void main(String[] args){

        Gato g01 = new Gato();
        g01.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        // 1. Identificar cuál botón disparó el evento
        JButton botonPulsado = (JButton) e.getSource();
        if (botonPulsado.getText().equals("")) {
            if (turnoX) {
                botonPulsado.setText("X");
                botonPulsado.setFont(fuente);
            } else {
                botonPulsado.setText("O");
            }
            botonPulsado.setEnabled(false);
            comprobarGanador();
            turnoX = !turnoX;
        }


    }
    private void comprobarGanador() {
        int[][] combinaciones = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Horizontales
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Verticales
                {0, 4, 8}, {2, 4, 6}             // Diagonales
        };
        for (int[] c : combinaciones) {

            String b1 = botones[c[0]].getText();
            String b2 = botones[c[1]].getText();
            String b3 = botones[c[2]].getText();

            if (!b1.isEmpty() && b1.equals(b2) && b1.equals(b3)) {
                JOptionPane.showMessageDialog(this, "¡El ganador es " + b1 + "!");
                desactivarTablero();
                return;
            }
        }
    }

    private void desactivarTablero() {
        for (JButton b : botones) {
            b.setEnabled(false);
        }
    }

}
