/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.hawkbit.ControllerPollProperties;
import org.eclipse.hawkbit.HawkbitServerProperties;
import org.eclipse.hawkbit.api.ArtifactUrlHandlerProperties;
import org.eclipse.hawkbit.api.PropertyBasedArtifactUrlHandler;
import org.eclipse.hawkbit.artifact.repository.ArtifactFilesystemProperties;
import org.eclipse.hawkbit.artifact.repository.ArtifactFilesystemRepository;
import org.eclipse.hawkbit.artifact.repository.ArtifactRepository;
import org.eclipse.hawkbit.cache.DefaultDownloadIdCache;
import org.eclipse.hawkbit.cache.DownloadIdCache;
import org.eclipse.hawkbit.cache.TenantAwareCacheManager;
import org.eclipse.hawkbit.event.BusProtoStuffMessageConverter;
import org.eclipse.hawkbit.repository.RolloutStatusCache;
import org.eclipse.hawkbit.repository.event.ApplicationEventFilter;
import org.eclipse.hawkbit.repository.model.helper.EventPublisherHolder;
import org.eclipse.hawkbit.repository.rsql.VirtualPropertyReplacer;
import org.eclipse.hawkbit.repository.rsql.VirtualPropertyResolver;
import org.eclipse.hawkbit.repository.test.util.TestdataFactory;
import org.eclipse.hawkbit.security.DdiSecurityProperties;
import org.eclipse.hawkbit.security.HawkbitSecurityProperties;
import org.eclipse.hawkbit.security.SecurityContextTenantAware;
import org.eclipse.hawkbit.security.SecurityTokenGenerator;
import org.eclipse.hawkbit.security.SpringSecurityAuditorAware;
import org.eclipse.hawkbit.security.SystemSecurityContext;
import org.eclipse.hawkbit.tenancy.TenantAware;
import org.eclipse.hawkbit.tenancy.configuration.TenantConfigurationProperties;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cloud.bus.ConditionalOnBusEnabled;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.AuditorAware;
import org.springframework.integration.support.locks.DefaultLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.concurrent.DelegatingSecurityContextScheduledExecutorService;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.util.AntPathMatcher;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * Spring context configuration required for Dev.Environment.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, mode = AdviceMode.PROXY, proxyTargetClass = false, securedEnabled = true)
@EnableConfigurationProperties({ HawkbitServerProperties.class, DdiSecurityProperties.class,
        ArtifactUrlHandlerProperties.class, ArtifactFilesystemProperties.class, HawkbitSecurityProperties.class,
        ControllerPollProperties.class, TenantConfigurationProperties.class })
@Profile("test")
@EnableAutoConfiguration
@PropertySource("classpath:/hawkbit-test-defaults.properties")
public class TestConfiguration implements AsyncConfigurer {

    /**
     * Disables caching during test to avoid concurrency failures during test.
     */
    @Bean
    RolloutStatusCache rolloutStatusCache(final TenantAware tenantAware) {
        return new RolloutStatusCache(tenantAware, 0);
    }

    @Bean
    LockRegistry lockRegistry() {
        return new DefaultLockRegistry();
    }

    @Bean
    SecurityTokenGenerator securityTokenGenerator() {
        return new SecurityTokenGenerator();
    }

    @Bean
    SystemSecurityContext systemSecurityContext(final TenantAware tenantAware) {
        return new SystemSecurityContext(tenantAware);
    }

    @Bean
    ArtifactRepository artifactRepository(final ArtifactFilesystemProperties artifactFilesystemProperties) {
        return new ArtifactFilesystemRepository(artifactFilesystemProperties);
    }

    @Bean
    TestdataFactory testdataFactory() {
        return new TestdataFactory();
    }

    @Bean
    PropertyBasedArtifactUrlHandler testPropertyBasedArtifactUrlHandler(
            final ArtifactUrlHandlerProperties urlHandlerProperties) {
        return new PropertyBasedArtifactUrlHandler(urlHandlerProperties);
    }

    @Bean
    TenantAware tenantAware() {
        return new SecurityContextTenantAware();
    }

    @Bean
    TenantAwareCacheManager cacheManager() {
        return new TenantAwareCacheManager(new GuavaCacheManager(), tenantAware());
    }

    /**
     * Bean for the download id cache.
     *
     * @return the cache
     */
    @Bean
    DownloadIdCache downloadIdCache() {
        return new DefaultDownloadIdCache(cacheManager());
    }

    @Bean(name = AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    SimpleApplicationEventMulticaster applicationEventMulticaster(final ApplicationEventFilter applicationEventFilter) {
        final SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new FilterEnabledApplicationEventPublisher(
                applicationEventFilter);
        simpleApplicationEventMulticaster.setTaskExecutor(asyncExecutor());
        return simpleApplicationEventMulticaster;
    }

    private static class FilterEnabledApplicationEventPublisher extends SimpleApplicationEventMulticaster {

        private final ApplicationEventFilter applicationEventFilter;

        FilterEnabledApplicationEventPublisher(final ApplicationEventFilter applicationEventFilter) {
            this.applicationEventFilter = applicationEventFilter;
        }

        @Override
        public void multicastEvent(final ApplicationEvent event, final ResolvableType eventType) {
            if (applicationEventFilter.filter(event)) {
                return;
            }

            super.multicastEvent(event, eventType);
        }
    }

    @Bean
    EventPublisherHolder eventBusHolder() {
        return EventPublisherHolder.getInstance();
    }

    @Bean
    Executor asyncExecutor() {
        return new DelegatingSecurityContextExecutorService(Executors.newSingleThreadExecutor());
    }

    @Bean
    AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Override
    public Executor getAsyncExecutor() {
        return asyncExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return new DelegatingSecurityContextScheduledExecutorService(Executors.newScheduledThreadPool(1,
                new ThreadFactoryBuilder().setNameFormat("central-scheduled-executor-pool-%d").build()));
    }

    /**
     *
     * @return returns a VirtualPropertyReplacer
     */
    @Bean
    VirtualPropertyReplacer virtualPropertyReplacer() {
        return new VirtualPropertyResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    ServiceMatcher serviceMatcher(final ApplicationContext applicationContext) {
        final ServiceMatcher serviceMatcher = new ServiceMatcher();
        serviceMatcher.setMatcher(new AntPathMatcher(":"));
        serviceMatcher.setApplicationContext(applicationContext);
        return serviceMatcher;
    }

    /**
     *
     * @return the protostuff io message converter
     */
    @Bean
    @ConditionalOnBusEnabled
    MessageConverter busProtoBufConverter() {
        return new BusProtoStuffMessageConverter();
    }
}
