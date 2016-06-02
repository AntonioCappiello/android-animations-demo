package com.antoniocappiello.androidanimations.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.antoniocappiello.androidanimations.controller.AnimationProvider;
import com.antoniocappiello.androidanimations.controller.AnimationSetBuilder;
import com.antoniocappiello.androidanimations.controller.OnRotateChangedListener;
import com.antoniocappiello.androidanimations.model.AnimationType;
import com.antoniocappiello.androidanimations.Config;
import com.antoniocappiello.androidanimations.controller.DialogBuilderHelper;
import com.antoniocappiello.androidanimations.model.InterpolatorType;
import com.antoniocappiello.androidanimations.controller.OnAlphaChangedListener;
import com.antoniocappiello.androidanimations.R;
import com.antoniocappiello.androidanimations.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleViewAnimationsActivity extends AppCompatActivity
        implements OnAlphaChangedListener, AnimationProvider, OnRotateChangedListener {

    private int mDuration;
    private int mStartOffset;

    @BindView(R.id.tv_to_animate)
    TextView tvToAnimate;

    @BindView(R.id.sb_duration)
    SeekBar sbDuration;

    @BindView(R.id.tv_duration)
    TextView tvDuration;

    @BindView(R.id.cb_alpha_animation)
    CheckBox cbAlphaAnimation;

    @BindView(R.id.cb_rotate_animation)
    CheckBox cbRotateAnimation;

    @BindView(R.id.cb_translate_animation)
    CheckBox cbTranslateAnimations;

    @BindView(R.id.cb_scale_animation)
    CheckBox cbScaleAnimation;

    @BindView(R.id.spinner_interpolator)
    Spinner spinnerInterpolator;

    @BindView(R.id.spinner_repeat_count)
    Spinner spinnerRepeatCount;

    @BindView(R.id.rb_restart)
    RadioButton rbRestart;

    @BindView(R.id.rb_reverse)
    RadioButton rbReverse;

    @BindView(R.id.sb_start_offset)
    SeekBar sbStartOffset;

    @BindView(R.id.tv_start_offset)
    TextView tvStartOffset;

    private float mFromAlpha;
    private float mToAlpha;
    private Map<AnimationType, CheckBox> checkBoxAnimationsList;
    private int mFromDegree;
    private int mToDegree;
    private int mRotatePivotX;
    private int mRotatePivotY;
    private int mFromScaleX;
    private int mToScaleX;
    private int mFromScaleY;
    private int mToScaleY;
    private int mScalePivotX;
    private int mScalePivotY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single_vie_animations);
        ButterKnife.bind(this);

        initInterpolatorSpinner();
        initRepeatCountSpinner();

        setDefaultValues();

        initSeekBarDuration();
        initSeekBarOffset();

        initCheckBookAnimationsList();
    }

    private void initCheckBookAnimationsList() {
        checkBoxAnimationsList = new HashMap<>();
        checkBoxAnimationsList.put(AnimationType.ALPHA, cbAlphaAnimation);
        checkBoxAnimationsList.put(AnimationType.ROTATE, cbRotateAnimation);
        checkBoxAnimationsList.put(AnimationType.SCALE, cbScaleAnimation);
        checkBoxAnimationsList.put(AnimationType.TRANSLATE, cbTranslateAnimations);
    }

    private void initSeekBarOffset() {
        sbStartOffset.setMax(Config.MAX_OFFSET);
        sbStartOffset.setOnSeekBarChangeListener(createStartOffsetChangeListener());

    }

    private void initSeekBarDuration() {
        sbDuration.setMax(Config.MAX_DURATION);
        sbDuration.setOnSeekBarChangeListener(createDurationChangeListener());
    }

    private SeekBar.OnSeekBarChangeListener createStartOffsetChangeListener() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mStartOffset = progress;
                tvStartOffset.setText(getString(R.string.start_offset_brackets, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
    }

    private void initRepeatCountSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"0", "1", "2", "3", Config.INFINITE});

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRepeatCount.setAdapter(adapter);
    }

    private void initInterpolatorSpinner() {
        HashMap<String, Interpolator> interpolatorHashMap = new HashMap<>();
        for (InterpolatorType interpolatorType : InterpolatorType.values()) {
            interpolatorHashMap.put(interpolatorType.name(), interpolatorType.getInterpolator());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                interpolatorHashMap.keySet().toArray(new String[interpolatorHashMap.keySet().size()]));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInterpolator.setAdapter(adapter);
    }

    private SeekBar.OnSeekBarChangeListener createDurationChangeListener() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDuration = progress;
                tvDuration.setText(getString(R.string.duration_brackets, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
    }

    private void setDefaultValues() {
        cbAlphaAnimation.setChecked(true);

        mDuration = Config.DEFAULT_DURATION;
        sbDuration.setProgress(Config.DEFAULT_DURATION);
        tvDuration.setText(getString(R.string.duration_brackets, Config.DEFAULT_DURATION));

        rbRestart.setChecked(true);

        mStartOffset = Config.DEFAULT_START_OFFSET;
        sbStartOffset.setProgress(Config.DEFAULT_START_OFFSET);
        tvStartOffset.setText(getString(R.string.start_offset_brackets, Config.DEFAULT_START_OFFSET));

        mFromAlpha = Config.DEFAULT_FROM_ALPHA;
        mToAlpha = Config.DEFAULT_TO_ALPHA;

        mFromDegree = Config.FROM_DEGREE;
        mToDegree = Config.MAX_DEGREE;
        mRotatePivotX = Config.DEFAULT_PIVOT_X;
        mRotatePivotY = Config.DEFAULT_PIVOT_Y;

        mFromScaleX = Config.DEFAULT_FROM_SCALE_X;
        mToScaleX = Config.DEFAULT_TO_SCALE_X;
        mFromScaleY = Config.DEFAULT_FROM_SCALE_Y;
        mToScaleY = Config.DEFAULT_TO_SCALE_Y;
        mScalePivotX = Config.DEFAULT_SCALE_PIVOT_X;
        mScalePivotY = Config.DEFAULT_SCALE_PIVOT_Y;
    }

    @OnClick(R.id.button_play)
    public void playAnimation() {
        AnimationSet animationSet = new AnimationSetBuilder.Builder()
                .animations(Utils.createAnimationMap(checkBoxAnimationsList, this))
                .duration(mDuration)
                .interpolator(InterpolatorType.valueOf(spinnerInterpolator.getSelectedItem().toString()).getInterpolator())
                .repeatCount(getRepeatCountValue())
                .repeatMode(getRepeatModeValue())
                .startOffset(mStartOffset)
                .build();
        tvToAnimate.startAnimation(animationSet);
    }

    private int getRepeatModeValue() {
        if (rbRestart.isChecked()) {
            return Animation.RESTART;
        } else {
            return Animation.REVERSE;
        }
    }

    public int getRepeatCountValue() {
        String selected = spinnerRepeatCount.getSelectedItem().toString();
        if (selected.equals(Config.INFINITE)) {
            return Animation.INFINITE;
        }
        return Integer.valueOf(selected);
    }

    @OnClick(R.id.iv_alpha_config)
    public void showAlphaConfigDialog() {
        DialogBuilderHelper.createAlphaAnimationDialog(
                SingleViewAnimationsActivity.this,
                this,
                mFromAlpha,
                mToAlpha)
                .show();
    }

    @OnClick(R.id.iv_rotate_config)
    public void showRotateConfigDialog() {
        DialogBuilderHelper.createRotateAnimationDialog(
                SingleViewAnimationsActivity.this,
                this,
                mFromDegree,
                mToDegree,
                mRotatePivotX,
                mRotatePivotY,
                tvToAnimate)
                .show();
    }

    @Override
    public void setFromAlpha(float fromAlpha) {
        mFromAlpha = fromAlpha;
    }

    @Override
    public void setToAlpha(float toAlpha) {
        mToAlpha = toAlpha;
    }

    @Override
    public Animation getAnimation(AnimationType animationType) {
        switch (animationType) {
            case ALPHA:
                return new AlphaAnimation(mFromAlpha, mToAlpha);
            case ROTATE:
                return new RotateAnimation(mFromDegree, mToDegree, mRotatePivotX, mRotatePivotY);
            case SCALE:
                if (mScalePivotX == Config.DEFAULT_SCALE_PIVOT_X) {
                    mScalePivotX = tvToAnimate.getWidth() / 2;
                }
                if (mScalePivotY == Config.DEFAULT_SCALE_PIVOT_Y) {
                    mScalePivotY = tvToAnimate.getHeight() / 2;
                }
                return new ScaleAnimation(
                        mFromScaleX,
                        mToScaleX,
                        mFromScaleY,
                        mToScaleY,
                        Animation.ABSOLUTE,
                        mScalePivotX,
                        Animation.ABSOLUTE,
                        mScalePivotY);
            case TRANSLATE:
                return new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF,
                        0,
                        Animation.RELATIVE_TO_SELF,
                        0,
                        Animation.ABSOLUTE,
                        ((ViewGroup) tvToAnimate.getParent()).getLayoutParams().height,
                        Animation.ABSOLUTE, tvToAnimate.getLayoutParams().height);
            default:
                return null;
        }
    }

    @Override
    public void setRotateConfig(int fromDegree, int toDegree, int pivotX, int pivotY) {
        mFromDegree = fromDegree;
        mToDegree = toDegree;
        mRotatePivotX = pivotX;
        mRotatePivotY = pivotY;
    }
}
