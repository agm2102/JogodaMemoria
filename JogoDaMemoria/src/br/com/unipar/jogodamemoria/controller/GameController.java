/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unipar.jogodamemoria.controller;

import br.com.unipar.jogodamemoria.model.Partida;
import br.com.unipar.jogodamemoria.view.GameView;
import br.com.unipar.jogodamemoria.view.MenuPrincipalView;

/**
 *
 * @author Adrian
 */
public class GameController {

    MenuPrincipalView menu = new MenuPrincipalView(this);

    public void iniciarJogo() {
        GameView view = new GameView();
        Partida partida = new Partida(view);
        partida.iniciarNovaPartida();
    }
    public void sair(){
        System.exit(0);
    }
}
