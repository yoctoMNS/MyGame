package map.ui;

import core.Position;
import core.Size;

public class VerticalContainer extends UIContainer {
    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for (UIComponent uiComponent : children) {
            combinedChildHeight += uiComponent.getSize().h + uiComponent.getMargin().getVertical();

            if (uiComponent.getSize().w > widestChildWidth) {
                widestChildWidth = uiComponent.getSize().w;
            }
        }

        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentY = padding.getTop();

        for (UIComponent uiComponent : children) {
            currentY += uiComponent.getMargin().getTop();
            uiComponent.setPosition(new Position(padding.getLeft(), currentY));
            currentY += uiComponent.getSize().h;
            currentY += uiComponent.getMargin().getBottom();
        }
    }
}
