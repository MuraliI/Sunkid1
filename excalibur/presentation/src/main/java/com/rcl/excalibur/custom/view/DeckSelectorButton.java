package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rcl.excalibur.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;


public class DeckSelectorButton extends LinearLayout {

    @BindView(R.id.text_deck_number) TextView deckNumber;
    @BindView(R.id.image_dropdown) ImageView openSelector;

    private PublishSubject<Boolean> deckSelectorButtonPressedPublisher = PublishSubject.create();
    private boolean isDeckSelectorOpen;

    public DeckSelectorButton(Context context) {
        super(context);
        init();
    }

    public DeckSelectorButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeckSelectorButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Animation openAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_180_degrees_counter_clockwise);
        final Animation closeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_180_degrees_clockwise);
        this.setOnClickListener(view -> {
            openSelector.startAnimation(isDeckSelectorOpen ? closeAnimation : openAnimation);
            isDeckSelectorOpen = !isDeckSelectorOpen;
            deckSelectorButtonPressedPublisher.onNext(isDeckSelectorOpen);
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setText(String text) {
        deckNumber.setText(text);
    }

    public String getText() {
        return deckNumber.getText().toString();
    }

    public void setButtonObserver(Consumer<Boolean> observer) {
        deckSelectorButtonPressedPublisher.subscribe(observer);
    }
}
