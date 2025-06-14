package br.com.unipar.jogodamemoria.view;

import br.com.unipar.jogodamemoria.controller.GameController;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class MenuPrincipalView extends JFrame {

    private final JPanel painel;
    private final JButton btIniciar;
    private final JButton btSair;
    private final JLabel titulo;
    private final int WIDTH_VIEW = 800;
    private final int HEIGHT_VIEW = 800;

    public MenuPrincipalView(GameController controller) {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH_VIEW, HEIGHT_VIEW);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Criação do título
        titulo = new JLabel("Jogo da Memória");
        titulo.setFont(new Font("Arial", Font.BOLD, 60)); // Aumentei o tamanho da fonte
        titulo.setForeground(Color.BLACK);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        // Criação dos botões estilizados
        btIniciar = criarBotao("INICIAR", new Color(0, 180, 0));
        btSair = criarBotao("SAIR", new Color(180, 0, 0));

        // Painel para os botões e título
        painel = new JPanel(new GridBagLayout());
        painel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // Título — mais para cima
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 80, 0); // margem inferior maior para descolar dos botões
        painel.add(titulo, gbc);

        // Botão Iniciar
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 20, 0); // espaçamento entre os botões
        painel.add(btIniciar, gbc);

        // Botão Sair
        gbc.gridy = 2;
        painel.add(btSair, gbc);

        add(painel, BorderLayout.CENTER);

        // Eventos
        btIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.iniciarJogo();
                dispose();
            }
        });

        btSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private JButton criarBotao(String texto, Color corFundo) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setForeground(Color.BLACK);
        botao.setBackground(corFundo);
        botao.setPreferredSize(new Dimension(200, 60));
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBorder(new RoundedBorder(30));
        return botao;
    }

    // Classe interna para criar borda arredondada
    private static class RoundedBorder implements Border {
        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
