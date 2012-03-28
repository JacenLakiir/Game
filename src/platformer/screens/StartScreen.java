package platformer.screens;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.background.ImageBackground;

public class StartScreen extends Screen
{
    private static final String BACKGROUND_IMAGE = "resources/main_screen.png";
    private static final String MUSIC = "resources/Title_Screen.mid";
    
    private Background myBackground;

    public StartScreen (GameEngine parent)
    {
        super(parent);
    }

    @Override
    public void initResources ()
    {
        hideCursor();
        setFPS(50);
        isMusicOn = true;
        
        bsGraphics.setWindowTitle("Platformer");
        playMusic(MUSIC);
        myBackground = new ImageBackground(getImage(BACKGROUND_IMAGE));
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        myBackground.update(elapsedTime);
        
        if (keyPressed(KeyEvent.VK_ENTER))
        {
            parent.nextGameID = 1;
            finish();
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        myBackground.render(g);
    }
    
}