package platformer.collisions;

import platformer.sprites.Skeleton;
import platformer.sprites.Projectile;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ProjectileSkeletonCollision extends BasicCollisionGroup
{
    
    {
        pixelPerfectCollision = true;
    }
    
    @Override
    public void collided (Sprite s1, Sprite s2)
    {
        Projectile proj = (Projectile) s1;
        Skeleton skeleton = (Skeleton) s2;
        
        proj.setIsDetonating(true);
        skeleton.setIsDying(true);
    }
    
}
