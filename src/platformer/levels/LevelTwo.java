package platformer.levels;

import java.awt.Graphics2D;
import java.util.Set;
import platformer.collisions.HeroBlockCollision;
import platformer.collisions.HeroBoundsCollision;
import platformer.collisions.HeroSkeletonCollision;
import platformer.collisions.ProjectileBoundsCollision;
import platformer.collisions.ProjectileSkeletonCollision;
import platformer.collisions.SkeletonBoundsCollision;
import platformer.sprites.Skeleton;
import platformer.sprites.Hero;
import platformer.sprites.Projectile;
import platformer.tiles.BlockTile;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

public class LevelTwo extends Level
{
    
    private static final String BACKGROUND_IMAGE = "resources/background.jpg";
    private static final String MUSIC = "resources/Sky_Sanctuary_Zone.mid";
    private static final int WIDTH = 720;
    private static final int HEIGHT = 1024;
    private static final int TILE_SIZE = 32;

    private PlayField myPlayfield;
    private Background myBackground;

    private Hero hero;
    private SpriteGroup projectiles;
    private SpriteGroup enemies;
    
    public LevelTwo (GameEngine parent)
    {
        super(parent);
    }
    
    @Override
    public void initResources ()
    {
        hideCursor();
        setFPS(50);
        bsGraphics.setWindowTitle("Level Two");
        bsMusic.play(MUSIC);

        myBackground = buildBackground();
        myPlayfield = new PlayField(myBackground);
        isPaused = false;
        isMusicOn = true;
        hasWon = false;
        
        SpriteGroup blocks = createBlocks();
        SpriteGroup player = createPlayer();
        enemies = new SpriteGroup("Enemies");
        projectiles = new SpriteGroup("Projectiles");
        
        myPlayfield.addGroup(blocks);
        myPlayfield.addGroup(player);
        myPlayfield.addGroup(enemies);
        myPlayfield.addGroup(projectiles);
        
        myPlayfield.addCollisionGroup(player, null, new HeroBoundsCollision(myBackground, this));
        myPlayfield.addCollisionGroup(player, blocks, new HeroBlockCollision());
        myPlayfield.addCollisionGroup(player, enemies, new HeroSkeletonCollision());
        
        myPlayfield.addCollisionGroup(enemies, null, new SkeletonBoundsCollision(myBackground, this));
        
        myPlayfield.addCollisionGroup(projectiles, null, new ProjectileBoundsCollision(myBackground));
        myPlayfield.addCollisionGroup(projectiles, enemies, new ProjectileSkeletonCollision());
    }

    @Override
    public void update (long elapsedTime)
    {
        checkForUserInput();
        if (letterRKey())   jumpToLevelTwo();
        if (isPaused)       return;
        
        myPlayfield.update(elapsedTime);
        myBackground.setToCenter(hero);
        updateProjectiles();
        generateRandomEnemies();
        
        if (hero.hasDied())
        {
            parent.nextGameID = 4;
            finish();
        }
        
        if (hasWon)
        {
            parent.nextGameID = 7;
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
    
    private SpriteGroup createBlocks ()
    {
        SpriteGroup blocks = new SpriteGroup("Blocks");
        
        for (int x = 0; x < 160; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 928, parent));
        
        for (int x = 320; x < 448; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 864, parent));
        
        for (int x = 576; x < 720; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 800, parent));
        
        for (int x = 160; x < 224; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 736, parent));
        
        for (int x = 352; x < 448; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 672, parent));
        
        blocks.add(new BlockTile(32, 640, parent));
        
        for (int x = 224; x < 320; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 576, parent));
        
        for (int x = 608; x < 672; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 544, parent));
        
        blocks.add(new BlockTile(384, 480, parent));
        
        for (int x = 32; x < 96; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 480, parent));
        
        for (int x = 480; x < 544; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 416, parent));
        
        for (int x = 672; x < 736; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 384, parent));
        
        for (int x = 256; x < 352; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 320, parent));
       
        blocks.add(new BlockTile(608, 256, parent));
        
        for (int x = 96; x < 192; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 192, parent));
        
        for (int x = 416; x < 480; x += TILE_SIZE)
            blocks.add(new BlockTile(x, 224, parent));
        
        blocks.add(new BlockTile(544, 96, parent));
        
        return blocks;
    }
    
    private SpriteGroup createPlayer ()
    {
        SpriteGroup player = new SpriteGroup("Player");
        hero = new Hero(18, 864, parent);
        player.add(hero);
        return player;
    }
    
    private void updateProjectiles ()
    {
        Set<Projectile> currentProjectiles = hero.getProjectileSet();
        for (Projectile p : currentProjectiles)
            projectiles.add(p);
        projectiles.removeInactiveSprites();
    }
    
    private void generateRandomEnemies ()
    {
        if (getRandom(0, 100000) > 99000)
            enemies.add(new Skeleton(getRandom(0, 720), myBackground.getY(), parent));
    }
    
}