package map.ui;

import core.Position;
import core.Size;

public class HorizontalContainer extends UIContainer {
    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for (UIComponent uiComponent : children) {
            combinedChildWidth += uiComponent.getSize().w + uiComponent.getMargin().getHorizontal();

            if (uiComponent.getSize().h > tallestChildHeight) {
                tallestChildHeight = uiComponent.getSize().h;
            }
        }

        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentX = padding.getLeft();

        for (UIComponent uiComponent : children) {
            currentX += uiComponent.getMargin().getLeft();
            uiComponent.setPosition(new Position(currentX, padding.getTop()));
            currentX += uiComponent.getSize().w;
            currentX += uiComponent.getMargin().getRight();
        }
    }
}
