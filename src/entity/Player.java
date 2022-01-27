package entity;

import controller.Controller;
import entity.effect.Caffeinated;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {
    public Player(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);

        effects.add(new Caffeinated());
    }
}
