package com.thoughtworks.www.samples.models;

import io.wcm.testing.mock.aem.junit.AemContext;
import junitx.util.PrivateAccessor;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.settings.SlingSettingsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ButtonModelTest {
    @Rule
    public AemContext context = new AemContext();

    @Mock
    SlingSettingsService settings;

    public Resource resource;

    private ButtonModel buttonModel;

    @Before
    public void setup() throws Exception {
        resource = context.create().resource("app/content/any/resource");
        context.addModelsForPackage("com.thoughtworks.www.samples.models");

        String slingId = UUID.randomUUID().toString();
        when(settings.getSlingId()).thenReturn(slingId);

        buttonModel = context.currentResource(resource).adaptTo(ButtonModel.class);
        PrivateAccessor.setField(buttonModel, "settings", settings);
    }


    @Test
    public void testGetText() throws Exception {
        assertNotNull(buttonModel);
        String msg = buttonModel.getText();
        assertNotNull(msg);
        assertTrue(msg.length() > 0);
    }

    @Test
    public void testGetLink() throws Exception {
        assertNotNull(buttonModel);
        String username = buttonModel.getLink();
        assertNotNull(username);
        assertTrue(username.length() > 0);
    }
}
