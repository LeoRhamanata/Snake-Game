import javax.swing.*;

/**
 * Main - Entry Point Program
 * Role 3 - UI & Robustness Engineer
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Jalankan di Event Dispatch Thread (EDT) - standar Swing
            SwingUtilities.invokeLater(() -> {
                new GameFrame();
            });
        } catch (Exception e) {
            System.err.println("[FATAL ERROR] " + e.getMessage());
            e.printStackTrace();
        }
    }
}