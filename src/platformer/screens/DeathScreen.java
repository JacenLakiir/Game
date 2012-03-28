package platformer.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

public class DeathScreen extends Screen
{
    private static final String BACKGROUND_IMAGE = "resources/death_screen.png";

    private Background myBackground;
    
    public DeathScreen (GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources ()
    {
        hideCursor();
        setFPS(50);
        bsGraphics.setWindowTitle("Fail!");
        myBackground = new ImageBackground(getImage(BACKGROUND_IMAGE));
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        myBackground.update(elapsedTime);
        
        if (keyPressed(KeyEvent.VK_ENTER))
        {
            parent.nextGameID = 2;
            finish();
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        myBackground.render(g);
    }
}
