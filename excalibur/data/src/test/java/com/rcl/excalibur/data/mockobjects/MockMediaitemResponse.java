package com.rcl.excalibur.data.mockobjects;


import com.rcl.excalibur.data.service.response.MediaItemResponse;

import java.util.ArrayList;
import java.util.List;

public final class MockMediaitemResponse {

    private MockMediaitemResponse() {
    }

    private static final String IMAGE1 = "image";
    private static final String IMAGE2 = "image";
    private static final String REF_LINK1 = "/media/royal/shared_assets/images/Lunch-4x4-ride-BB83-mosaic.jpg";
    private static final String REF_LINK2 = "/media/royal/shared_assets/images/Lunch-2x4-ride-BB83-mosaic.jpg";

    public static List<MediaItemResponse> getMockMockMediaitemResponse() {
        List<MediaItemResponse> mediaItemResponses = new ArrayList<>();
        MediaItemResponse mediaItemResponse1 = new MediaItemResponse();
        MediaItemResponse mediaItemResponse2 = new MediaItemResponse();
        mediaItemResponse1.setMediaType(IMAGE1);
        mediaItemResponse1.setMediaRefLink(REF_LINK1);
        mediaItemResponse2.setMediaType(IMAGE2);
        mediaItemResponse2.setMediaRefLink(REF_LINK2);
        mediaItemResponses.add(mediaItemResponse1);
        mediaItemResponses.add(mediaItemResponse2);
        return mediaItemResponses;
    }
}
