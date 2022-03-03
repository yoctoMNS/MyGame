package state.menu.ui;

import core.Size;
import state.editor.EditorState;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.Color;

public class UIMainMenu extends VerticalContainer {
    public UIMainMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
        setBackgroundColor(Color.DARK_GRAY);

        addUIComponent(new UIText("MY TEST 2D GAME"));
        addUIComponent(new UIButton("PLAY", (state) -> state.setNextState(new GameState(windowSize, state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("OPTIONS", (state) -> ((MenuState)state).enterMenu(new UIOptionMenu(windowSize, state.getGameSettings()))));
        addUIComponent(new UIButton("EDITOR", (state) -> state.setNextState(new EditorState(windowSize, state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("EXIT", (state) -> System.exit(0)));
    }
}
