package com.rcl.excalibur.custom.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcl.excalibur.R;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpandableDescriptionLayout extends FrameLayout implements AnalyticsManager {

    private static final int DEFAULT_MAX_LINES_COUNT = 6;
    private static final int DEFAULT_ANIMATION_DURATION = 1000;

    @Bind(R.id.text_description) TextView description;
    @Bind(R.id.view_transparent_layout) View transParentLayout;
    @Bind(R.id.text_see_more_btn) TextView seeMoreBtnText;
    @Bind(R.id.image_dropdown_arrow) ImageView seeMoreArrowIcon;
    @Bind(R.id.layout_show_more_container) View showMoreContainer;

    private int maxLineCount;
    private int animationDuration;
    private boolean isExpanded;
    private boolean firstOnMeasure = true;
    private CharSequence expandedBtnText;
    private CharSequence collapsedBtnText;
    private AnalyticEvent eventBuilder;

    public ExpandableDescriptionLayout(Context context) {
        super(context);
        initialize(context, null, 0, 0);
    }

    public ExpandableDescriptionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0, 0);
    }

    public ExpandableDescriptionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr, 0);
    }

    public ExpandableDescriptionLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        LayoutInflater.from(context).inflate(R.layout.custom_view_expandable_description, this);
        ButterKnife.bind(this, this);
        TypedArray tp = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ExpandableDescriptionLayout,
                defStyleAttr,
                defStyleRes);
        try {
            //Btn text
            CharSequence collapsedText = tp.getText(R.styleable.ExpandableDescriptionLayout_seeMoreCollapsedButtonText);
            collapsedBtnText = collapsedText != null ? collapsedText
                    : getResources().getString(R.string.discover_item_detail_see_more_btn);
            expandedBtnText = tp.getText(R.styleable.ExpandableDescriptionLayout_seeMoreExpandedButtonText);

            description.setText(tp.getText(R.styleable.ExpandableDescriptionLayout_descriptionText));
            seeMoreBtnText.setText(collapsedText);
            maxLineCount = tp.getInteger(R.styleable.ExpandableDescriptionLayout_collapseLinesCount, DEFAULT_MAX_LINES_COUNT);
            animationDuration = tp.getInteger(R.styleable.ExpandableDescriptionLayout_animationDuration, DEFAULT_ANIMATION_DURATION);
        } finally {
            tp.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //Always check go for the best case that the view doesn't need to be collapsed;
        description.setMaxLines(Integer.MAX_VALUE);
        measureChild(description, widthMeasureSpec, heightMeasureSpec);

        //Check if the see more btn will be needed due to the size of the text
        if (firstOnMeasure && View.VISIBLE == showMoreContainer.getVisibility()) {
            boolean showSeeMoreBtn = description.getLineCount() > maxLineCount;
            showMoreContainer.setVisibility(showSeeMoreBtn ? View.VISIBLE : View.GONE);
            transParentLayout.setVisibility(showSeeMoreBtn ? View.VISIBLE : View.GONE);
            firstOnMeasure = false;
        }

        //if the show more btn is indeed needed, check if it needs to be drawn collapsed or expanded
        if (View.VISIBLE == showMoreContainer.getVisibility()) {
            collapseDescriptionText(description.getLineCount() > maxLineCount && !isExpanded);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @OnClick(R.id.layout_show_more_container)
    public void onShowMoreClicked(View view) {
        isExpanded = !isExpanded;
        seeMoreBtnText.setText(isExpanded ? expandedBtnText : collapsedBtnText);
        seeMoreArrowIcon.setImageResource(isExpanded ? R.drawable.ic_arrow_drop_up : R.drawable.ic_arrow_drop_down);
        if (isExpanded)
            sendAnalytics();
        requestLayout();
    }

    public void collapseDescriptionText(boolean collapse) {
        transParentLayout.setVisibility(collapse ? View.VISIBLE : View.GONE);
        description.setMaxLines(collapse ? maxLineCount : Integer.MAX_VALUE);
    }

    public int getMaxLineCount() {
        return maxLineCount;
    }

    public void setMaxLineCount(int maxLineCount) {
        this.maxLineCount = maxLineCount;
    }

    public Spanned getDescription() {
        return new SpannedString(description.getText());
    }

    public void setDescription(Spanned description) {
        this.description.setText(description);
    }

    public String getSeeMoreBtnText() {
        return seeMoreBtnText.getText().toString();
    }

    public void setSeeMoreBtnText(String seeMoreBtnText) {
        this.seeMoreBtnText.setText(seeMoreBtnText);
    }

    @Override
    public void setAnalyticEvent(AnalyticEvent analyticEvent) {
        this.eventBuilder = analyticEvent;
    }

    @Override
    public void sendAnalytics() {
        //TODO Implement analytics call here.
    }
}
