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
import org.eclipse.hawkbit.repository.model.Action;

/**
 * Defines the remote event of updated a {@link Action}.
 */
<<<<<<< HEAD
public class ActionUpdatedEvent extends AbstractActionEvent {
=======
public class ActionUpdatedEvent extends AbstractActionEvent implements EntityUpdatedEvent {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    private static final long serialVersionUID = 2L;

    /**
     * Default constructor.
     */
    public ActionUpdatedEvent() {
        // for serialization libs like jackson
    }

    /**
     * Constructor
     * 
     * @param action
     *            the updated action
     * @param rolloutId
     *            rollout identifier (optional)
     * @param rolloutGroupId
     *            rollout group identifier (optional)
     * @param applicationId
     *            the origin application id
     */
    public ActionUpdatedEvent(final Action action, final Long rolloutId, final Long rolloutGroupId,
            final String applicationId) {
        super(action, rolloutId, rolloutGroupId, applicationId);
    }

}
