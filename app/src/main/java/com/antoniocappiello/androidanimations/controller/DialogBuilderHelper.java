package com.antoniocappiello.androidanimations.controller;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.antoniocappiello.androidanimations.Config;
import com.antoniocappiello.androidanimations.R;
import com.antoniocappiello.androidanimations.Utils;

public class DialogBuilderHelper {

    private DialogBuilderHelper() {
    }

    public static AlertDialog createAlphaAnimationDialog(final Activity activity,
                                                         final OnAlphaChangedListener onAlphaChangedListener,
                                                         float fromAlpha,
                                                         float toAlpha) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_alpha_animation, null);
        final SeekBar sbFromAlpha = (SeekBar) view.findViewById(R.id.sb_from_alpha);
        final SeekBar sbToAlpha = (SeekBar) view.findViewById(R.id.sb_to_alpha);
        final TextView tvFromAlpha = (TextView) view.findViewById(R.id.tv_from_alpha);
        final TextView tvToAlpha = (TextView) view.findViewById(R.id.tv_to_alpha);

        tvFromAlpha.setText(activity.getString(R.string.from_alpha_brackets, fromAlpha));
        tvToAlpha.setText(activity.getString(R.string.from_alpha_brackets, toAlpha));

        sbFromAlpha.setProgress(Utils.covertProgressToInt(fromAlpha, Config.MAX_ALPHA));
        sbToAlpha.setProgress(Utils.covertProgressToInt(toAlpha, Config.MAX_ALPHA));

        sbFromAlpha.setOnSeekBarChangeListener(Utils.createSeekBarChangeListener(activity, tvFromAlpha, R.string.from_alpha_brackets));

        sbToAlpha.setOnSeekBarChangeListener(Utils.createSeekBarChangeListener(activity, tvToAlpha, R.string.to_alpha_brackets));

        return new AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(R.string.configure_alpha_animation)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onAlphaChangedListener.setFromAlpha(Utils.covertProgressToFloat(sbFromAlpha.getProgress(), Config.MAX_ALPHA));
                        onAlphaChangedListener.setToAlpha(Utils.covertProgressToFloat(sbToAlpha.getProgress(), Config.MAX_ALPHA));
                    }
                })
                .create();
    }

    public static AlertDialog createRotateAnimationDialog(final Activity activity,
                                                          final OnRotateChangedListener onRotateChangedListener,
                                                          final int fromDegree,
                                                          final int toDegree,
                                                          final int pivotX,
                                                          final int pivotY,
                                                          final TextView tvToAnimate) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_rotate_animation, null);

        final SeekBar sbFromDegree = (SeekBar) view.findViewById(R.id.sb_from_degree);
        final SeekBar sbToDegree = (SeekBar) view.findViewById(R.id.sb_to_degree);
        final SeekBar sbPivotX = (SeekBar) view.findViewById(R.id.sb_pivot_x);
        final SeekBar sbPivotY = (SeekBar) view.findViewById(R.id.sb_pivot_y);

        final TextView tvFromDegree = (TextView) view.findViewById(R.id.tv_from_degree);
        final TextView tvToDegree = (TextView) view.findViewById(R.id.tv_to_degree);
        final TextView tvPivotX = (TextView) view.findViewById(R.id.tv_pivot_x);
        final TextView tvPivotY = (TextView) view.findViewById(R.id.tv_pivot_y);

        tvFromDegree.setText(activity.getString(R.string.from_degree_brackets, fromDegree));
        tvToDegree.setText(activity.getString(R.string.to_degree_brackets, toDegree));
        tvPivotX.setText(activity.getString(R.string.pivot_x_brackets, pivotX));
        tvPivotY.setText(activity.getString(R.string.pivot_y_brackets, pivotY));

        sbFromDegree.setMax(Config.MAX_DEGREE);
        sbFromDegree.setProgress(fromDegree);

        sbToDegree.setMax(Config.MAX_DEGREE);
        sbToDegree.setProgress(toDegree);

        sbPivotX.setMax(tvToAnimate.getWidth());
        sbPivotX.setProgress(pivotX);

        sbPivotY.setMax(tvToAnimate.getHeight());
        sbPivotY.setProgress(pivotY);

        sbFromDegree.setOnSeekBarChangeListener(Utils.createSeekBarChangeListener(activity, tvFromDegree, R.string.from_degree_brackets));
        sbToDegree.setOnSeekBarChangeListener(Utils.createSeekBarChangeListener(activity, tvToDegree, R.string.to_degree_brackets));
        sbPivotX.setOnSeekBarChangeListener(Utils.createSeekBarChangeListener(activity, tvPivotX, R.string.pivot_x_brackets));
        sbPivotY.setOnSeekBarChangeListener(Utils.createSeekBarChangeListener(activity, tvPivotY, R.string.pivot_y_brackets));

        return new AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(R.string.configure_rotate_animation)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onRotateChangedListener.setRotateConfig(
                                sbFromDegree.getProgress(),
                                sbToDegree.getProgress(),
                                sbPivotX.getProgress(),
                                sbPivotY.getProgress()
                        );
                    }
                })
                .create();

    }
}
