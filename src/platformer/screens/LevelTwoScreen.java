package platformer.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

public class LevelTwoScreen extends Screen
{

    private static final String BACKGROUND_IMAGE = "resources/level_two_screen.png";

    private Background myBackground;
    
    public LevelTwoScreen (GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources ()
    {
        hideCursor();
        bsGraphics.setWindowTitle("Level Two");
        myBackground = new ImageBackground(getImage(BACKGROUND_IMAGE));
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        myBackground.update(elapsedTime);

        if (keyPressed(KeyEvent.VK_ENTER))
        {
            parent.nextGameID = 5;
            finish();
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        myBackground.render(g);
    }
}
