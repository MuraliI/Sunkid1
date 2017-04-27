package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class MediaItemDataMapper extends BaseDataMapper<List<MediaItem>, List<MediaItemResponse>, Void> {


    @Nullable
    @Override
    public List<MediaItem> transform(List<MediaItemResponse> input, Void additionalArg) {
        ArrayList<MediaItem> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(input)) {
            return items;
        }
        for (MediaItemResponse mediaItemResponse : input) {

            if (mediaItemResponse == null) {
                continue;
            }

            MediaItem output = new MediaItem();
            output.setMediaRefLink(mediaItemResponse.getMediaRefLink());
            output.setMediaType(mediaItemResponse.getMediaType());
            items.add(output);
        }

        return items;
    }
}
