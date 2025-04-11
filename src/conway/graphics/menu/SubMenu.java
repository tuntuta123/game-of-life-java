package conway.graphics.menu;

import conway.demo.*;
import conway.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SubMenu implements MenuInterface {

    public JFrame frame;
    public String title;
    public JSpinner size;
    public JComboBox<ColorItem> liveColor, deadColor;
    public JCheckBox emoji;
    public JButton back, reset, start; 
    public JPanel panel;

    public final int defaultSize = 10;
    public final ColorItem defaultLiveColor = new ColorItem(Color.RED, "Red");
    public final ColorItem defaultDeadColor = new ColorItem(Color.WHITE, "Pink");
    public final boolean defaultEmoji = false;

    // Add JComboBox to select the algorithm
    public JComboBox<String> algorithmComboBox;  

    public SubMenu(String title) {
        this.title = title;
        this.frame = new JFrame(title);
        this.frame.setSize(920, 500);  
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);  
        this.frame.add(this.panel);
        this.placeComponents(this.panel);
    }

    @Override
    public void placeComponents(JPanel panel) {
        JLabel sizeLabel = new JLabel("Choisissez la taille de la grille entre 3 et 100 (10 par défaut)");
        sizeLabel.setBounds(50, 50, 500, 50);  
        this.panel.add(sizeLabel);

        SpinnerNumberModel spinner = new SpinnerNumberModel(this.defaultSize, 3, 100, 1);
        this.size = new JSpinner(spinner);
        this.size.setBounds(550, 55, 200, 40);  
        this.panel.add(this.size);

        JLabel liveLabel = new JLabel("Choisissez la couleur des cellules vivantes:");
        liveLabel.setBounds(50, 120, 500, 40);  
        this.panel.add(liveLabel);

        List<ColorItem> liveColors = new ArrayList<>();
        liveColors.add(new ColorItem(Color.PINK, "Rose"));
        liveColors.add(new ColorItem(Color.RED, "Rouge"));
        liveColors.add(new ColorItem(Color.GREEN, "Vert"));
        liveColors.add(new ColorItem(Color.BLUE, "Bleu"));
        liveColors.add(new ColorItem(Color.YELLOW, "Jaune"));
        liveColors.add(new ColorItem(Color.MAGENTA, "Magenta"));
        liveColors.add(new ColorItem(Color.ORANGE, "Orange"));
        liveColors.add(new ColorItem(Color.CYAN, "Cyan"));

        this.liveColor= new JComboBox<>(liveColors.toArray(new ColorItem[0]));
        this.liveColor.setBounds(550, 120, 200, 40);
        this.panel.add(this.liveColor);

        JLabel deadLabel = new JLabel("Choisissez la couleur des cellules mortes :");
        deadLabel.setBounds(50, 195, 500, 40);  
        this.panel.add(deadLabel);

        List<ColorItem> deadColors = new ArrayList<>();
        deadColors.add(new ColorItem(Color.WHITE, "Blanc"));
        deadColors.add(new ColorItem(Color.GRAY, "Grise"));
        deadColors.add(new ColorItem(Color.LIGHT_GRAY, "Gris clair"));
        deadColors.add(new ColorItem(Color.BLACK, "Noir"));

        this.deadColor = new JComboBox<>(deadColors.toArray(new ColorItem[0]));
        this.deadColor.setBounds(550, 190, 200, 40);  
        this.panel.add(this.deadColor);

        this.emoji = new JCheckBox("Enable Emojis");
        this.emoji.setBounds(50, 270, 300, 40);
        this.panel.add(this.emoji);

        // Add ComboBox for algorithm selection
        JLabel algorithmLabel = new JLabel("Choisissez l'algorithme:");
        algorithmLabel.setBounds(50, 320, 500, 40);
        this.panel.add(algorithmLabel);

        String[] algorithms = {"BasicAlgo", "HashlifeAlgo"};
        this.algorithmComboBox = new JComboBox<>(algorithms);
        this.algorithmComboBox.setBounds(550, 320, 200, 40);
        this.panel.add(this.algorithmComboBox);

        JPanel buttons = new JPanel();
        buttons.setBounds(5, 350, 900, 100);  
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20)); 
        this.panel.add(buttons);

        Dimension dim = new Dimension(250, 40);

        this.back = new JButton("Retour au menu principal");
        this.back.setPreferredSize(dim);  
        buttons.add(this.back);

        this.reset = new JButton("Réinitialiser les paramètres");
        this.reset.setPreferredSize(dim);  
        buttons.add(this.reset);

        this.start = new JButton("Commencer le jeu");
        this.start.setPreferredSize(dim);  
        buttons.add(this.start);

        this.back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu("Menu principal", "Commencer le jeu", "Règles du jeu", "Quitter");
                menu.display();
                frame.dispose();
            }
        });

        this.reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                size.setValue(defaultSize);
                liveColor.setSelectedItem(defaultLiveColor);
                deadColor.setSelectedItem(defaultDeadColor);
                emoji.setSelected(defaultEmoji);
                algorithmComboBox.setSelectedIndex(0); 
            }
        });

        this.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedSize = (int) size.getValue();
                ColorItem selectedLiveColorItem = (ColorItem) liveColor.getSelectedItem();
                ColorItem selectedDeadColorItem = (ColorItem) deadColor.getSelectedItem();
                boolean emojisEnabled = emoji.isSelected();

                String selectedAlgorithm = (String) algorithmComboBox.getSelectedItem();
                Demo demo = null;
                DemoHashlife demoh = null;

                if (selectedAlgorithm.equals("BasicAlgo")) {
                    demo = new Demo(selectedSize, selectedLiveColorItem.getColor(), selectedDeadColorItem.getColor(), emojisEnabled);
                } else if (selectedAlgorithm.equals("HashlifeAlgo")) {
                    demoh = new DemoHashlife();
                }

                if (demo != null) {
                    demo.startSimulation();
                    demo.updateAliveCellCount();
                }

                
                frame.dispose(); 
            }
        });
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }
}

