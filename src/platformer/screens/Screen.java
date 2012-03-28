package platformer.screens;

import java.awt.event.KeyEvent;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public abstract class Screen extends GameObject
{

    protected boolean isMusicOn;
    
    public Screen (GameEngine parent)
    {
        super(parent);
    }
    
    protected void checkForUserInput ()
    {
        if (letterIKey())   showInstructions();
        if (letterMKey())   toggleMusic();
        if (number1Key())   jumpToLevelOne();
        if (number2Key())   jumpToLevelTwo();
    }

    protected void toggleMusic ()
    {
        isMusicOn = !isMusicOn;
        if (isMusicOn)
        {
            bsMusic.setActive(true);
            bsMusic.play("resources/Title_Screen.mid");
        }
        else
            bsMusic.setActive(false);
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
    
    protected void showInstructions ()
    {
        parent.nextGameID = 7;
        finish();
    }
    
    protected boolean letterIKey ()
    {
        return keyPressed(KeyEvent.VK_I);
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
