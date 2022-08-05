package state.editor;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import map.Tile;
import state.State;
import input.mouse.action.TilePlacer;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;
import state.editor.ui.UITileMenu;
import ui.Alignment;
import ui.UITabContainer;

public class EditorState extends State {

    public EditorState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);

        gameMap = new GameMap(new Size(16, 16), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(true);
        mouseHandler.setPrimaryButtonAction(new TilePlacer(new Tile(spriteLibrary, "grass")));

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));

        UITabContainer toolsConteiner = new UITabContainer(windowSize);
        toolsConteiner.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));
        toolsConteiner.addTab("TILES", new UITileMenu(windowSize, spriteLibrary, gameSettings.getEditorSettings()));
        toolsConteiner.addTab("SCENERY", new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
        uiContainers.add(toolsConteiner);
    }
}
