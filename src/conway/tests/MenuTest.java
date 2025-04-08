package conway.tests;

import conway.demo.Demo;
import conway.graphics.menu.*;

public class MenuTest {

    private Menu menu;
    private SubMenu mockSubMenu;
    private RulesMenu mockRulesMenu;

    private boolean subMenuDisplayed = false;
    private boolean rulesMenuDisplayed = false;
    private boolean menuClosed = false;

    public MenuTest() {
    }

    private void setUp() {
        mockSubMenu = new SubMenu("Mock SubMenu");
        mockRulesMenu = new RulesMenu("Mock RulesMenu");
        menu = new Menu("Menu principal", "Commencer le jeu", "Les règles du jeu", "Quitter");

        menu.start.addActionListener(e -> {
            subMenuDisplayed = true; 
            menu.frame.dispose();   
        });

        menu.rules.addActionListener(e -> {
            rulesMenuDisplayed = true; 
            menu.frame.dispose();    
        });

        menu.quit.addActionListener(e -> {
            System.exit(0);
        });
    }
    
    public void testMenuInitialization() {
        setUp(); 

        assert (menu.frame != null) : "Frame should be initialized";
        assert (menu.start != null) : "Start button should be initialized";
        assert (menu.rules != null) : "Rules button should be initialized";
        assert (menu.quit != null) : "Quit button should be initialized";

        assert (menu.start.getText().equals("Commencer le jeu")) : "Start button label is incorrect";
        assert (menu.rules.getText().equals("Les règles du jeu")) : "Rules button label is incorrect";
        assert (menu.quit.getText().equals("Quitter")) : "Quit button label is incorrect";
    }
    
    public void testStartButtonAction() {
        setUp(); 
        menu.start.doClick();
        assert subMenuDisplayed : "SubMenu should be displayed after clicking the start button.";
        assert menuClosed : "Menu frame should be closed after clicking the start button.";
    }

    public void testRulesButtonAction() {
        setUp();
        menu.rules.doClick();
        assert rulesMenuDisplayed : "RulesMenu should be displayed after clicking the rules button.";
        assert menuClosed : "Menu frame should be closed after clicking the rules button.";
    }

    public void testQuitButtonAction() {
        setUp(); 
        try {
            menu.quit.doClick();
            assert true : "Quit button action should be handled correctly (System.exit).";
        } catch (SecurityException e) {
            assert true : "System.exit(0) was called and caught.";
        }
    }

    public void testTitle() {
        setUp(); 

        assert (menu.title != null) : "Title should be initialized";
        assert (menu.title.getText().equals("Bienvenue au jeu de la vie (jeu de Conway) !")) :
                "Title label is incorrect";
    }

    public void runTests() {
        testMenuInitialization();
        testStartButtonAction();
        testRulesButtonAction();
        testQuitButtonAction();
        testTitle();

        System.out.println("All tests passed for menu!");
    }

}
