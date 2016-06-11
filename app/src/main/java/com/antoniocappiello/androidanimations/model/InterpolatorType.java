package com.antoniocappiello.androidanimations.model;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public enum InterpolatorType {
    ACCELERATE_DECELERATE_INTERPOLATOR(new AccelerateDecelerateInterpolator()),
    ACCELERATE_INTERPOLATOR(new AccelerateInterpolator()),
    ANTICIPATE_INTERPOLATOR(new AnticipateInterpolator()),
    ANTICIPATE_OVERSHOOT_INTERPOLATOR(new AnticipateOvershootInterpolator()),
    BOUNCE_INTERPOLATOR(new BounceInterpolator()),
    CYCLE_INTERPOLATOR(new CycleInterpolator((float) 0.5)),
    DECELERATE_INTERPOLATOR(new DecelerateInterpolator()),
    FAST_OUT_LINEAR_IN_INTERPOLATOR(new FastOutLinearInInterpolator()),
    FAST_OUT_SLOW_IN_INTERPOLATOR(new FastOutSlowInInterpolator()),
    LINEAR_INTERPOLATOR(new LinearInterpolator()),
    LINEAR_OUT_SLOW_IN_INTERPOLATOR(new LinearOutSlowInInterpolator()),
    OVERSHOOT_INTERPOLATOR(new OvershootInterpolator());

    private final Interpolator mInterpolator;

    InterpolatorType(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public Interpolator getInterpolator() {
        return mInterpolator;
    }
}
