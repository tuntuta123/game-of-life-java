/*package conway.tests;

import conway.algo.*;
import conway.logic.*;

public class BasicAlgoTest {

    public static void runTests() {
        // Run each individual test
        testGenerateWithCacheHit();
        testGenerateWithCacheUse();
        testGenerateWithNewNodeState();
        testGetNeighborKeyWithAliveNeighbor();
        testGetNeighborKeyWithDeadNeighbor();
        testGetKeyForNode();
    }

    // Test 1: Vérifie si le cache est utilisé correctement.
    public static void testGenerateWithCacheHit() {
        Grid grid = new Grid(3); // Create a 3x3 grid
        Rule rule = new Conway(); // Use ConwayRule for this test
        BasicAlgo basicAlgo = new BasicAlgo(grid, rule);

        Node node = new Node(); // Create a mock node
        node.setAlive(true); // Set the node to alive
        grid.setNode(0, 0, node); // Place the node in the grid

        // Check that the cache is empty initially
        assert grid.cache.isEmpty() : "Cache should be empty at the beginning.";

        // Generate the next generation
        basicAlgo.generate();

        // The cache should now contain the node's state
        assert !grid.cache.isEmpty() : "Cache should contain entries after generation.";
    }

    // Test 2: Vérifie si le cache est utilisé et la règle n'est pas appliquée une deuxième fois.
    public static void testGenerateWithCacheUse() {
        Grid grid = new Grid(3); 
        Rule rule = new Conwa(); 
        BasicAlgo basicAlgo = new BasicAlgo(grid, rule);

        Node node = new Node();
        node.setAlive(true); 
        grid.setNode(0, 0, node);

        // Add the state to the cache
        basicAlgo.cache.put(basicAlgo.getKey(0, 0), true);

        // Call generate()
        basicAlgo.generate();

        // The cache should have been used, avoiding recalculation
        boolean cacheHit = basicAlgo.cache.containsKey(basicAlgo.getKey(0, 0));
        assert cacheHit : "Cache should have been used, avoiding recalculating the state.";
    }

    // Test 3: Vérifie la mise à jour correcte de l'état des cellules dans la grille.
    public static void testGenerateWithNewNodeState() {
        Grid grid = new Grid(3); 
        Rule rule = new Conway(); 
        BasicAlgo basicAlgo = new BasicAlgo(grid, rule);

        Node node = new Node();
        node.setAlive(false); // Set the node as dead
        grid.setNode(0, 0, node);

        // Check that the node is initially dead
        assert !grid.getNode(0, 0).isAlive() : "Node should be dead initially.";

        // Generate the next generation
        basicAlgo.generate();

        // Check if the node's state has been correctly updated
        assert grid.getNode(0, 0).isAlive() : "Node state should have been updated after generation.";
    }

    // Test 4: Vérifie la méthode getNeighborKey() pour un voisin vivant.
    public static void testGetNeighborKeyWithAliveNeighbor() {
        Node neighbor = new Node();
        neighbor.setAlive(true); // Set the neighbor as alive

        BasicAlgo basicAlgo = new BasicAlgo(new Grid(3), new Conway());
        int key = basicAlgo.getNeighborKey(neighbor);

        // Check that the alive neighbor returns 1
        assert key == 1 : "Alive neighbor should return 1.";
    }

    // Test 5: Vérifie la méthode getNeighborKey() pour un voisin mort.
    public static void testGetNeighborKeyWithDeadNeighbor() {
        Node neighbor = new Node();
        neighbor.setAlive(false); // Set the neighbor as dead

        BasicAlgo basicAlgo = new BasicAlgo(new Grid(3), new Conway());
        int key = basicAlgo.getNeighborKey(neighbor);

        // Check that the dead neighbor returns 0
        assert key == 0 : "Dead neighbor should return 0.";
    }

    // Test 6: Vérifie la génération d'une clé unique pour un noeud et ses voisins.
    public static void testGetKeyForNode() {
        Grid grid = new Grid(3);
        Node node = new Node();
        Node northNeighbor = new Node();
        Node southNeighbor = new Node();

        node.setAlive(true);
        northNeighbor.setAlive(false);
        southNeighbor.setAlive(true);

        grid.setNode(0, 0, node);
        grid.setNode(0, 1, northNeighbor);
        grid.setNode(1, 0, southNeighbor);

        BasicAlgo basicAlgo = new BasicAlgo(grid, new Conway());

        // Calculate the key for the node (with its neighbors)
        int key = basicAlgo.getKey(0, 0);

        // Check that the key is valid (not zero or undefined)
        assert key != 0 : "The generated key for the node should not be 0.";
    }
}*/

