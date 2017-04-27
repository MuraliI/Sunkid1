package com.rcl.excalibur.data;


import com.rcl.excalibur.data.service.response.MediaItemResponse;

import java.util.ArrayList;
import java.util.List;

public final class MockMediaitemResponse {

    private  MockMediaitemResponse() {
    }

    public static List<MediaItemResponse> getMockMockMediaitemResponse() {
        List<MediaItemResponse> mediaItemResponses = new ArrayList<>();
        MediaItemResponse mediaItemResponse1 = new MediaItemResponse();
        MediaItemResponse mediaItemResponse2 = new MediaItemResponse();
        mediaItemResponse1.setMediaType("image");
        mediaItemResponse1.setMediaRefLink("/media/royal/shared_assets/images/Lunch-4x4-ride-BB83-mosaic.jpg");
        mediaItemResponse2.setMediaType("image");
        mediaItemResponse2.setMediaRefLink("/media/royal/shared_assets/images/Lunch-4x4-ride-BB83-mosaic.jpg");
        mediaItemResponses.add(mediaItemResponse1);
        mediaItemResponses.add(mediaItemResponse2);
        return mediaItemResponses;
    }
}
