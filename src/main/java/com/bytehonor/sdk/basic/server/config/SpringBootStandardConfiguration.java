package com.bytehonor.sdk.basic.server.config;

import java.util.List;

import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.bytehonor.sdk.basic.server.web.advisor.ErrorResponseAdvisor;
import com.bytehonor.sdk.basic.server.web.advisor.JsonResponseAdvisor;
import com.bytehonor.sdk.basic.server.web.controller.CustomErrorController;
import com.bytehonor.sdk.basic.server.web.mvc.WebMvcCustom;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ResourceProperties.class, SpringBootStandardProperties.class })
public class SpringBootStandardConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootStandardConfiguration.class);

    @Autowired(required = false)
    private List<ErrorViewResolver> errorViewResolvers;

    private final ServerProperties serverProperties;

    public SpringBootStandardConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "error-controller-enable", matchIfMissing = true)
    @ConditionalOnMissingBean(value = ErrorController.class)
    public CustomErrorController basicErrorController(ErrorAttributes errorAttributes) {
        LOG.info("[standard boot bean] CustomErrorController");
        return new CustomErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "error.advisor.enable", matchIfMissing = true)
    @ConditionalOnMissingBean(value = ErrorResponseAdvisor.class)
    public ErrorResponseAdvisor errorResponseAdvisor() {
        LOG.info("[standard boot bean] ErrorResponseAdvisor");
        return new ErrorResponseAdvisor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "response.advisor.enable", matchIfMissing = true)
    @ConditionalOnMissingBean(value = JsonResponseAdvisor.class)
    public JsonResponseAdvisor jsonResponseAdvisor() {
        LOG.info("[standard boot bean] JsonResponseAdvisor");
        return new JsonResponseAdvisor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "server.core.web", name = "mvc-custom-enable", matchIfMissing = true)
    public WebMvcCustom webMvcCustom() {
        LOG.info("[standard boot bean] WebMvcCustom");
        return new WebMvcCustom();
    }
}
