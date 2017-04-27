package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.domain.MediaItem;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MediaItemDataMapperTest {

    MediaItemDataMapper mediaItemDataMapper;
    List<MediaItemResponse> mediaItemResponses;
    MediaItemResponse mediaItemResponse1;
    MediaItemResponse mediaItemResponse2;

    @Before
    public void setUp() throws Exception {
        mediaItemDataMapper = new MediaItemDataMapper();
        mediaItemResponses = new ArrayList<>();
        mediaItemResponse1 = new MediaItemResponse();
        mediaItemResponse2 = new MediaItemResponse();
        mediaItemResponse1.setMediaType("image");
        mediaItemResponse1.setMediaRefLink("/media/royal/shared_assets/images/Lunch-4x4-ride-BB83-mosaic.jpg");
        mediaItemResponse2.setMediaType("image");
        mediaItemResponse2.setMediaRefLink("/media/royal/shared_assets/images/Lunch-4x4-ride-BB83-mosaic.jpg");
        mediaItemResponses.add(mediaItemResponse1);
        mediaItemResponses.add(mediaItemResponse2);
    }

    @Test
    public void transform() throws Exception {
        List<MediaItem> mediaItemList = mediaItemDataMapper.transform(mediaItemResponses, null);
        assertNotNull(mediaItemList);
        assertEquals(mediaItemList.size(), mediaItemResponses.size());

        for (int i = 0; i < mediaItemList.size(); i++) {
            assertEquals(mediaItemList.get(i).getMediaType(), mediaItemResponses.get(i).getMediaType());
            assertEquals(mediaItemList.get(i).getMediaRefLink(), mediaItemResponses.get(i).getMediaRefLink());

        }

    }

}