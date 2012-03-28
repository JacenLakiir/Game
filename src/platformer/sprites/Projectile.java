package platformer.sprites;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.AnimatedSprite;

@SuppressWarnings("serial")
public class Projectile extends AnimatedSprite
{
    
    private static final String ANIMATIONS = "resources/tile_spritesheet.png";
    private static final int COLS = 8;
    private static final int ROWS = 9;
    
    private static final double SPEED = 3;
    
    private Hero myOwner;
    private boolean isDetonating;
    private boolean isDetonated;    
    
    public Projectile (Hero owner, double x, double y, GameEngine game)
    {
        super(game.getImages(ANIMATIONS, COLS, ROWS), x, y);
        myOwner = owner;
        isDetonating = false;
        isDetonated = false;
    }
    
    @Override
    public void update (long elapsedTime)
    {
        if (isDetonated)            setActive(false);
        else if (isDetonating)      detonate();
        
        if (isOnScreen())           travel();
        
    }
    
    public Hero getOwner ()
    {
        return myOwner;
    }
    
    public void setIsDetonating (boolean state)
    {
        isDetonating = true;
    }
    
    private void travel ()
    {
        setAnimationFrame(5, 5);
        setLoopAnim(false);
        moveX(SPEED);
    }
    
    private void detonate ()
    {
        setAnimationFrame(0, 2);
        setLoopAnim(false);
        updateAnimation();
        if (getFrame() == getFinishAnimationFrame())
            isDetonated = true;
    }
}
