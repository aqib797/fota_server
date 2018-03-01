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
import org.eclipse.hawkbit.repository.event.entitiy.EntityUpdatedEvent;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.eclipse.hawkbit.repository.model.RolloutGroup;

/**
 * Defines the remote event of updated a {@link RolloutGroup}.
 */
<<<<<<< HEAD
public class RolloutGroupUpdatedEvent extends AbstractRolloutGroupEvent {
=======
public class RolloutGroupUpdatedEvent extends AbstractRolloutGroupEvent implements EntityUpdatedEvent {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    private static final long serialVersionUID = 2L;

    /**
     * Default constructor.
     */
    public RolloutGroupUpdatedEvent() {
        // for serialization libs like jackson
    }

    /**
     * Constructor
     * 
     * @param rolloutGroup
     *            the updated rolloutGroup
     * @param rolloutId
     *            of the related rollout
     * @param applicationId
     *            the origin application id
     */
    public RolloutGroupUpdatedEvent(final RolloutGroup rolloutGroup, final Long rolloutId, final String applicationId) {
        super(rolloutGroup, rolloutId, applicationId);
    }

}
