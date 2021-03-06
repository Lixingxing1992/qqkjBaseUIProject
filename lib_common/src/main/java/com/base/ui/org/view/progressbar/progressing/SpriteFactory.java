package com.base.ui.org.view.progressbar.progressing;

import com.base.ui.org.view.progressbar.progressing.sprite.Sprite;
import com.base.ui.org.view.progressbar.progressing.style.ChasingDots;
import com.base.ui.org.view.progressbar.progressing.style.Circle;
import com.base.ui.org.view.progressbar.progressing.style.CubeGrid;
import com.base.ui.org.view.progressbar.progressing.style.DoubleBounce;
import com.base.ui.org.view.progressbar.progressing.style.FadingCircle;
import com.base.ui.org.view.progressbar.progressing.style.FoldingCube;
import com.base.ui.org.view.progressbar.progressing.style.MultiplePulse;
import com.base.ui.org.view.progressbar.progressing.style.MultiplePulseRing;
import com.base.ui.org.view.progressbar.progressing.style.Pulse;
import com.base.ui.org.view.progressbar.progressing.style.PulseRing;
import com.base.ui.org.view.progressbar.progressing.style.RotatingCircle;
import com.base.ui.org.view.progressbar.progressing.style.RotatingPlane;
import com.base.ui.org.view.progressbar.progressing.style.ThreeBounce;
import com.base.ui.org.view.progressbar.progressing.style.WanderingCubes;
import com.base.ui.org.view.progressbar.progressing.style.Wave;

/**
 * Created by ybq.
 */
public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}
