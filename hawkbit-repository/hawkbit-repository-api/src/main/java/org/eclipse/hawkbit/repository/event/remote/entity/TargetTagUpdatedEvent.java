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
import org.eclipse.hawkbit.repository.model.TargetTag;

/**
 * Defines the remote event for updating a {@link TargetTag}.
 *
 */
<<<<<<< HEAD
public class TargetTagUpdatedEvent extends RemoteEntityEvent<TargetTag> {
=======
public class TargetTagUpdatedEvent extends RemoteEntityEvent<TargetTag> implements EntityUpdatedEvent {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public TargetTagUpdatedEvent() {
        // for serialization libs like jackson
    }

    /**
     * Constructor.
     * 
     * @param tag
     *            the tag which is updated
     * @param applicationId
     *            the origin application id
     */
    public TargetTagUpdatedEvent(final TargetTag tag, final String applicationId) {
        super(tag, applicationId);
    }
}
