package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class ExpandableAccesibilityViewType implements RecyclerViewType {
    private String title;
    private String[] imageUrl;
    private String[] content;
    private String[] description;
    private boolean contentWithCheckMark;

    public ExpandableAccesibilityViewType(String title, String[] imageUrl,
                                          String[] content,
                                          String[] description,
                                          boolean contentWithCheckMark) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
        this.description = description;
        this.contentWithCheckMark = contentWithCheckMark;
    }

    public String getTitle() {
        return title;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public String[] getContent() {
        return content;
    }

    public String[] getDescription() {
        return description;
    }

    public boolean isContentWithCheckMark() {
        return contentWithCheckMark;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ACCESSIBILITY_VIEW;
    }
}
