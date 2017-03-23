package com.rcl.excalibur.deckmap.custom.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PopupLayout extends RelativeLayout {
    // TODO: PENDING REFACTOR TO MVP

    @Bind(R.id.image_product) ImageView productImage;
    @Bind(R.id.text_title_product) TextView titleProductText;
    @Bind(R.id.text_type_product) TextView typeProductText;
    @Bind(R.id.text_price_range) TextView priceRangeText;

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

    public void setProduct(Product product) {
        titleProductText.setText(product.getProductTitle());
        typeProductText.setText(product.getProductType().getProductType());
        priceRangeText.setText(product.getProductType().getProductTypeName());

        List<MediaItem> mediaItems = product.getProductMedia().getMediaItem();
        if (!mediaItems.isEmpty()) {
            Picasso.with(getContext())
                    .load(BuildConfig.PREFIX_IMAGE + mediaItems.get(0).getMediaRefLink())
                    .into(productImage);
        }
    }
}
