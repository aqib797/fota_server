/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.event.remote.entity;

<<<<<<< HEAD
=======
import org.eclipse.hawkbit.repository.event.entitiy.EntityCreatedEvent;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.eclipse.hawkbit.repository.model.DistributionSet;

/**
 * Defines the the remote of creating a new {@link DistributionSet}.
 *
 */
<<<<<<< HEAD
public class DistributionSetCreatedEvent extends RemoteEntityEvent<DistributionSet> {
=======
public class DistributionSetCreatedEvent extends RemoteEntityEvent<DistributionSet> implements EntityCreatedEvent {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public DistributionSetCreatedEvent() {
        // for serialization libs like jackson
    }

    /**
     * Constructor.
     * 
     * @param distributionSet
     *            the created distributionSet
     * @param applicationId
     *            the origin application id
     */
    public DistributionSetCreatedEvent(final DistributionSet distributionSet, final String applicationId) {
        super(distributionSet, applicationId);
    }

}
