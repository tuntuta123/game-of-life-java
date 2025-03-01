package conway.graphics.menu;

import conway.demo.Demo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SubMenu {

    private JFrame subMenuFrame;
    private String subMenuTitle;
    private JSpinner sizeSpinner; 
    private JButton applyButton;

    public SubMenu(String subMenuTitle) {
        this.subMenuTitle = subMenuTitle;

        subMenuFrame = new JFrame(subMenuTitle);
        subMenuFrame.setSize(400, 300);
        subMenuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        subMenuFrame.add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel sizeLabel = new JLabel("Choose Grid Size (1 - 100):");
        sizeLabel.setBounds(50, 50, 200, 30);
        panel.add(sizeLabel);

        SpinnerNumberModel sizeModel = new SpinnerNumberModel(10, 3, 200, 1);  
        sizeSpinner = new JSpinner(sizeModel);
        sizeSpinner.setBounds(200, 50, 100, 30);
        panel.add(sizeSpinner);

        applyButton = new JButton("Apply");
        applyButton.setBounds(150, 150, 100, 30);
        panel.add(applyButton);

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedSize = (int) sizeSpinner.getValue();

                Demo demo = new Demo(selectedSize); 
                demo.startSimulation();
                subMenuFrame.dispose();
            }
        });
    }

    public void display() {
        subMenuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SubMenu subMenu = new SubMenu("Grid Size Selection");
        subMenu.display();
    }
}

