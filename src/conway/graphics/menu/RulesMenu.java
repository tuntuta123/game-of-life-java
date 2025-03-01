package conway.graphics.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RulesMenu implements MenuInterface {

    private JFrame frame;
    private String title;
    private JPanel panel;
    private JLabel titleLabel;
    private JTextPane rulesText;
    private ImageIcon image1, image2;
    private JLabel imgLabel1, imgLabel2;
    private JLabel step1, step2;
    private JButton back;

    public RulesMenu(String title) {
        this.title = title;
        this.frame = new JFrame(title);
        this.frame.setSize(1200, 1000);  
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.panel = new JPanel(); 
        this.panel.setLayout(null); 
        this.frame.add(this.panel);
        this.placeComponents(this.panel);
    }
    
    @Override
    public void placeComponents(JPanel panel) {
        this.titleLabel = new JLabel("Les règles du jeu");
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.titleLabel.setBounds(480, 20, 240, 40);  
        this.panel.add(this.titleLabel);

        this.rulesText = new JTextPane();
        this.rulesText.setText("Le Jeu de la Vie est un système dynamique inventé par le mathématicien John Horton Conway en 1970.\n" +
                                               "Il repose sur une grille infinie où chaque cellule peut être vivante ou morte. À chaque itération, l'état de chaque cellule est déterminé par ses huit voisines selon les règles suivantes :\n" +
                                               "- une cellule morte avec exactement trois voisins vivants devient vivante (création de nouvelles cellules) \n" + 
                                               "- une cellule vivante avec deux ou trois voisins vivants continue de vivre, sinon elle meurt (décès par isolement ou surpopulation).");
        this.rulesText.setFont(new Font("Serif", Font.PLAIN, 16));
        this.rulesText.setBounds(250, 100, 700, 250);  
        this.rulesText.setEditable(false); 
        this.rulesText.setBackground(new Color(240, 240, 240));
        this.panel.add(this.rulesText);

        this.image1 = new ImageIcon("src/conway/graphics/menu/imgs/begin.png");
        this.imgLabel1 = new JLabel(this.image1);
        this.imgLabel1.setBounds(150, 350, 400, 400);  
        this.panel.add(this.imgLabel1);

        this.step1 = new JLabel("Première étape");
        this.step1.setFont(new Font("Arial", Font.PLAIN, 16));
        this.step1.setBounds(280, 760, 200, 30); 
        this.panel.add(this.step1);
        
        this.image2 = new ImageIcon("src/conway/graphics/menu/imgs/end.png");  
        this.imgLabel2 = new JLabel(this.image2);
        this.imgLabel2.setBounds(650, 350, 400, 400);  
        this.panel.add(this.imgLabel2);

        this.step2 = new JLabel("Deuxième étape");
        this.step2.setFont(new Font("Arial", Font.PLAIN, 16));
        this.step2.setBounds(780, 760, 200, 30); 
        this.panel.add(this.step2);

        this.back = new JButton("Retourner au menu principal");
        this.back.setBounds(450, 850, 300, 30); 
        this.panel.add(this.back);

        this.back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu("Menu principal", "Commencer le jeu", "Les règles du jeu", "Quitter");
                menu.display();
                frame.dispose();
            }
        });
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }
}

