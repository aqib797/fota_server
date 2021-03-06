/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.event.remote;

<<<<<<< HEAD
=======
import org.eclipse.hawkbit.repository.event.entitiy.EntityDeletedEvent;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.eclipse.hawkbit.repository.model.DistributionSet;

/**
 * Defines the remote event for deletion of {@link DistributionSet}.
 */
<<<<<<< HEAD
public class DistributionSetDeletedEvent extends RemoteIdEvent {
=======
public class DistributionSetDeletedEvent extends RemoteIdEvent implements EntityDeletedEvent {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public DistributionSetDeletedEvent() {
        // for serialization libs like jackson
    }

    /**
     * Constructor.
     * 
     * @param tenant
     *            the tenant
     * @param entityId
     *            the entity id
     * @param applicationId
     *            the origin application id
     */
    public DistributionSetDeletedEvent(final String tenant, final Long entityId, final String entityClass,
            final String applicationId) {
        super(entityId, tenant, entityClass, applicationId);
    }
}
