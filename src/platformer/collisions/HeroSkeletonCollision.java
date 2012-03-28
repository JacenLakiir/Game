package platformer.collisions;

import platformer.sprites.Hero;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class HeroSkeletonCollision extends BasicCollisionGroup
{
    
    {
        pixelPerfectCollision = true;
    }

    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        Hero player = (Hero) s1;
        player.setIsDying(true);
    }

}
