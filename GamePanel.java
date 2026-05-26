import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * GamePanel - JPanel utama tempat game dirender
 * Menggantikan GameBoard + GameController versi console
 * Role 2 - Data & Logic Engineer
 */
public class GamePanel extends JPanel implements ActionListener {

    // Ukuran board
    static final int TILE = 25;        // ukuran tiap kotak (pixel)
    static final int COLS = 24;        // jumlah kolom
    static final int ROWS = 20;        // jumlah baris
    static final int WIDTH  = COLS * TILE;
    static final int HEIGHT = ROWS * TILE;

    private Snake snake;
    private Apple apple;
    private Timer timer;

    private int score;
    private int highScore;
    private boolean running;
    private boolean gameOver;

    // Collections - ArrayList riwayat skor
    private ArrayList<Integer> scoreHistory;

    // Warna
    private static final Color COLOR_BG         = new Color(15, 15, 15);
    private static final Color COLOR_GRID        = new Color(25, 25, 25);
    private static final Color COLOR_HEAD        = new Color(80, 220, 100);
    private static final Color COLOR_BODY        = new Color(50, 170, 70);
    private static final Color COLOR_APPLE       = new Color(220, 60, 60);
    private static final Color COLOR_TEXT        = new Color(220, 220, 220);
    private static final Color COLOR_SCORE       = new Color(255, 200, 0);
    private static final Color COLOR_OVERLAY     = new Color(0, 0, 0, 180);

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 50));
        setBackground(COLOR_BG);
        setFocusable(true);
        scoreHistory = new ArrayList<>();
        highScore = 0;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKey(e.getKeyCode());
            }
        });
        startGame();
    }

    /**
     * Inisialisasi / restart game
     */
    public void startGame() {
        snake = new Snake(COLS / 2, ROWS / 2);
        apple = new Apple(COLS, ROWS);
        respawnAppleSafely();
        score = 0;
        running = true;
        gameOver = false;

        if (timer != null) timer.stop();
        timer = new Timer(150, this);
        timer.start();
        requestFocusInWindow();
    }

    /**
     * Game loop - dipanggil tiap tick Timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkCollisions();
        }
        repaint();
    }

    /**
     * Cek semua collision
     */
    private void checkCollisions() {
        int hx = snake.getX();
        int hy = snake.getY();

        // Tabrakan dinding
        if (hx < 0 || hx >= COLS || hy < 0 || hy >= ROWS) {
            endGame();
            return;
        }

        // Tabrakan tubuh sendiri
        if (snake.isSelfCollision()) {
            endGame();
            return;
        }

        // Makan apel
        if (hx == apple.getX() && hy == apple.getY()) {
            snake.eatApple();
            score += 10;
            respawnAppleSafely();
        }
    }

    private void endGame() {
        running = false;
        gameOver = true;
        timer.stop();
        scoreHistory.add(score);
        if (score > highScore) highScore = score;
    }

    private void respawnAppleSafely() {
        do {
            apple.respawn(COLS, ROWS);
        } while (snake.occupies(apple.getX(), apple.getY()));
    }

    /**
     * Handle input keyboard - WASD & Arrow Keys
     * Role 3 - InputHandler
     */
    private void handleKey(int keyCode) {
        try {
            if (gameOver) {
                if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_R) {
                    startGame();
                }
                return;
            }
            switch (keyCode) {
                case KeyEvent.VK_W: case KeyEvent.VK_UP:    snake.changeDirection('W'); break;
                case KeyEvent.VK_S: case KeyEvent.VK_DOWN:  snake.changeDirection('S'); break;
                case KeyEvent.VK_A: case KeyEvent.VK_LEFT:  snake.changeDirection('A'); break;
                case KeyEvent.VK_D: case KeyEvent.VK_RIGHT: snake.changeDirection('D'); break;
            }
        } catch (Exception e) {
            System.err.println("[InputHandler] Error key: " + e.getMessage());
        }
    }

    /**
     * Render semua elemen game
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2);
        drawApple(g2);
        drawSnake(g2);
        drawHUD(g2);

        if (gameOver) drawGameOver(g2);
    }

    private void drawBackground(Graphics2D g) {
        g.setColor(COLOR_BG);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Grid tipis
        g.setColor(COLOR_GRID);
        for (int col = 0; col <= COLS; col++)
            g.drawLine(col * TILE, 0, col * TILE, HEIGHT);
        for (int row = 0; row <= ROWS; row++)
            g.drawLine(0, row * TILE, WIDTH, row * TILE);
    }

    private void drawApple(Graphics2D g) {
        int ax = apple.getX() * TILE;
        int ay = apple.getY() * TILE;
        g.setColor(COLOR_APPLE);
        g.fillOval(ax + 3, ay + 3, TILE - 6, TILE - 6);
        g.setColor(COLOR_APPLE.brighter());
        g.fillOval(ax + 6, ay + 5, 6, 5); // highlight
    }

    private void drawSnake(Graphics2D g) {
        java.util.LinkedList<int[]> body = snake.getBody();
        for (int i = body.size() - 1; i >= 0; i--) {
            int[] seg = body.get(i);
            int sx = seg[0] * TILE;
            int sy = seg[1] * TILE;
            if (i == 0) {
                // Kepala
                g.setColor(COLOR_HEAD);
                g.fillRoundRect(sx + 1, sy + 1, TILE - 2, TILE - 2, 8, 8);
                // Mata
                g.setColor(Color.BLACK);
                g.fillOval(sx + 5, sy + 6, 4, 4);
                g.fillOval(sx + 15, sy + 6, 4, 4);
            } else {
                // Body — makin belakang makin gelap
                float ratio = 1f - (float) i / body.size() * 0.4f;
                g.setColor(COLOR_BODY.darker().brighter());
                g.fillRoundRect(sx + 2, sy + 2, TILE - 4, TILE - 4, 6, 6);
            }
        }
    }

    private void drawHUD(Graphics2D g) {
        // Panel bawah
        g.setColor(new Color(20, 20, 20));
        g.fillRect(0, HEIGHT, WIDTH, 50);

        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.setColor(COLOR_SCORE);
        g.drawString("SCORE: " + score, 15, HEIGHT + 32);

        g.setColor(COLOR_TEXT);
        g.drawString("BEST: " + highScore, WIDTH / 2 - 40, HEIGHT + 32);

        g.setColor(new Color(100, 100, 100));
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.drawString("WASD / Arrow Keys", WIDTH - 160, HEIGHT + 32);
    }

    private void drawGameOver(Graphics2D g) {
        // Overlay gelap
        g.setColor(COLOR_OVERLAY);
        g.fillRect(0, 0, WIDTH, HEIGHT + 50);

        // Kotak tengah
        int bx = WIDTH / 2 - 140, by = HEIGHT / 2 - 90;
        g.setColor(new Color(30, 30, 30));
        g.fillRoundRect(bx, by, 280, 190, 20, 20);
        g.setColor(new Color(80, 220, 100));
        g.setStroke(new BasicStroke(2));
        g.drawRoundRect(bx, by, 280, 190, 20, 20);

        g.setFont(new Font("Monospaced", Font.BOLD, 28));
        g.setColor(new Color(220, 60, 60));
        g.drawString("GAME OVER", bx + 35, by + 50);

        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g.setColor(COLOR_SCORE);
        g.drawString("Score  : " + score, bx + 40, by + 90);
        g.setColor(COLOR_TEXT);
        g.drawString("Best   : " + highScore, bx + 40, by + 115);

        g.setFont(new Font("Monospaced", Font.BOLD, 14));
        g.setColor(new Color(80, 220, 100));
        g.drawString("ENTER / R = Main Lagi", bx + 35, by + 155);
    }

    // Getter untuk testing
    public int getScore() { return score; }
    public int getHighScore() { return highScore; }
    public Snake getSnake() { return snake; }
    public Apple getApple() { return apple; }
    public ArrayList<Integer> getScoreHistory() { return scoreHistory; }
    public boolean isGameOver() { return gameOver; }
    public boolean isRunning() { return running; }
}