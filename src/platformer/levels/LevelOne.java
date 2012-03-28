package platformer.levels;

import java.awt.Graphics2D;
import java.util.Set;
import platformer.collisions.HeroBlockCollision;
import platformer.collisions.HeroBoundsCollision;
import platformer.collisions.HeroSkeletonCollision;
import platformer.collisions.ProjectileBoundsCollision;
import platformer.collisions.ProjectileSkeletonCollision;
import platformer.collisions.SkeletonBlockCollision;
import platformer.collisions.SkeletonBoundsCollision;
import platformer.sprites.Skeleton;
import platformer.sprites.Hero;
import platformer.sprites.Projectile;
import platformer.tiles.BlockTile;
import platformer.tiles.FloorTile;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class LevelOne extends Level
{
    
    private static final String BACKGROUND_IMAGE = "resources/background.jpg";
    private static final String MUSIC = "resources/Flying_Battery_Zone_Act_1.mid";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 640;
    private static final int TILE_SIZE = 32;

    private PlayField myPlayfield;
    private Background myBackground;

    private Hero hero;
    private SpriteGroup projectiles;
    
    public LevelOne (GameEngine parent)
    {
        super(parent);
    }
    
    @Override
    public void initResources ()
    {
        hideCursor();
        setFPS(50);
        bsGraphics.setWindowTitle("Level One");
        bsMusic.play(MUSIC);
        
        myBackground = buildBackground();
        myPlayfield = new PlayField(myBackground);
        isPaused = false;
        isMusicOn = true;
        hasWon = false;
        
        SpriteGroup floor = createFloor();
        SpriteGroup blocks = createBlocks();
        SpriteGroup player = createPlayer();
        SpriteGroup enemies = createEnemies();
        projectiles = new SpriteGroup("Projectiles");
        
        myPlayfield.addGroup(floor);
        myPlayfield.addGroup(blocks);
        myPlayfield.addGroup(player);
        myPlayfield.addGroup(enemies);
        myPlayfield.addGroup(projectiles);
        
        myPlayfield.addCollisionGroup(player, null, new HeroBoundsCollision(myBackground, this));
        myPlayfield.addCollisionGroup(player, floor, new HeroBlockCollision());
        myPlayfield.addCollisionGroup(player, blocks, new HeroBlockCollision());
        myPlayfield.addCollisionGroup(player, enemies, new HeroSkeletonCollision());
        
        myPlayfield.addCollisionGroup(enemies, null, new SkeletonBoundsCollision(myBackground, this));
        myPlayfield.addCollisionGroup(enemies, floor, new SkeletonBlockCollision());
        myPlayfield.addCollisionGroup(enemies, blocks, new SkeletonBlockCollision());
        
        myPlayfield.addCollisionGroup(projectiles, null, new ProjectileBoundsCollision(myBackground));
        myPlayfield.addCollisionGroup(projectiles, enemies, new ProjectileSkeletonCollision());
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        if (letterRKey())   jumpToLevelOne();
        if (isPaused)       return;
        
        myPlayfield.update(elapsedTime);
        myBackground.setToCenter(hero);
        updateProjectiles();
        
        if (hero.hasDied())
        {
            parent.nextGameID = 6;
            finish();
        }
        
        if (hasWon)
        {
            parent.nextGameID = 4;
            finish();
        }
    }

    @Override
    public void render (Graphics2D g)
    {
        myPlayfield.render(g);
    }
    
    @Override
    protected String getMusic ()
    {
        return MUSIC;
    }
    
    private Background buildBackground ()
    {
        Background background = new ImageBackground(parent.getImage(BACKGROUND_IMAGE));
        background.setSize(WIDTH, HEIGHT);
        return background;
    }
    
    private SpriteGroup createFloor ()
    {
        SpriteGroup floor = new SpriteGroup("Floor");
        for (int y = 576; y <= 608; y += TILE_SIZE)
        {
            for (int x = 0; x < 320; x += TILE_SIZE)
                floor.add(new FloorTile(x, y, parent));
            for (int x = 384; x < 832; x += TILE_SIZE)
                floor.add(new FloorTile(x, y, parent));
            for (int x = 992; x < WIDTH; x += TILE_SIZE)
                floor.add(new FloorTile(x, y, parent));
        }
        return floor;
    }
    
    private SpriteGroup createBlocks ()
    {
        SpriteGroup blocks = new SpriteGroup("Blocks");
        
        // free-floating blocks
        blocks.add(new BlockTile(192, 480, parent));
        blocks.add(new BlockTile(224, 480, parent));
        blocks.add(new BlockTile(352, 416, parent));
        
        for (int x = 480; x < 544; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 416, parent));
        
        for (int x = 672; x < 736; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 320, parent));
        
        for (int x = 832; x < 960; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 352, parent));
        
        // large half-pyramid
        for (int x = 480; x < 576; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 544, parent));
        for (int x = 512; x < 576; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 512, parent));
        blocks.add(new BlockTile(544, 480, parent));
        

        // small half-pyramid
        for (int x = 1024; x < 1088; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 544, parent));
        blocks.add(new BlockTile(1024, 512, parent));
        
        // wall at end
        for (int y = 544; y > 320; y -= TILE_SIZE)
            blocks.add(new BlockTile(1184, y, parent));
        
        return blocks;
    }
    
    private SpriteGroup createPlayer ()
    {
        SpriteGroup player = new SpriteGroup("Player");
        hero = new Hero(50, 512, parent);
        player.add(hero);
        return player;
    }
    
    private SpriteGroup createEnemies ()
    {
        SpriteGroup enemies = new SpriteGroup("Enemies");
//        enemies.add(new Skeleton(768, 518, parent));
//        enemies.add(new Skeleton(288, 518, parent));
        enemies.add(new Skeleton(768, 500, parent));
        enemies.add(new Skeleton(288, 500, parent));
        return enemies;
    }
    
    private void updateProjectiles ()
    {
        Set<Projectile> currentProjectiles = hero.getProjectileSet();
        for (Projectile p : currentProjectiles)
            projectiles.add(p);
        projectiles.removeInactiveSprites();
    }
    
}
