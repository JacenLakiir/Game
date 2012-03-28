package platformer.collisions;

import platformer.sprites.Projectile;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class ProjectileBoundsCollision extends CollisionBounds
{
    
    public ProjectileBoundsCollision (Background backgr)
    {
        super(backgr);
    }

    @Override
    public void collided (Sprite sprite)
    {
        Projectile proj = (Projectile) sprite;
        proj.setActive(false);
    }
    
}