package platformer;

import platformer.levels.LevelOne;
import platformer.levels.LevelTwo;
import platformer.screens.DeathScreen;
import platformer.screens.InstructionScreen;
import platformer.screens.LevelOneScreen;
import platformer.screens.LevelTwoScreen;
import platformer.screens.StartScreen;
import platformer.screens.VictoryScreen;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;


public class PlatformerGame extends GameEngine
{

    @Override
    public GameObject getGame (int GameID)
    {        
        switch (GameID)
        {
            case 0: return new StartScreen(this);
            case 1: return new InstructionScreen(this);
            case 2: return new LevelOneScreen(this);
            case 3: return new LevelOne(this);
            case 4: return new LevelTwoScreen(this);
            case 5: return new LevelTwo(this);
            case 6: return new DeathScreen(this);
            case 7: return new VictoryScreen(this);
        }
        return null;
    }
    
}
