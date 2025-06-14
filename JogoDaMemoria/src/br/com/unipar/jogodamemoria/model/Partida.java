
package br.com.unipar.jogodamemoria.model;

import br.com.unipar.jogodamemoria.view.GameView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Partida {

    private final GameView view;
    private final List<Carta> cartas;
    private final List<Carta> cartasViradasAtualmente;
    private final List<Carta> cartasAchadas;
    private MouseAdapter mouseAdapter;
    private final List<String> nomeFrutas;
    private Timer timer;

    private final int QTD_CARTAS_PARTIDA = 16;

    public Partida(GameView view) {
        this.view = view;
        cartas = new ArrayList<>();
        cartasAchadas = new ArrayList<>();
        cartasViradasAtualmente = new ArrayList<>();
        nomeFrutas = new ArrayList<>(Arrays.asList(
                "LARANJA",
                "LARANJA",
                "CEREJA",
                "CEREJA",
                "LIMAO",
                "LIMAO",
                "BANANA",
                "BANANA",
                "ABACATE",
                "ABACATE",
                "MORANGO",
                "MORANGO",
                "PERA",
                "PERA",
                "MACA",
                "MACA"
        ));
        escolherCartas();
    }

    public void iniciarNovaPartida() {
        gerarCartas();
        adicionarListnerNasCartas();
        atualizaView();
    }

    public void reiniciarPartida() {
        for (Carta carta : cartas) {
            carta.virarCarta();
        }
        Collections.shuffle(cartas);
        atualizaView();
    }
    

    public void sairDoJogo() {
        System.exit(0);
    }

    private void adicionarListnerNasCartas() {
        for (Carta carta : cartas) {
            carta.getLabelCarta().addMouseListener(mouseAdapter);
            view.getPainel().add(carta.getLabelCarta());
        }
    }

    private void fimDeJogo() {
        if (cartasAchadas.size() == QTD_CARTAS_PARTIDA) {
            String[] opcoes = {"Reiniciar", "Sair"};
            int escolha = JOptionPane.showOptionDialog(
                    view,
                    "Parabéns! Você encontrou todos os pares.\nDeseja jogar novamente?",
                    "Fim de Jogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == 0) {
                reiniciarPartida();
            } else if (escolha == 1 || escolha == JOptionPane.CLOSED_OPTION) {
                sairDoJogo();
            }
        }
    }

    private void escolherCartas() {

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel labelCarta = (JLabel) e.getSource();
                Carta carta = (Carta) labelCarta.getClientProperty(labelCarta.getName());
                System.out.println(labelCarta.getName());
                if (!carta.isEstaVirada() && cartasViradasAtualmente.size() < 2) {
                    carta.virarCarta();
                    cartasViradasAtualmente.add(carta);
                    atualizaView();
                    if (cartasViradasAtualmente.size() == 2) {
                        if (!verificaSeAchouPar()) {
                            timer = new Timer(1000, event -> {
                                desvirarCartas();
                                timer.stop();
                                cartasViradasAtualmente.clear();
                            });
                            timer.setRepeats(false);
                            timer.start();

                        } else {
                            cartasAchadas.addAll(cartasViradasAtualmente);
                            cartasViradasAtualmente.clear();
                            fimDeJogo();
                        }
                    }
                }
            }
        };
    }

    private void desvirarCartas() {
        cartasViradasAtualmente.getFirst().virarCarta();
        cartasViradasAtualmente.getLast().virarCarta();
    }

    private boolean verificaSeAchouPar() {
        return cartasViradasAtualmente.get(0).getNomeFruta().equals(cartasViradasAtualmente.get(1).getNomeFruta());
    }

    private void gerarCartas() {
        for (int i = 1; i <= QTD_CARTAS_PARTIDA; i++) {
            Carta carta = new Carta();
            carta.setNomeFruta(nomeFrutas.get(i - 1));
            carta.getLabelCarta().setName(carta.getNomeFruta());
            carta.getLabelCarta().putClientProperty(carta.getNomeFruta(), carta);
            carta.setIndice(i);
            cartas.add(carta);
        }
        Collections.shuffle(cartas);
    }

    private void atualizaView() {
        view.revalidate();
        view.repaint();
    }
}
