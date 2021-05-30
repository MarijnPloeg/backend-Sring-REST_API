package nl.marijnploeg.kitereparatie.config;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@ContextConfiguration(classes = {MvcConfig.class})
@ExtendWith(SpringExtension.class)
public class MvcConfigTest {
    @Autowired
    private MvcConfig mvcConfig;

    @Test
    public void testAddResourceHandlers() {
        // TODO: Make assertions for this test

        AnnotationConfigReactiveWebApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext();
        this.mvcConfig.addResourceHandlers(new ResourceHandlerRegistry(applicationContext, new MockServletContext()));
    }

    @Test
    public void testAddResourceHandlers2() {
        ResourceHandlerRegistry resourceHandlerRegistry = mock(ResourceHandlerRegistry.class);
        when(resourceHandlerRegistry.addResourceHandler(any()))
                .thenReturn(new ResourceHandlerRegistration("foo", "foo", "foo"));
        this.mvcConfig.addResourceHandlers(resourceHandlerRegistry);
        verify(resourceHandlerRegistry).addResourceHandler(any());
    }

    @Test
    public void testAddResourceHandlers3() {
        ResourceHandlerRegistration resourceHandlerRegistration = new ResourceHandlerRegistration("foo", "foo", "foo");
        resourceHandlerRegistration.addResourceLocations("profile-img");
        ResourceHandlerRegistry resourceHandlerRegistry = mock(ResourceHandlerRegistry.class);
        when(resourceHandlerRegistry.addResourceHandler(any())).thenReturn(resourceHandlerRegistration);
        this.mvcConfig.addResourceHandlers(resourceHandlerRegistry);
        verify(resourceHandlerRegistry).addResourceHandler(any());
    }
}

