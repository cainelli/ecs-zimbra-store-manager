package com.emc.ecs.zimbra.integration;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.mailbox.Mailbox;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Askhat_Shagirov on 28.01.2015.
 */
public class EcsLocatorUtilTest {

    @Test
    public void getBucketName_GeneratesValidBucketName() throws ServiceException {
        Mailbox mbox = mock(Mailbox.class);
        when(mbox.getId()).thenReturn(1);

        assertEquals("zimbra.server1.1",EcsLocatorUtil.generateEcsLocator(mbox).getBucketName());
    }

    @Test
    public void toStringLocator_ConvertsEcsLocatorCorrectly() {
        String bucketName = "zimbra.server1.1";
        String key = "fc159b81-4f34-4b51-adbd-967185389fef";
        EcsLocator locator = new EcsLocator(bucketName, key);

        assertEquals("zimbra.server1.1/fc159b81-4f34-4b51-adbd-967185389fef", EcsLocatorUtil.toStringLocator(locator));
    }

    @Test
    public void fromStringLocator_ParsesValidEcsLocator() {
        EcsLocator locator = EcsLocatorUtil.fromStringLocator("zimbra.server1.1/fc159b81-4f34-4b51-adbd-967185389fef");

        assertEquals("zimbra.server1.1", locator.getBucketName());
        assertEquals("fc159b81-4f34-4b51-adbd-967185389fef", locator.getKey());
    }
}
