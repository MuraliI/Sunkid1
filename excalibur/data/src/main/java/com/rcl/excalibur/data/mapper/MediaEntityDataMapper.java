package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.MediaValueEntity;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class MediaEntityDataMapper extends BaseDataMapper<List<MediaItem>, List<MediaValueEntity>, Void> {


    @Nullable
    @Override
    public List<MediaItem> transform(List<MediaValueEntity> input, Void additionalArg) {
        ArrayList<MediaItem> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(input)) {
            return items;
        }
        for (MediaValueEntity mediaValueEntity : input) {

            if (mediaValueEntity == null) {
                continue;
            }

            MediaItem output = new MediaItem();
            output.setMediaRefLink(mediaValueEntity.getLink());
            output.setMediaType(mediaValueEntity.getType());
            items.add(output);
        }

        return items;
    }
}
