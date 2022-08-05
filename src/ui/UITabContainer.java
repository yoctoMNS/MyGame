package ui;

import core.Size;
import state.State;
import ui.clickable.UIClickable;

import java.awt.Image;

public class UITabContainer extends VerticalContainer {
    private UIContainer tabContainer;
    private UIContainer contentContainer;
    private UITab activeTab;

    public UITabContainer(Size windowSize) {
        super(windowSize);

        tabContainer = new HorizontalContainer(windowSize);
        contentContainer = new VerticalContainer(windowSize);
    }

    private void activeTab(UITab uiTab) {
        activeTab = uiTab;
        contentContainer.clear();
        contentContainer.addUIComponent(uiTab.getContents());
    }

    public void addTab(String label, UIContainer contents) {
        UITab tab = new UITab(this, label, contents);
        tabContainer.addUIComponent(tab);

        if (activeTab == null) {
            activeTab(tab);
        }
    }

    static class UITab extends UIClickable {
        private UITabContainer parent;
        private UIContainer label;
        private UIContainer contents;

        public UITab(UITabContainer parent, String label, UIContainer contents) {
            this.parent = parent;
            this.contents = contents;
            this.label = new VerticalContainer(new Size(0, 0));
            this.label.addUIComponent(new UIText(label));
        }

        @Override
        public void update(State state) {
            super.update(state);
            label.update(state);
            size = label.getSize();
        }

        @Override
        public void onClick(State state) {
            parent.activeTab(this);
        }

        @Override
        public void onDrag(State state) {
        }

        @Override
        public Image getSprite() {
            return label.getSprite();
        }

        @Override
        protected void onFocus(State state) {
        }

        public UIContainer getContents() {
            return contents;
        }
    }
}
