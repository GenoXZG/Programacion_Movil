// ALUMNO: Zarate Gonzalez Luis David
// Programacion Movil: Fred 20


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Fred extends JFrame {

    JButton[] botones = new JButton[4];
    ArrayList<Integer> patron = new ArrayList<>();

    int nivel = 1;
    int indiceUsuario = 0;

    JLabel lblNivel;

    Color[] paleta = {
        Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW
    };

    Random rnd = new Random();
    boolean turnoUsuario = false;

    public Fred() {
        setTitle("Fred 20");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 350);
        setLayout(new BorderLayout());

        lblNivel = new JLabel("Nivel: 1", SwingConstants.CENTER);
        lblNivel.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblNivel, BorderLayout.NORTH);

    
        JPanel panel = new JPanel(new GridLayout(2, 2));
        add(panel, BorderLayout.CENTER);

        for (int i = 0; i < 4; i++) {
            final int pos = i;
            botones[i] = new JButton();
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setOpaque(true);
            botones[i].setBorderPainted(false);

            botones[i].addActionListener(e -> clickUsuario(pos));
            panel.add(botones[i]);
        }

        siguienteNivel();
    }

    void siguienteNivel() {
        
        patron.add(rnd.nextInt(4));

        lblNivel.setText("Nivel: " + nivel);
        reproducirPatron();
    }

    void reproducirPatron() {
        turnoUsuario = false;
        indiceUsuario = 0;

        for (JButton b : botones) {
            b.setEnabled(false);
        }

        final int[] i = {0};
        Timer t = new Timer(700, null);

        t.addActionListener(e -> {
            if (i[0] >= patron.size()) {
                t.stop();
                turnoUsuario = true;

                for (JButton b : botones) {
                    b.setEnabled(true);
                }
                return;
            }

            int pos = patron.get(i[0]);
            encender(pos, 300);
            i[0]++;
        });

        t.setInitialDelay(0);
        t.start();
    }

    void clickUsuario(int pos) {
        if (!turnoUsuario) return;

        encender(pos, 200);

        if (pos != patron.get(indiceUsuario)) {
        
            nivel = 1;
            patron.clear();
            lblNivel.setText("Perdiste - Nivel: 1");

            new Timer(1000, e -> {
                ((Timer) e.getSource()).stop();
                siguienteNivel();
            }).start();

            return;
        }

        indiceUsuario++;

        if (indiceUsuario == patron.size()) {
            nivel++;

    
            new Timer(800, e -> {
                ((Timer) e.getSource()).stop();
                siguienteNivel();
            }).start();
        }
    }

    void encender(int pos, int tiempo) {
        botones[pos].setBackground(paleta[pos]);

        Timer t = new Timer(tiempo, e -> botones[pos].setBackground(Color.LIGHT_GRAY));
        t.setRepeats(false);
        t.start();
    }

    public static void main(String[] args) {
        new Fred().setVisible(true);
    }
}