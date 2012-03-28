package platformer.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

public class LevelOneScreen extends Screen
{
    private static final String BACKGROUND_IMAGE = "resources/level_one_screen.png";

    private Background myBackground;
    
    public LevelOneScreen (GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources ()
    {
        hideCursor();
        bsGraphics.setWindowTitle("Level One");
        myBackground = new ImageBackground(getImage(BACKGROUND_IMAGE));
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        myBackground.update(elapsedTime);

        if (keyPressed(KeyEvent.VK_ENTER))
        {
            parent.nextGameID = 3;
            finish();
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        myBackground.render(g);
    }
}