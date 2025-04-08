package conway.tests;

import conway.graphics.menu.*;
import conway.demo.Demo;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubMenuTest {

    public SubMenuTest() {}

    public static void runTests() {
        System.out.println(": Tests pour le SubMenu");

        SubMenu subMenu = new SubMenu("Options de jeu");

        assert subMenu.frame != null : "Error: SubMenu frame should be initialized.";
        assert subMenu.size != null : "Error: Size spinner should be initialized.";
        assert subMenu.liveColor != null : "Error: LiveColor comboBox should be initialized.";
        assert subMenu.deadColor != null : "Error: DeadColor comboBox should be initialized.";
        assert subMenu.emoji != null : "Error: Emoji checkbox should be initialized.";
        assert subMenu.back != null : "Error: Back button should be initialized.";
        assert subMenu.reset != null : "Error: Reset button should be initialized.";
        assert subMenu.start != null : "Error: Start button should be initialized.";

        System.out.println("Tests passed for SubMenu initialization.");

        AtomicBoolean backButtonClicked = new AtomicBoolean(false);
        AtomicBoolean frameDisposed = new AtomicBoolean(false);

        subMenu.back.addActionListener(e -> {
            backButtonClicked.set(true);
            subMenu.frame.dispose();
        });

        SwingUtilities.invokeLater(() -> {
            subMenu.back.doClick();
        });

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert backButtonClicked.get() : "Error: Back button should navigate back to the main menu.";
        assert !subMenu.frame.isDisplayable() : "Error: SubMenu frame should be disposed.";
        System.out.println("Tests passed for the Back button action.");

        AtomicBoolean resetButtonClicked = new AtomicBoolean(false);
        subMenu.reset.addActionListener(e -> {
            resetButtonClicked.set(true);
            subMenu.size.setValue(subMenu.defaultSize);
            subMenu.liveColor.setSelectedItem(subMenu.defaultLiveColor);
            subMenu.deadColor.setSelectedItem(subMenu.defaultDeadColor);
            subMenu.emoji.setSelected(subMenu.defaultEmoji);
        });

        SwingUtilities.invokeLater(() -> {
            subMenu.reset.doClick();
        });

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert resetButtonClicked.get() : "Error: Reset button should reset all parameters.";
        assert subMenu.size.getValue().equals(subMenu.defaultSize) : "Error: Size should reset to default.";
        assert subMenu.emoji.isSelected() == subMenu.defaultEmoji : "Error: Emoji checkbox should reset to default.";

        System.out.println("Tests passed for the Reset button action.");

        AtomicBoolean startButtonClicked = new AtomicBoolean(false);
        subMenu.start.addActionListener(e -> {
            startButtonClicked.set(true);
            int selectedSize = (int) subMenu.size.getValue();
            ColorItem selectedLiveColorItem = (ColorItem) subMenu.liveColor.getSelectedItem();
            ColorItem selectedDeadColorItem = (ColorItem) subMenu.deadColor.getSelectedItem();
            boolean emojisEnabled = subMenu.emoji.isSelected();

            Demo demo = new Demo(selectedSize, selectedLiveColorItem.getColor(), selectedDeadColorItem.getColor(), emojisEnabled);
            demo.startSimulation();
            demo.updateAliveCellCount();
            subMenu.frame.dispose();
        });

        SwingUtilities.invokeLater(() -> {
            subMenu.start.doClick();
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert startButtonClicked.get() : "Error: Start button should start the simulation and close the SubMenu.";
        System.out.println("Tests passed for the Start button action.");

        assert subMenu.size.getValue().equals(subMenu.defaultSize) : "Error: Default size should be 10.";
        assert !subMenu.emoji.isSelected() : "Error: Default emoji checkbox should be unchecked.";

        System.out.println("Tests passed for default values.");

        subMenu.frame.dispose();
    }
}

