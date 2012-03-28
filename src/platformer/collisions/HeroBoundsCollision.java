package platformer.collisions;

import platformer.levels.Level;
import platformer.levels.LevelOne;
import platformer.levels.LevelTwo;
import platformer.sprites.Hero;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class HeroBoundsCollision extends CollisionBounds
{
    
    private Level level;
    
    public HeroBoundsCollision (Background backgr, Level l)
    {
        super(backgr);
        level = l;
    }

    @Override
    public void collided (Sprite sprite)
    {
        Hero player = (Hero) sprite;
        
        if (level instanceof LevelOne)
        {
            if (isCollisionSide(CollisionBounds.LEFT_COLLISION))
                player.moveX(Hero.RUN_SPEED);
            if (isCollisionSide(CollisionBounds.RIGHT_COLLISION))
                level.setHasWon(true);
            if (isCollisionSide(CollisionBounds.TOP_COLLISION))
                player.moveY(Hero.GRAVITY);
            if (isCollisionSide(CollisionBounds.BOTTOM_COLLISION))
                player.dieByFalling();
        }
        
        if (level instanceof LevelTwo)
        {
            if (isCollisionSide(CollisionBounds.LEFT_COLLISION))
                player.moveX(Hero.RUN_SPEED);
            if (isCollisionSide(CollisionBounds.RIGHT_COLLISION))
                player.moveX(-1 * Hero.RUN_SPEED);
            if (isCollisionSide(CollisionBounds.TOP_COLLISION))
               level.setHasWon(true);
            if (isCollisionSide(CollisionBounds.BOTTOM_COLLISION))
                player.dieByFalling();
        }
    }
    
}
