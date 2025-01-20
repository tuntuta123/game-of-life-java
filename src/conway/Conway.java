import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Human extends Element {
    public Human() {
        super("\uD83D\uDE00"); 
    }
}
       
public class Conway extends PlanetTk {
    private int width;
    private int height;
    private int cellSize;
    private int gutterSize;
    private int marginSize;

    private JButton startButton;
    private JButton nextButton;
    private JButton quitButton;

    private int[][] cells;
    private boolean running;
    private Timer timer;


    public Conway(JFrame master, int width, int height, int cellSize, int gutterSize, int marginSize) {
        super(master, height, width, cellSize, gutterSize, marginSize, "white", "grey", "white");
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.gutterSize = gutterSize;
        this.marginSize = marginSize;

        initializeCells();
        createControlButtons(master);
        setupTimer();
        running = false;
        drawBoard();

        master.getContentPane().setLayout(new BorderLayout());
        master.getContentPane().add(this, BorderLayout.CENTER);
        master.pack();
        master.setResizable(false);
        master.setLocationRelativeTo(null); 
        master.setVisible(true);
    }  
        
    private void initializeCells() {
        cells = new int[height][width];
        Random rand = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = rand.nextInt(2);
            }
        }
    }
    
    private void createControlButtons(JFrame master) {
        startButton = new JButton("Commencer");
        nextButton = new JButton("Suivant");
        quitButton = new JButton("Quitter");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(quitButton);


        master.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextMove();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

//dali mozebi ova e poefikasno i tocno za koristenje
//startButton.addActionListener(e -> startGame());
//nextButton.addActionListener(e -> nextMove());
//quitButton.addActionListener(e -> System.exit(0));
  
        # Place un élément graphique représentant un 'Human' dans chaque cellule de la grille.
        for cell_number in range(width * height):
            i, j = PlanetTk.get_coordinates_from_cell_number(self.__grid_canvas,cell_number)
            x = j * (cell_size + gutter_size) + margin_size
            y = i * (cell_size + gutter_size) + margin_size
            self.__grid_canvas.create_text(x + cell_size // 2, y + cell_size // 2, text=Human(), fill="white", font=('Arial', cell_size // 2, 'bold'), tags=(f't_{i}_{j}', f't_{cell_number}'))
    


    private void setupTimer() {
        timer = new Timer(500, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBoard();
            }
        });
    }
//isto taka i za ova mozebi podobro e so ova
//timer = new Timer(500, e -> updateBoard());

    private void startGame() {
        if (!running) {
            running = true;
            timer.start();
        }
    }

    private void nextMove() {
        if (!running) {
            updateBoard();
        }
    }


   public void drawBoard() {
       for (int y = 0; y < height; y++) {
           for (int x = 0; x < width; x++) {
               boolean filled = cells[y][x] == 1;
	       int cellNumber = y * width + x;
	       setCellColorConway(cellNumber, filled, cells);
	       if (filled) {
		   setCellText(cellNumber, new Human().toString()); 
               } else {
		   setCellText(cellNumber, ""); 
	       }
            }
        }
	repaint();
   }
		        
    private int countNeighbors(int x, int y) {
        int count = 0;
        for (int dy = -1; dy <=1; dy++) {
            for (int dx = -1; dx <=1; dx++) {
                if (dx == 0 && dy == 0) continue; 
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < width && ny >=0 && ny < height) {
                    count += cells[ny][nx];
                }
            }
        }
        return count;
    


    private void updateBoard() {
        int[][] newCells = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighbors = countNeighbors(x, y);
                if (cells[y][x] == 1 && (neighbors < 2 || neighbors > 3)) {
                    newCells[y][x] = 0; 
                } else if (cells[y][x] == 0 && neighbors == 3) {
                    newCells[y][x] = 1; 
                } else {
                    newCells[y][x] = cells[y][x]; 
                }
            }
        }
        cells = newCells;
        drawBoard(); 
    }

    public static void main(String[] args) {
        // go pravi master framot
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Conway(frame, 20, 20, 40, 0, 1);
    }
}
