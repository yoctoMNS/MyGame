package ui.clickable;

import core.Size;
import game.settings.Setting;
import gfx.ImageUtils;
import state.State;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIComponent;
import ui.UIContainer;
import ui.UIText;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class UICheckbox extends UIComponent {
    private UIContainer container;

    public UICheckbox(String label, Setting<Boolean> setting) {
        this.container = new HorizontalContainer(new Size(0, 0));
        container.addUIComponent(new Checkbox(setting));
        container.addUIComponent(new UIText(label));
    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }

    @Override
    public void update(State state) {
        container.update(state);
        size = container.getSize();
        container.setParent(parent);
        container.setAbsolutePosition(absolutePosition);
    }

    private static class Checkbox extends UIClickable {
        private Setting<Boolean> setting;
        private Color color;

        private Checkbox(Setting<Boolean> setting) {
            this.setting = setting;
            size = new Size(20, 20);
            this.color = Color.GRAY;
            margin = new Spacing(0, 5, 0, 0);
        }

        @Override
        public void update(State state) {
            super.update(state);

            this.color = setting.getValue() ? Color.WHITE : Color.GRAY;

            if (hasFocus) {
                this.color = Color.LIGHT_GRAY;

                if (isPressed) {
                    this.color = Color.DARK_GRAY;
                }
            }
        }

        @Override
        public Image getSprite() {
            BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
            Graphics2D graphics = sprite.createGraphics();

            graphics.setColor(color);
            if (setting.getValue()) {
                graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
            } else {
                graphics.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);
            }

            graphics.dispose();
            return sprite;
        }

        @Override
        protected void onFocus(State state) {

        }

        @Override
        public void onDrag(State state) {

        }

        @Override
        public void onClick(State state) {
            if (hasFocus) {
                setting.setValue(!setting.getValue());
            }
        }
    }
}
