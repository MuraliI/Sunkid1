
package com.rcl.excalibur.data.service.response;

import java.util.List;


public class ProductMediaResponse {

    private List<MediaItemResponse> mediaItem;

    public ProductMediaResponse() {
    }

    public List<MediaItemResponse> getMediaItem() {
        return mediaItem;
    }

    public void setMediaItem(List<MediaItemResponse> mediaItem) {
        this.mediaItem = mediaItem;
    }
}
