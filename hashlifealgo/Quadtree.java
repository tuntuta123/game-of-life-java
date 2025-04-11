public class Quadtree {
    private Node root;
    private int size;

    public Quadtree(int size, boolean[][] initialState) {
        this.size = size;
        this.root = new Node(size, initialState);
    }

    public void generate() {
        this.root = HashLifeAlgo.generateNextState(this);
    }

    public void printState() {
        for (boolean[] row : this.root.state) {
            for (boolean cell : row) {
                System.out.print(cell ? "1" : "0");
            }
            System.out.println();
        }
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

