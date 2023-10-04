package ui;

import impl.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    JFrame frame = new JFrame("Menú");

    public Menu(HistorialDeVentas ventas, TablaDeProductos productos) {
        frame.setSize(300, 80);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton boton1 = new JButton("Ventas");
        JButton boton2 = new JButton("Productos");

        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventas.setVisible(true);
            }
        });

        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productos.setVisible(true);
            }
        });

        panel.add(boton1);
        panel.add(boton2);

        frame.add(panel);
        frame.setVisible(true);
    }
}