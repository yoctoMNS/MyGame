package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.effect.Sick;
import input.Input;
import map.GameMap;
import map.ui.Alignment;
import map.ui.Spacing;
import map.ui.UIContainer;
import map.ui.UIText;
import map.ui.VerticalContainer;

import java.awt.Color;

public class GameState extends State {
    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        this.gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        initializeCharacters();
        initializeUI(windowSize);
    }

    private void initializeUI(Size windowSize) {
        UIContainer container = new VerticalContainer(windowSize);
        container.setPadding(new Spacing(5));
        container.getBackgroundColor(new Color(0, 0, 0, 0));

        UIContainer containerEnd = new VerticalContainer(windowSize);
        containerEnd.setPadding(new Spacing(5));
        containerEnd.getBackgroundColor(new Color(0, 0, 0, 0));
        containerEnd.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));

        containerEnd.addUIComponent(new UIText("Good bye!"));

        uiContainers.add(container);
        uiContainers.add(containerEnd);
    }

    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);

        this.gameObjects.add(player);
        camera.focusOn(player);

        initializeNPCs(200);
    }

    private void initializeNPCs(int numberOfNPCs) {
        for (int i = 0; i < numberOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            npc.addEffect(new Sick());
            gameObjects.add(npc);
        }
    }
}
