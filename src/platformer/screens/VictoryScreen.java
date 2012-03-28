package platformer.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

public class VictoryScreen extends Screen
{
    
    private static final String BACKGROUND_IMAGE = "resources/victory_screen.png";
    
    private Background myBackground;
    
    public VictoryScreen (GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources ()
    {
        hideCursor();
        setFPS(50);
        bsGraphics.setWindowTitle("Victory!");
        myBackground = new ImageBackground(getImage(BACKGROUND_IMAGE));
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        myBackground.update(elapsedTime);
        
        if (keyPressed(KeyEvent.VK_ENTER))
        {
            parent.nextGameID = 0;
            finish();
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        myBackground.render(g);
    }
}
