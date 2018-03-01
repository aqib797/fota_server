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
import org.eclipse.hawkbit.repository.model.Target;

/**
 * Defines the remote event for updating a {@link Target}.
 *
 */
<<<<<<< HEAD
public class TargetUpdatedEvent extends RemoteEntityEvent<Target> {
=======
public class TargetUpdatedEvent extends RemoteEntityEvent<Target> implements EntityUpdatedEvent {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TargetUpdatedEvent() {
        // for serialization libs like jackson
    }

    /**
     * Constructor.
     * 
     * @param baseEntity
     *            Target entity
     * @param applicationId
     *            the origin application id
     */
    public TargetUpdatedEvent(final Target baseEntity, final String applicationId) {
        super(baseEntity, applicationId);
    }

}
