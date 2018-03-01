/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.ui;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for Management UI customization.
 *
 */
@ConfigurationProperties("hawkbit.server.ui")
public class UiProperties implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean gravatar;

<<<<<<< HEAD
=======
    private final Links links = new Links();

    private final Login login = new Login();

    private final Demo demo = new Demo();

    private final Event event = new Event();

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    public boolean isGravatar() {
        return gravatar;
    }

    public void setGravatar(final boolean gravatar) {
        this.gravatar = gravatar;
    }

    /**
     * Demo account login information.
     *
     */
    public static class Demo implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Demo tenant.
         */
        private String tenant = "";
        /**
         * Demo user name.
         */
        private String user = "";

        /**
         * Demo user password.
         */
        // Exception squid:S2068 - Empty password
        @SuppressWarnings({ "squid:S2068" })
        private String password = "";

        public String getPassword() {
            return password;
        }

        public String getTenant() {
            return tenant;
        }

        public String getUser() {
            return user;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

        public void setTenant(final String tenant) {
            this.tenant = tenant;
        }

        public void setUser(final String user) {
            this.user = user;
        }

    }

    /**
     * Links to potentially other systems (e.g. support, user management,
     * documentation etc.).
     *
     */
    public static class Links implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Configuration of UI documentation links.
         *
         */
        public static class Documentation implements Serializable {
            private static final long serialVersionUID = 1L;
            /**
             * Link to root of documentation and user guides.
             */
            private String root = "";

            /**
             * Link to documentation of deployment view.
             */
            private String deploymentView = "";

            /**
             * Link to documentation of distribution view.
             */
            private String distributionView = "";

            /**
             * Link to documentation of upload view.
             */
            private String uploadView = "";

            /**
             * Link to documentation of system configuration view.
             */
            private String systemConfigurationView = "";

            /**
             * Link to security related documentation.
             */
            private String security = "";

            /**
             * Link to target filter view.
             */
            private String targetfilterView = "";

            /**
             * Link to documentation of rollout view.
             */
            private String rolloutView = "";

            public String getDeploymentView() {
                return deploymentView;
            }

            public String getDistributionView() {
                return distributionView;
            }

            public String getRolloutView() {
                return rolloutView;
            }

            public String getRoot() {
                return root;
            }

            public String getSecurity() {
                return security;
            }

            public String getSystemConfigurationView() {
                return systemConfigurationView;
            }

            public String getTargetfilterView() {
                return targetfilterView;
            }

            public String getUploadView() {
                return uploadView;
            }

            public void setDeploymentView(final String deploymentView) {
                this.deploymentView = deploymentView;
            }

            public void setDistributionView(final String distributionView) {
                this.distributionView = distributionView;
            }

            public void setRolloutView(final String rolloutView) {
                this.rolloutView = rolloutView;
            }

            public void setRoot(final String root) {
                this.root = root;
            }

            public void setSecurity(final String security) {
                this.security = security;
            }

            public void setSystemConfigurationView(final String systemConfigurationView) {
                this.systemConfigurationView = systemConfigurationView;
            }

            public void setTargetfilterView(final String targetfilterView) {
                this.targetfilterView = targetfilterView;
            }

            public void setUploadView(final String uploadView) {
                this.uploadView = uploadView;
            }

        }

        private final Documentation documentation = new Documentation();

        /**
         * Link to product support.
         */
        private String support = "";

        /**
         * Link to request a system account, access.
         */
        private String requestAccount = "";

        /**
         * Link to user management.
         */
        private String userManagement = "";

        public Documentation getDocumentation() {
            return documentation;
        }

        public String getRequestAccount() {
            return requestAccount;
        }

        public String getSupport() {
            return support;
        }

        public String getUserManagement() {
            return userManagement;
        }

        public void setRequestAccount(final String requestAccount) {
            this.requestAccount = requestAccount;
        }

        public void setSupport(final String support) {
            this.support = support;
        }

        public void setUserManagement(final String userManagement) {
            this.userManagement = userManagement;
        }

    }

    /**
     * Configuration of login view.
     *
     */
    public static class Login implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * Cookie configuration for login credential cookie.
         *
         */
        public static class Cookie implements Serializable {
            private static final long serialVersionUID = 1L;
            /**
             * Secure cookie enabled.
             */
            private boolean secure = true;

            public boolean isSecure() {
                return secure;
            }

            public void setSecure(final boolean secure) {
                this.secure = secure;
            }
        }

        private final Cookie cookie = new Cookie();

        public Cookie getCookie() {
            return cookie;
        }
    }

    /**
     * Configuration of the UI event bus.
     */
    public static class Event implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 
         * Configuration of the UI push.
         *
         */
        public static class Push implements Serializable {
            private static final long serialVersionUID = 1L;

            /**
             * The delay for the ui event forwarding.
             */
            private long delay = TimeUnit.SECONDS.toMillis(2);

            public long getDelay() {
                return delay;
            }

            public void setDelay(final long delay) {
                this.delay = delay;
            }
        }

        private final Push push = new Push();

        public Push getPush() {
            return push;
        }
    }

<<<<<<< HEAD
    private final Links links = new Links();

    private final Login login = new Login();

    private final Demo demo = new Demo();

    private final Event event = new Event();

=======
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    public Demo getDemo() {
        return demo;
    }

    public Links getLinks() {
        return links;
    }

    public Login getLogin() {
        return login;
    }

    public Event getEvent() {
        return event;
    }

}
