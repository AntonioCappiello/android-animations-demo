package com.antoniocappiello.androidanimations.controller;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;

import com.antoniocappiello.androidanimations.model.AnimationType;

import java.util.Map;

public class AnimationSetBuilder {

    public static class Builder {

        private Map<AnimationType, Animation> mAnimations;
        private int mDuration;
        private Interpolator mInterpolator;
        private int mRepeatCount;
        private int mRepeatMode;
        private int mStartOffset;

        public Builder animations(Map<AnimationType, Animation> animations) {
            mAnimations = animations;
            return this;
        }

        public Builder duration(int duration) {
            mDuration = duration;
            return this;
        }

        public AnimationSet build() {
            AnimationSet animationSet = createAnimationSet(mAnimations, mRepeatCount, mRepeatMode);
            animationSet.setDuration(mDuration);
            animationSet.setInterpolator(mInterpolator);
            animationSet.setStartOffset(mStartOffset);
            return animationSet;
        }

        public Builder interpolator(Interpolator interpolator) {
            mInterpolator = interpolator;
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            mRepeatCount = repeatCount;
            return this;
        }

        public Builder repeatMode(int repeatMode) {
            mRepeatMode = repeatMode;
            return this;
        }

        public Builder startOffset(int startOffset) {
            mStartOffset = startOffset;
            return this;
        }
    }

    private static AnimationSet createAnimationSet(
            Map<AnimationType, Animation> animations,
            int repeatCount,
            int repeatMode) {

        AnimationSet animationSet = new AnimationSet(true);

        for (Animation animation : animations.values()) {
            animation.setRepeatCount(repeatCount);
            animation.setRepeatMode(repeatMode);
            animationSet.addAnimation(animation);
        }

        return animationSet;

    }
}
