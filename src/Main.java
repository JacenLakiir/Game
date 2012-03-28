import java.awt.Dimension;
import platformer.PlatformerGame;
import com.golden.gamedev.GameLoader;


public class Main
{
    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new PlatformerGame(), new Dimension(720, 640), false);
        loader.start();
    }
}
