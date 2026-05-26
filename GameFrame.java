import javax.swing.*;
import java.awt.*;

/**
 * GameFrame - JFrame utama window game
 * Role 2 - Data & Logic Engineer
 */
public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame() {
        gamePanel = new GamePanel();

        setTitle("Snake Game - Final Project PBO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel judul
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(10, 10, 10));
        JLabel titleLabel = new JLabel("🐍 SNAKE GAME OOP");
        titleLabel.setForeground(new Color(80, 220, 100));
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        titlePanel.add(titleLabel);

        add(titlePanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // tengah layar
        setVisible(true);

        gamePanel.requestFocusInWindow();
    }
}