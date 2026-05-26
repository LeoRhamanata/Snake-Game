import java.util.Random;

/**
 * Class Apple - extends Entity
 * Representasi buah apel yang dimakan ular.
 * Posisi apel di-generate secara acak.
 *
 * Role 1 - Class Architect
 */
public class Apple extends Entity {
    private static final char APPLE_SYMBOL = '@';
    private Random random;

    public Apple(int boardWidth, int boardHeight) {
        super(0, 0, APPLE_SYMBOL);
        this.random = new Random();
        respawn(boardWidth, boardHeight);
    }

    /**
     * Pindahkan apel ke posisi random baru di dalam board
     */
    public void respawn(int boardWidth, int boardHeight) {
        // Pastikan tidak spawn di border
        this.x = 1 + random.nextInt(boardWidth - 2);
        this.y = 1 + random.nextInt(boardHeight - 2);
    }

    @Override
    public char render() {
        return APPLE_SYMBOL;
    }
}