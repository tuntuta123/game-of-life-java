package conway.gameBasicAlgo.tests;

import conway.gameBasicAlgo.demo.Demo;
import javax.swing.*;
import java.awt.*;

public class DemoTest {
    public DemoTest(){}
    public static void runTests(){
        System.out.println(":Tests pour le Demo");
        Demo demo = new Demo(10, Color.PINK, Color.WHITE, false);

//Test: Commancer Button
	assert !demo.active : "Error : pas active imediatement, car pour commance je doit clicque le button start";
        demo.start.doClick(); 
	assert demo.active : "Error: Demo doit etre active apres on clique sur 'Commencer'.";
        System.out.println("Tests passes pour le button Commancer");

//Test: Avancer et Pause Buttons
        if (demo.simulationTimer.isRunning()) {
            demo.simulationTimer.stop();
        }

        demo.play.doClick();
        if (demo.active) {
            assert demo.simulationTimer.isRunning() : "Error: Timer doit etre active apres on clique sur button Avancer ";
        }
        System.out.println("Tests passes pour le button Avancer");

        demo.stop.doClick();
        assert !demo.simulationTimer.isRunning() : "Error : Timer doit areter apres on clique sur button Pause";
        System.out.println("Tests passes pour le button Pause");

//Test: Suivante Button
        int countOfGeneration = demo.generationCount;
        demo.next.doClick();
        assert demo.generationCount == countOfGeneration + 1 : "Error: Le nombre de generation doit augmente pour 1";
        System.out.println("Tests passes pour le button Suivant");

//Test: modeComboBox
        demo.modeComboBox.setSelectedItem("Aléatoire");
        assert demo.generationCount == 0 : "Error: Le nombre de genration doit etre 0 sur  'Aléatoire' mode.";
        assert !demo.figureComboBox.isEnabled() : "Eror : Mode 'Aléatoire' doit etre inactive , parce que vous jouez dans cette mode";

        demo.modeComboBox.setSelectedItem("Le joueur choisit");
        assert demo.generationCount == 0 : "Error: Le nombre de genration doit etre 0 sur 'Le joueur choisit' mode.";
        assert !demo.figureComboBox.isEnabled() :  "Eror : Mode 'Le joueur choisit' doit etre inactive , parce que vous jouez dans cette mode";

        demo.modeComboBox.setSelectedItem("Figures");
        assert demo.generationCount == 0 : "Mode ComboBox Test Failed: Generation count should reset to 0 in 'Figures' mode.";
        assert demo.figureComboBox.isEnabled() : "Eror : Mode 'Figures' doit etre inactive , parce que vous jouez dans cette mode";

        System.out.println("Tests passes pour tout des modes dans modeComboBox");

//Test: Test pour les cellules vivant et mort avec couleur
        demo.initializeRandomGrid();
        demo.updateAliveCellCount();  
        for (int x = 0; x < demo.size; x++) {
            for (int y = 0; y < demo.size; y++) {
                Color a = demo.gridPanel.getCellAlive();
					 Color d = demo.gridPanel.getCellDead(); 
                boolean isAlive = demo.grid.getNode(x, y).isAlive();
                if (isAlive) {
                    assert a.equals(Color.PINK) : "Cellule vivant couleur est incorect positionee a (" + x + ", " + y + ")";
                } else {
                    assert d.equals(Color.WHITE) : "Cellule mort couleur est incorect positionee a (" + x + ", " + y + ")";
                }
            }
        }
        System.out.println("Tests passes pour les couleurs pour les cellules mort et cellules vivant ");

//Test : Compteur pour les cellules vivantes 
        int aliveCount = 0;
        for (int x = 0; x < demo.size; x++) {
            for (int y = 0; y < demo.size; y++) {
                if (demo.grid.getNode(x, y).isAlive()) {
                    aliveCount++;
                }
            }
        }
        demo.updateAliveCellCount();
        String labelText = demo.aliveCellLabel.getText();
        assert labelText.contains(String.valueOf(aliveCount)) : "Error : Le nombre des cellules vivantes doit etre  " + aliveCount + ", mais etais '" + labelText + "'";
        System.out.println("Tests passes pour comptage des cellules vivantes");

        demo.dispose();
	}

}	
