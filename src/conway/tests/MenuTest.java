package conway.tests;

import conway.graphics.menu.Menu;
import javax.swing.*;

public class MenuTest {

    public MenuTest() {}

    public static void runTests() {
        System.out.println(": Tests pour le Menu");

        Menu menu = new Menu("Menu principal", "Commencer le jeu", "Les règles du jeu", "Quitter");

        boolean allTestsPassed = true;

        try {
            assert (menu.frame != null) : "Error: Menu frame was not initialized properly.";
            assert (menu.start != null) : "Error: Start button was not initialized properly.";
            assert (menu.rules != null) : "Error: Rules button was not initialized properly.";
            assert (menu.quit != null) : "Error: Quit button was not initialized properly.";
            assert (menu.title != null) : "Error: Title label was not initialized properly.";
            System.out.println("Tests passed for menu initialization.");
        } catch (AssertionError e) {
            allTestsPassed = false;
            System.out.println(e.getMessage());
        }

        final boolean[] subMenuDisplayed = {false};
        menu.start.addActionListener(e -> {
            subMenuDisplayed[0] = true;
            menu.frame.dispose();
        });

        try {
            SwingUtilities.invokeAndWait(() -> menu.start.doClick());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            assert subMenuDisplayed[0] : "Error: Start button did not trigger the expected action.";
            System.out.println("Tests passed for the Start button action.");
        } catch (AssertionError e) {
            allTestsPassed = false;
            System.out.println(e.getMessage());
        }

        final boolean[] rulesMenuDisplayed = {false};
        menu.rules.addActionListener(e -> {
            rulesMenuDisplayed[0] = true;
            menu.frame.dispose();
        });

        try {
            SwingUtilities.invokeAndWait(() -> menu.rules.doClick());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            assert rulesMenuDisplayed[0] : "Error: Rules button did not trigger the expected action.";
            System.out.println("Tests passed for the Rules button action.");
        } catch (AssertionError e) {
            allTestsPassed = false;
            System.out.println(e.getMessage());
        }

        try {
            assert !menu.frame.isDisplayable() : "Error: Frame was not disposed properly.";
            System.out.println("Tests passed for frame disposal.");
        } catch (AssertionError e) {
            allTestsPassed = false;
            System.out.println(e.getMessage());
        }

        if (allTestsPassed) {
            System.out.println("All tests for Menu passed.");
        }

        menu.frame.dispose();
    }
}

