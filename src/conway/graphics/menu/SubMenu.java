package conway.graphics.menu;

import conway.demo.Demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SubMenu implements MenuInterface {

    private JFrame frame;
    private String title;
    private JSpinner size;
    private JComboBox<ColorItem> liveColor, deadColor;
    private JCheckBox emoji;
    private JButton back, reset, start; 
    private JPanel panel;

    private final int defaultSize = 10;
    private final ColorItem defaultLiveColor = new ColorItem(Color.RED, "Red");
    private final ColorItem defaultDeadColor = new ColorItem(Color.WHITE, "Pink");
    private final boolean defaultEmoji = false;

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
        JLabel sizeLabel = new JLabel("Choisissez la taille de la grille (3 - 100):");
        sizeLabel.setBounds(50, 50, 400, 40);  
        this.panel.add(sizeLabel);

        SpinnerNumberModel spinner = new SpinnerNumberModel(this.defaultSize, 3, 100, 1);
        this.size = new JSpinner(spinner);
        this.size.setBounds(400, 50, 200, 40);  
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
        this.liveColor.setBounds(400, 120, 200, 40);
        this.panel.add(this.liveColor);

        JLabel deadLabel = new JLabel("Choisissez la couleur des cellules mortes :");
        deadLabel.setBounds(50, 190, 500, 40);  
        this.panel.add(deadLabel);

        List<ColorItem> deadColors = new ArrayList<>();
        deadColors.add(new ColorItem(Color.WHITE, "Blanc"));
        deadColors.add(new ColorItem(Color.GRAY, "Grise"));
        deadColors.add(new ColorItem(Color.LIGHT_GRAY, "Gris clair"));
        deadColors.add(new ColorItem(Color.BLACK, "Noir"));

        this.deadColor = new JComboBox<>(deadColors.toArray(new ColorItem[0]));
        this.deadColor.setBounds(400, 190, 200, 40);  
        this.panel.add(this.deadColor);
        
        this.emoji = new JCheckBox("Enable Emojis");
        this.emoji.setBounds(50, 270, 300, 40);
        this.panel.add(this.emoji);

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
            }
        });

        this.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedSize = (int) size.getValue();
                ColorItem selectedLiveColorItem = (ColorItem) liveColor.getSelectedItem();
                ColorItem selectedDeadColorItem = (ColorItem) deadColor.getSelectedItem();
                boolean emojisEnabled = emoji.isSelected();

                Demo demo = new Demo(selectedSize, selectedLiveColorItem.getColor(), selectedDeadColorItem.getColor(), emojisEnabled);
                demo.startSimulation();
                demo.updateAliveCellCount();
                frame.dispose();
            }
        });
    }

    @Override
    public void display() {
        this.frame.setVisible(true);
    }
}

