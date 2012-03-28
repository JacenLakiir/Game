package platformer.sprites;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.AnimatedSprite;

@SuppressWarnings("serial")
public class Skeleton extends AnimatedSprite
{
    
    private static final String ANIMATIONS = "resources/skeleton_spritesheet.png";
    private static final int COLS = 9;
    private static final int ROWS = 8;
    
    public static final double RUN_SPEED = 1;
    public static final double GRAVITY = 4;
    
    private boolean isOnGround;
    private boolean isDying;
    private boolean isDead;
    
    public Skeleton (double x, double y, GameEngine game)
    {
        super(game.getImages(ANIMATIONS, COLS, ROWS), x, y);
        setAnimate(true);
        intializeState();
    }
    
    @Override
    public void update (long elapsedTime)
    {
        if (isDead)             setActive(false);
        else if (isDying)       dieByEnemy();
        
        if (isOnScreen())
        {
            if (isOnGround && getOldX() >= getX())     runLeft();
            else if (isOnGround && getOldX() <= getX())  runRight();
            else                drop();
        }
    }
    
    public void setOnGround (boolean state)
    {
        isOnGround = state;
    }
    
    public void setIsDying (boolean state)
    {
        isDying = state;
    }
    
    public void drop ()
    {
        setAnimationFrame(27, 27);
        setLoopAnim(false);
        setOnGround(false);
        moveY(GRAVITY);
    }
    
    public void dieByEnemy ()
    {
        setAnimationFrame(7, 8);
        setLoopAnim(false);
        updateAnimation();
        if (getFrame() == getFinishAnimationFrame())
            isDead = true;
    }
    
    public void dieByFalling ()
    {
        setAnimationFrame(27, 27);
        setLoopAnim(false);
        isDead = true;
    }
    
    private void intializeState ()
    {
        isOnGround = true;
        isDying = false;
        isDead = false;
    }
    
    public void runLeft ()
    {
        if (isFalling())
            forceY(getOldY());
        setAnimationFrame(18, 24);
        setLoopAnim(true);
        updateAnimation();
        move(-1 * RUN_SPEED, GRAVITY);
        setOnGround(false);
    }
    
    public void runRight ()
    {
        if (isFalling())
            forceY(getOldY());
        setAnimationFrame(54, 60);
        setLoopAnim(true);
        updateAnimation();
        move(RUN_SPEED, GRAVITY);
        setOnGround(false);
    }
    
    private boolean isFalling ()
    {
        return (getOldY() < getY());
    }
    
}
