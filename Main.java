import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                new GameFrame();
            });
        } catch (Exception e) {
            System.err.println("[FATAL ERROR] " + e.getMessage());
            e.printStackTrace();
        }
    }
}
