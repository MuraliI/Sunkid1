package com.rcl.excalibur.custom.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
        this.setOnClickListener(view -> {
            isDeckSelectorOpen = !isDeckSelectorOpen;
            openSelector.setImageDrawable(getResources()
                    .getDrawable(isDeckSelectorOpen
                                    ? R.drawable.ic_arrow_drop_up
                                    : R.drawable.ic_arrow_drop_down
                            , getContext().getTheme()));
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

    public void setButtonObserver(Consumer<Boolean> observer) {
        deckSelectorButtonPressedPublisher.subscribe(observer);
    }
}
