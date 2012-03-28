package platformer.collisions;

import platformer.sprites.Skeleton;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class SkeletonBlockCollision extends CollisionGroup
{
    {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        Skeleton enemy = (Skeleton) s1;
        int side = getCollisionSide();
        if (side != CollisionGroup.TOP_BOTTOM_COLLISION)
            enemy.setOnGround(true);
        if (side == CollisionGroup.RIGHT_LEFT_COLLISION)
            enemy.runLeft();
        else if (side == CollisionGroup.LEFT_RIGHT_COLLISION)
            enemy.moveX(Skeleton.RUN_SPEED);
        else if (side == CollisionGroup.TOP_BOTTOM_COLLISION)
            enemy.drop();

    }
    
}