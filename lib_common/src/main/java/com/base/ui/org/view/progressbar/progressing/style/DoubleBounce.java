package com.base.ui.org.view.progressbar.progressing.style;

import android.animation.ValueAnimator;

import com.base.ui.org.view.progressbar.progressing.animation.SpriteAnimatorBuilder;
import com.base.ui.org.view.progressbar.progressing.sprite.CircleSprite;
import com.base.ui.org.view.progressbar.progressing.sprite.Sprite;
import com.base.ui.org.view.progressbar.progressing.sprite.SpriteContainer;

/**
 * Created by ybq.
 */
public class DoubleBounce extends SpriteContainer {

    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new Bounce(), new Bounce()
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        super.onChildCreated(sprites);
        sprites[1].setAnimationDelay(-1000);
    }

    private class Bounce extends CircleSprite {

        Bounce() {
            setAlpha(153);
            setScale(0f);
        }

        @Override
        public ValueAnimator onCreateAnimation() {
            float fractions[] = new float[]{0f, 0.5f, 1f};
            return new SpriteAnimatorBuilder(this).scale(fractions, 0f, 1f, 0f).
                    duration(2000).
                    easeInOut(fractions)
                    .build();
        }
    }
}
