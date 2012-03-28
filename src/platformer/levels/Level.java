package platformer.levels;

import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public abstract class Level extends GameObject
{
    
    protected boolean isPaused;
    protected boolean isMusicOn;
    protected boolean hasWon;
    
    public Level (GameEngine parent)
    {
        super(parent);
    }
    
    public void setHasWon (boolean state)
    {
        hasWon = state;
    }
    
    protected void checkForUserInput ()
    {
        if (letterPKey())   pause();
        if (letterMKey())   toggleMusic();
        if (number1Key())   jumpToLevelOne();
        if (number2Key())   jumpToLevelTwo();
    }
    
    protected void reset ()
    {
        parent.nextGameID = 1;
        finish();
    }
    
    protected void pause()
    {
        isPaused = !isPaused;
    }
    
    protected void toggleMusic ()
    {
        isMusicOn = !isMusicOn;
        if (isMusicOn)
        {
            bsMusic.setActive(true);
            bsMusic.play(getMusic());
        }
        else
            bsMusic.setActive(false);
    }
    
    protected String getMusic ()
    {
        return null;
    }
    
    protected void jumpToLevelOne ()
    {
        parent.nextGameID = 2;
        finish();
    }
    
    protected void jumpToLevelTwo ()
    {
        parent.nextGameID = 4;
        finish();
    }
    
    protected boolean letterPKey ()
    {
        return keyPressed(KeyEvent.VK_P);
    }
    
    protected boolean letterRKey ()
    {
        return keyPressed(KeyEvent.VK_R);
    }
    
    protected boolean letterMKey ()
    {
        return keyPressed(KeyEvent.VK_M);
    }
    
    protected boolean number1Key ()
    {
        return keyPressed(KeyEvent.VK_1);
    }
    
    protected boolean number2Key ()
    {
        return keyPressed(KeyEvent.VK_2);
    }

}
