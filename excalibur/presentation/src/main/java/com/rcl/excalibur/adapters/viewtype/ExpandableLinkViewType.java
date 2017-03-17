package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class ExpandableLinkViewType implements RecyclerViewType {

    private String title;
    private String[] content;
    private boolean contentWithCheckMark;

    public ExpandableLinkViewType(String title, String[] content, boolean contentWithCheckMark) {
        this.title = title;
        this.content = content;
        this.contentWithCheckMark = contentWithCheckMark;
    }

    public String getTitle() {
        return title;
    }

    public String[] getContent() {
        return content;
    }

    public boolean isContentWithCheckMark() {
        return contentWithCheckMark;
    }

    public void setContentWithCheckMark(boolean contentWithCheckMark) {
        this.contentWithCheckMark = contentWithCheckMark;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK;
    }
}
