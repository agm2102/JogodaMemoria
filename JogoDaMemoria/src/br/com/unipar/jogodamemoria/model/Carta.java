package br.com.unipar.jogodamemoria.model;

import br.com.unipar.jogodamemoria.utilities.GameUtilities;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Carta {
    
    private URL imgUrl;
    private ImageIcon imagemCarta;
    private final JLabel labelCarta;
    private final int SIZE_IMAGE = 180;
    private boolean estaVirada = false;
    private String nomeFruta;
    private int indice;
    private final GameUtilities util;

    public Carta() {
        util = new GameUtilities();
      
        imgUrl = getClass().getResource("/br/com/unipar/jogodamemoria/images/logoUnipar.png");
        
        imagemCarta = new ImageIcon(imgUrl);
        imagemCarta = util.imageScaler(imagemCarta, SIZE_IMAGE);
        
        labelCarta = new JLabel(imagemCarta);  
    }
    
    public void virarCarta(){
        estaVirada = !estaVirada;
        if(!estaVirada){
            imgUrl = getClass().getResource("/br/com/unipar/jogodamemoria/images/logoUnipar.png");
        }
        else{
            imgUrl = getClass().getResource("/br/com/unipar/jogodamemoria/images/"+indice+".jpg");
        }
        imagemCarta = new ImageIcon(imgUrl);
        imagemCarta = util.imageScaler(imagemCarta, SIZE_IMAGE);
        labelCarta.setIcon(imagemCarta);
    }

    public boolean isEstaVirada() {
        return estaVirada;
    }

    public JLabel getLabelCarta() {
        return labelCarta;
    }

    public String getNomeFruta() {
        return nomeFruta;
    }

    public void setNomeFruta(String nomeFruta) {
        this.nomeFruta = nomeFruta;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
}
