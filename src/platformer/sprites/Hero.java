package platformer.sprites;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.AnimatedSprite;

@SuppressWarnings("serial")
public class Hero extends AnimatedSprite
{
    private static final String ANIMATIONS = "resources/hero_spritesheet.png";
    private static final int COLS = 8;
    private static final int ROWS = 9;
    
    public static final double RUN_SPEED = 3;
    public static final double GRAVITY = 4;
    public static final double JUMP_CEILING = 150;
    
    private GameEngine engine;
    
    private Set<Projectile> myProjectiles;
    private double myJumpCeiling;
    private boolean isOnGround;
    private boolean isInAir;
    private boolean isDying;
    private boolean isDead;
    
    public Hero (int x, int y, GameEngine game)
    {
        super(game.getImages(ANIMATIONS, COLS, ROWS), x, y);
        engine = game;
        initializeState();
        setAnimate(true);
    }
    
    @Override
    public void update (long elapsedTime)
    {   
        if (isDying)                dieByEnemy();
        else
        {
            if (isInAir)            executeAirCommands();
            else if (isOnGround)    executeGroundCommands();
            else                    stand();
        }
                
        for (Projectile p : myProjectiles)
            p.update(elapsedTime);
    }

    public void setOnGround (boolean state)
    {
        isOnGround = state;
        isInAir = !state;
    }
    
    public void setIsDying (boolean state)
    {
        isDying = state;
    }
    
    public void drop ()
    {
        setAnimationFrame(45, 47);
        setLoopAnim(false);
        setOnGround(false);
        moveY(GRAVITY);
    }
    
    public void dieByEnemy ()
    {
        setAnimationFrame(16, 23);
        setLoopAnim(false);
        updateAnimation();
        if (getFrame() == getFinishAnimationFrame())
            isDead = true;
    }
    
    public void dieByFalling ()
    {
        setAnimationFrame(26, 26);
        setLoopAnim(false);
        isDead = true;
    }
    
    public Set<Projectile> getProjectileSet ()
    {
        return Collections.unmodifiableSet(myProjectiles);
    }
    
    public boolean hasDied ()
    {
        return isDead;
    }
    
    private void initializeState ()
    {
        myProjectiles = new HashSet<Projectile>();
        isOnGround = true;
        isInAir = false;
        isDying = false;
        isDead = false;
        updateJumpCeiling();
    }

    private void executeGroundCommands ()
    {
        if (isFalling())        forceY(getOldY());

        if (spacebarKey())
        {
            updateJumpCeiling();
            jump();
        }        
        else if (letterAKey())  attack();
        else if (rightKey())    runRight();
        else if (leftKey())     runLeft();
        else                    stand();
    }
    
    private void executeAirCommands ()
    {
        if (getY() > myJumpCeiling && isRising())
            jump();
        else
            drop();
        
        if (letterAKey())   attack();
        if (rightKey())     moveX(RUN_SPEED);
        if (leftKey())      moveX(-1 * RUN_SPEED);
    }
    
    private boolean isFalling ()
    {
        return (getOldY() < getY());
    }
    
    private boolean isRising ()
    {
        return (getOldY() > getY());
    }
    
    private void updateJumpCeiling ()
    {
        myJumpCeiling = getY() - JUMP_CEILING;
    }
    
    private void stand ()
    {
        setAnimationFrame(64, 64);
        setLoopAnim(false);
        moveY(GRAVITY);
        setOnGround(false);
    }

    private void runRight ()
    {
        setAnimationFrame(4, 11);
        setLoopAnim(true);
        updateAnimation();
        move(RUN_SPEED, GRAVITY);
        setOnGround(false);
    }
    
    private void runLeft ()
    {
        setAnimationFrame(4, 11);
        setLoopAnim(true);
        updateAnimation();
        move(-1 * RUN_SPEED, GRAVITY);
        setOnGround(false);
    }
    
    private void jump ()
    {
        setAnimationFrame(43, 45);
        setLoopAnim(false);
        setOnGround(false);
        moveY(-1 * GRAVITY);
    }
    
    private void attack ()
    {
        setAnimationFrame(12, 15);
        setLoopAnim(false);
        myProjectiles.add(new Projectile(this, getX(), getY(), engine));
        engine.bsSound.play("resources/sound1.wav");
    }
    
    private boolean leftKey ()
    {
        return engine.keyDown(KeyEvent.VK_LEFT);
    }

    private boolean rightKey ()
    {
        return engine.keyDown(KeyEvent.VK_RIGHT);
    }

    private boolean spacebarKey ()
    {
        return engine.keyPressed(KeyEvent.VK_SPACE);
    }
    
    private boolean letterAKey()
    {
        return engine.keyPressed(KeyEvent.VK_A);
    }
    
}
