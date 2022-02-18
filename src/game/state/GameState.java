package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknessStatistics;
import input.Input;
import map.GameMap;
import map.ui.Alignment;
import map.ui.UIText;
import map.ui.VerticalContainer;

import java.util.List;

public class GameState extends State {
    private List<Condition> victoryConditions;
    private List<Condition> defeatConditions;
    private boolean playing;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        this.gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        this.playing = true;

        initializeCharacters();
        initializeUI(windowSize);
        initializeConditions();
    }

    private void initializeConditions() {
        victoryConditions = List.of(() -> getNumberOfSick() == 0);
        defeatConditions = List.of(() -> (float)getNumberOfSick() / getNumberOfNPCs() > 0.25);
    }

    private void initializeUI(Size windowSize) {
        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UISicknessStatistics(windowSize));
    }

    private void initializeCharacters() {
        SelectionCircle circle = new SelectionCircle();
        Player player = new Player(new PlayerController(input), spriteLibrary, circle);

        this.gameObjects.add(player);
        camera.focusOn(player);

        gameObjects.add(circle);

        initializeNPCs(200);
        makeNumberOfNPCsSick(0);
    }

    private void makeNumberOfNPCsSick(int number) {
        getGameObjectsOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Sick()));
    }

    private void initializeNPCs(int numberOfNPCs) {
        for (int i = 0; i < numberOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            gameObjects.add(npc);
        }
    }

    @Override
    public void update() {
        super.update();

        if (playing) {
            if (victoryConditions.stream().allMatch(Condition::isMet)) {
                win();
            }

            if (defeatConditions.stream().allMatch(Condition::isMet)) {
                lose();
            }
        }
    }

    private void win() {
        playing = false;

        VerticalContainer winContainer = new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        winContainer.addUIComponent(new UIText("VICTORY"));
        uiContainers.add(winContainer);
    }

    private void lose() {
        playing = false;

        VerticalContainer loseContainer = new VerticalContainer(camera.getSize());
        loseContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        loseContainer.addUIComponent(new UIText("DEFEAT"));
        uiContainers.add(loseContainer);
    }

    public long getNumberOfSick() {
         return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && !npc.isAffectedBy(Isolated.class))
                .count();
    }

    public long getNumberOfIsolated() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && npc.isAffectedBy(Isolated.class))
                .count();
    }

    public long getNumberOfHealthy() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> !npc.isAffectedBy(Sick.class))
                .count();
    }

    public long getNumberOfNPCs() {
        return getGameObjectsOfClass(NPC.class).size();
    }
}
