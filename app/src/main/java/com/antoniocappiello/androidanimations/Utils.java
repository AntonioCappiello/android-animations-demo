package com.antoniocappiello.androidanimations;

import android.app.Activity;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.antoniocappiello.androidanimations.controller.AnimationProvider;
import com.antoniocappiello.androidanimations.model.AnimationType;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    private Utils() {
    }

    public static Map<AnimationType, Animation> createAnimationMap(Map<AnimationType, CheckBox> checkBoxAnimationsList, AnimationProvider animationProvider) {

        Map<AnimationType, Animation> animations = new HashMap<>();

        for (AnimationType animationType : checkBoxAnimationsList.keySet()) {
            if (checkBoxAnimationsList.get(animationType).isChecked()) {
                animations.put(animationType, animationProvider.getAnimation(animationType));
            } else {
                if (animations.containsKey(animationType)) {
                    animations.remove(animationType);
                }
            }
        }

        return animations;
    }

    public static SeekBar.OnSeekBarChangeListener createSeekBarChangeListener(final Activity activity, final TextView tvToUpdate, final int stringResId) {
        return createSeekBarChangeListener(activity, tvToUpdate, stringResId, -1);
    }

    public static SeekBar.OnSeekBarChangeListener createSeekBarChangeListener(final Activity activity, final TextView tvToUpdate, final int stringResId, final int maxProgress) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvToUpdate.setText(activity.getString(stringResId, String.valueOf(maxProgress > 0 ? covertProgressToFloat(progress, maxProgress) : progress)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    public static int covertProgressToInt(float fromAlpha, int maxProgress) {
        return (int) (fromAlpha * maxProgress);
    }

    public static float covertProgressToFloat(int progress, int maxProgress) {
        if (progress == 0) {
            return 0;
        } else {
            return (float) progress / maxProgress;
        }
    }
}
