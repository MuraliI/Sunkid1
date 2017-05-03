package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.mockobjects.MockMediaitemResponse;
import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.domain.MediaItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MediaItemDataMapperTest {

    MediaItemDataMapper mediaItemDataMapper;
    List<MediaItemResponse> mediaItemResponses;


    @Before
    public void setUp() throws Exception {
        mediaItemDataMapper = new MediaItemDataMapper();
        mediaItemResponses = MockMediaitemResponse.getMockMockMediaitemResponse();

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