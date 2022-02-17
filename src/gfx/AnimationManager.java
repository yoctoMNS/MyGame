package gfx;

import core.Direction;
import game.Game;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private SpriteSet spriteSet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private int currentFrameTime;
    private int updatesPerFrame;
    private int frameIndex;
    private int directionIndex;
    private boolean looping;

    public AnimationManager(SpriteSet spriteSet) {
        this(spriteSet, true);
    }

    public AnimationManager(SpriteSet spriteSet, boolean looping) {
        this.spriteSet = spriteSet;
        this.currentFrameTime = 0;
        this.updatesPerFrame = 20;
        this.frameIndex = 0;
        this.directionIndex = 0;
        this.currentAnimationName = "";
        this.looping = looping;
        playAnimation("stand");
    }

    public Image getSprite() {
        return currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public void update(Direction direction) {
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();

        if (currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            int animationSize = currentAnimationSheet.getWidth() / Game.SPRITE_SIZE;
            if (frameIndex >= animationSize) {
                frameIndex = looping ? 0 : animationSize - 1;
            }
        }
    }

    public void playAnimation(String name) {
        if (!name.equals(currentAnimationName)) {
            currentAnimationSheet = (BufferedImage)spriteSet.getOrGetDefault(name);
            currentAnimationName = name;
            frameIndex = 0;
        }
    }
}
