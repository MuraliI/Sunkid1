package com.rcl.excalibur.utils.analytics;

import com.adobe.mobile.Analytics;
import com.rcl.excalibur.model.DiscoverItemModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Analytics.class)
public class AnalyticsUtilsTest {

    DiscoverItemModel discoverItemModel;
    String type;

    @Before
    public void setUp() {
        discoverItemModel = new DiscoverItemModel();
        type = "myType";
        discoverItemModel.setType(type);
    }

    @Test
    public void trackEvent() throws Exception {
        PowerMockito.mockStatic(Analytics.class);

        AnalyticEvent event = AnalyticsUtils.getDiscoverDetailEvent(discoverItemModel);
        AnalyticsUtils.trackEvent(event);

        PowerMockito.verifyStatic(Mockito.times(1));
        Analytics.trackAction(Mockito.anyString(), Mockito.anyMap());
    }

    @Test
    public void getDiscoverDetailEvent() throws Exception {
        AnalyticEvent event = AnalyticsUtils.getDiscoverDetailEvent(discoverItemModel);
        Assert.assertTrue("".equals(event.getEventName()));
        Assert.assertTrue(event.getKeyValues().containsKey(AnalyticsConstants.KEY_DISCOVER_ITEM_DETAILS_ACTIVITY_TYPE));
        Assert.assertTrue(event.getKeyValues().get(AnalyticsConstants.KEY_DISCOVER_ITEM_DETAILS_ACTIVITY_TYPE).equals(type));
    }
}
