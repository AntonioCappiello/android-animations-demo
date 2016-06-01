package com.antoniocappiello.androidanimations.controller;

import android.view.animation.Animation;

import com.antoniocappiello.androidanimations.model.AnimationType;

public interface AnimationProvider {
    Animation getAnimation(AnimationType animationType);
}
