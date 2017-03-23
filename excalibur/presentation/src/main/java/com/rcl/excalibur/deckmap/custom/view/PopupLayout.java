package com.medellin.team.moveimagetest.custom.view;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.medellin.team.moveimagetest.R;
import com.medellin.team.moveimagetest.model.DeckItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopupLayout extends RelativeLayout {
    @BindView(R.id.image_event) ImageView eventImage;
    @BindView(R.id.text_title) TextView titleText;
    @BindView(R.id.text_type) TextView typeText;
    @BindView(R.id.text_price_range) TextView priceRangeText;

    public PopupLayout(Context context) {
        super(context);
        initialize(context);
    }

    public PopupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public PopupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    public PopupLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_deck, this);
        ButterKnife.bind(this, this);
    }

    public void setActivity(DeckItem deckItem) {
        titleText.setText(deckItem.getTitle());
        typeText.setText(deckItem.getType());
        priceRangeText.setText("$$, " + deckItem.getCategory());

        final Context context = getContext();
        if (context == null || TextUtils.isEmpty(deckItem.getImageUrl())) {
            return;
        }
        Picasso.with(context).load(deckItem.getImageUrl()).into(eventImage);
    }
}
