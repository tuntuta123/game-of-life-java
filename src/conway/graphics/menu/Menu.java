package conway.graphics.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements MenuInterface {

    private JFrame frame;
    private JPanel panel;
    private String startLabel, rulesLabel, quitLabel;
    private JButton start, rules, quit;
    private JLabel title; 

    public Menu(String mainWindowTitle, String startLabel, String rulesLabel, String quitLabel) {
        this.startLabel = startLabel;
        this.rulesLabel = rulesLabel;
        this.quitLabel = quitLabel;

        this.frame = new JFrame(mainWindowTitle);
        this.frame.setSize(830, 400);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel = new JPanel();
        this.frame.add(this.panel);
        this.placeComponents(this.panel);
    }

    @Override
    public void placeComponents(JPanel panel) {
        this.panel.setLayout(null);

        this.title = new JLabel("Bienvenue au jeu de la vie (jeu de Conway) !");
        this.title.setFont(new Font("Arial", Font.BOLD, 20));  
        this.title.setBounds(150, 20, 600, 40);  
        this.panel.add(this.title);

        this.start = new JButton(startLabel);
        this.start.setBounds(200, 100, 400, 50);  
        this.panel.add(this.start);

        this.rules = new JButton(rulesLabel);
        this.rules.setBounds(200, 170, 400, 50); 
        this.panel.add(this.rules);

        this.quit = new JButton(quitLabel);
        this.quit.setBounds(200, 240, 400, 50); 
        this.panel.add(this.quit);

        this.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SubMenu subMenu = new SubMenu("Options de jeu");
                subMenu.display();
                frame.dispose();
            }
        });

        this.rules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RulesMenu rulesMenu = new RulesMenu("Les règles du jeu");
                rulesMenu.display();
                frame.dispose();
            }
        });

        this.quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu app = new Menu("Menu principal", "Commencer le jeu", "Les règles du jeu", "Quitter");
        app.display();
    }
}

