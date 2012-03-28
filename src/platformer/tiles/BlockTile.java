package platformer.tiles;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.AnimatedSprite;

@SuppressWarnings("serial")
public class BlockTile extends AnimatedSprite
{
    private static final String TILES = "resources/tile_spritesheet.png";
    private static final int COLS = 22;
    private static final int ROWS = 12;
        
    public BlockTile (int x, int y, GameEngine game)
    {
        super(game.getImages(TILES, COLS, ROWS), x, y);
        setAnimationFrame(31, 31);
        setAnimate(false);
        setLoopAnim(false);
    }
    
}