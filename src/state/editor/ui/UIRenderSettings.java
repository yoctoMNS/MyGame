package state.editor.ui;

import core.Size;
import game.settings.RenderSettings;
import map.GameMap;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;
import ui.clickable.UIMinimap;

public class UIRenderSettings extends VerticalContainer {
    public UIRenderSettings(Size windowSize, RenderSettings renderSettings, GameMap gameMap) {
        super(windowSize);

        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
        setCenterChildren(true);

        addUIComponent(new UIMinimap(gameMap));
        addUIComponent(new UIText("RENDER SETTINGS"));
        addUIComponent(new UICheckbox("GRID", renderSettings.getShouldRenderGrid()));
    }
}
