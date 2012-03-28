package platformer.collisions;

import platformer.levels.LevelOne;
import platformer.levels.LevelTwo;
import platformer.sprites.Skeleton;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class SkeletonBoundsCollision extends CollisionBounds
{
    
    private GameObject level;
    
    public SkeletonBoundsCollision (Background backgr, GameObject l)
    {
        super(backgr);
        level = l;
    }

    @Override
    public void collided (Sprite sprite)
    {
        Skeleton enemy = (Skeleton) sprite;
        
        if (level instanceof LevelOne)
        {
            if (isCollisionSide(CollisionBounds.TOP_COLLISION))
                enemy.moveY(Skeleton.GRAVITY);
            if (isCollisionSide(CollisionBounds.BOTTOM_COLLISION))
                enemy.dieByFalling();
            if (isCollisionSide(CollisionBounds.LEFT_COLLISION))
                enemy.runRight();
            if (isCollisionSide(CollisionBounds.RIGHT_COLLISION))
                enemy.runLeft();
        }
        
        if (level instanceof LevelTwo)
        {
            if (isCollisionSide(CollisionBounds.BOTTOM_COLLISION))
                enemy.dieByFalling();
        }
    }
    
}