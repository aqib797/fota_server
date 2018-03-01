/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.integration.listener;

import org.eclipse.hawkbit.rabbitmq.test.listener.TestRabbitListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class DeadletterListener implements TestRabbitListener {

    public static final String LISTENER_ID = "deadletter";

    @Override
    @RabbitListener(id = "deadletter", queues = "dmf_connector_deadletter_ttl")
    public void handleMessage(Message message) {
        // currently the message is not needed
    }

}
