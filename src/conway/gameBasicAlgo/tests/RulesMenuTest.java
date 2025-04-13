package conway.gameBasicAlgo.tests;

import conway.menu.*;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RulesMenuTest {

    public RulesMenuTest() {}

    public static void runTests() {
        System.out.println(": Tests pour le RulesMenu");

        RulesMenu rulesMenu = new RulesMenu("Règles du Jeu");

        assert rulesMenu.frame != null : "Error: RulesMenu frame should be initialized.";
        assert rulesMenu.titleLabel != null : "Error: Title label should be initialized.";
        assert rulesMenu.rulesText != null : "Error: Rules text pane should be initialized.";
        assert rulesMenu.imgLabel1 != null : "Error: First image label should be initialized.";
        assert rulesMenu.imgLabel2 != null : "Error: Second image label should be initialized.";
        assert rulesMenu.step1 != null : "Error: Step 1 label should be initialized.";
        assert rulesMenu.step2 != null : "Error: Step 2 label should be initialized.";
        assert rulesMenu.back != null : "Error: Back button should be initialized.";

        System.out.println("Tests passed for RulesMenu initialization.");

        AtomicBoolean backButtonClicked = new AtomicBoolean(false);
        rulesMenu.back.addActionListener(e -> {
            backButtonClicked.set(true);
            rulesMenu.frame.dispose();
        });

        SwingUtilities.invokeLater(() -> {
            rulesMenu.back.doClick();
        });

        try {
            Thread.sleep(200); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert backButtonClicked.get() : "Error: Back button should navigate back to the main menu.";
        assert !rulesMenu.frame.isDisplayable() : "Error: RulesMenu frame should be disposed.";
        System.out.println("Tests passed for the Back button action.");

        assert rulesMenu.rulesText.getText().contains("Le Jeu de la Vie est un système dynamique") : "Error: Rules text should contain the correct description.";
        System.out.println("Tests passed for rulesText content.");

        assert rulesMenu.imgLabel1 != null : "Error: First image should be initialized.";
        assert rulesMenu.imgLabel2 != null : "Error: Second image should be initialized.";
        System.out.println("Tests passed for images.");

        rulesMenu.frame.dispose();
    }
}

