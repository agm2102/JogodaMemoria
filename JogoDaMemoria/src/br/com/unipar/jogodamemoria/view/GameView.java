/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unipar.jogodamemoria.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Adrian
 */
public class GameView extends JFrame {

    private final JPanel painel;
    private final int WIDTH_VIEW = 800;
    private final int HEIGHT_VIEW = 800;
    
    public GameView(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH_VIEW, HEIGHT_VIEW);
        setLocationRelativeTo(null); // centraliza a janela na tela

        setLayout(new BorderLayout()); // usar layout automático

        painel = new JPanel();
        painel.setSize(WIDTH_VIEW, HEIGHT_VIEW);
        painel.setLayout(new GridLayout(4, 4)); // 4x4 cartas
        painel.setBackground(Color.LIGHT_GRAY);
        add(painel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public JPanel getPainel() {
        return painel;
    }

    public int getWIDTH_VIEW() {
        return WIDTH_VIEW;
    }

    public int getHEIGHT_VIEW() {
        return HEIGHT_VIEW;
    }
    
}
