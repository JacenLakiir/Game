package platformer.collisions;

import platformer.sprites.Hero;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class HeroBlockCollision extends CollisionGroup
{
    {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        Hero player = (Hero) s1;
        int side = getCollisionSide();
        if (side == CollisionGroup.BOTTOM_TOP_COLLISION)
            player.setOnGround(true);
        else if (side == CollisionGroup.TOP_BOTTOM_COLLISION)
            player.drop();
        else
            player.forceX(player.getOldX());
    }
    
}