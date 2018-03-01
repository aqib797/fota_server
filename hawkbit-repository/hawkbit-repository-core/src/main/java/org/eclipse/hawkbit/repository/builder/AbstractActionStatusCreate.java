/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.builder;

import java.util.ArrayList;
import java.util.Collection;
<<<<<<< HEAD
import java.util.Optional;

import org.eclipse.hawkbit.repository.model.Action.Status;
=======
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.hawkbit.repository.ValidString;
import org.eclipse.hawkbit.repository.model.Action.Status;
import org.springframework.util.StringUtils;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

/**
 * Create and update builder DTO.
 *
 * @param <T>
 *            update or create builder interface
 */
public abstract class AbstractActionStatusCreate<T> {
    protected Status status;
    protected Long occurredAt;
<<<<<<< HEAD
    protected Collection<String> messages;
=======

    protected List<@ValidString String> messages;

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    protected Long actionId;

    public Long getActionId() {
        return actionId;
    }

    public T status(final Status status) {
        this.status = status;

        return (T) this;
    }

<<<<<<< HEAD
    public T occurredAt(final Long occurredAt) {
=======
    public T occurredAt(final long occurredAt) {
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        this.occurredAt = occurredAt;

        return (T) this;
    }

    public T messages(final Collection<String> messages) {
        if (this.messages == null) {
<<<<<<< HEAD
            this.messages = messages;
        } else {
            this.messages.addAll(messages);
=======
            this.messages = messages.stream().map(StringUtils::trimWhitespace).collect(Collectors.toList());
        } else {
            this.messages.addAll(messages.stream().map(StringUtils::trimWhitespace).collect(Collectors.toList()));
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        }

        return (T) this;
    }

    public T message(final String message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
<<<<<<< HEAD
        this.messages.add(message);
=======
        this.messages.add(StringUtils.trimWhitespace(message));
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

        return (T) this;
    }

    public Optional<Long> getOccurredAt() {
        return Optional.ofNullable(occurredAt);
    }

}
