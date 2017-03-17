
package com.rcl.excalibur.data.service.response;

import java.util.List;


public class RestrictionMediaResponse {

    private List<MediaItemResponse> mediaItem;

    public RestrictionMediaResponse() {
    }

    public List<MediaItemResponse> getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(List<MediaItemResponse> mediaItem) {
        this.mediaItem = mediaItem;
    }
}
