/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.builder;

import java.net.URI;
import java.util.Optional;

<<<<<<< HEAD
import org.eclipse.hawkbit.repository.exception.InvalidTargetAddressException;
import org.eclipse.hawkbit.repository.model.TargetUpdateStatus;
=======
import org.eclipse.hawkbit.repository.ValidString;
import org.eclipse.hawkbit.repository.exception.InvalidTargetAddressException;
import org.eclipse.hawkbit.repository.model.TargetUpdateStatus;
import org.springframework.util.StringUtils;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

/**
 * Create and update builder DTO.
 *
 * @param <T>
 *            update or create builder interface
 */
public class AbstractTargetUpdateCreate<T> extends AbstractNamedEntityBuilder<T> {
<<<<<<< HEAD
    protected String controllerId;
    protected String address;
    protected String securityToken;
=======
    @ValidString
    protected String controllerId;

    protected String address;

    @ValidString
    protected String securityToken;

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    protected Long lastTargetQuery;
    protected TargetUpdateStatus status;

    protected AbstractTargetUpdateCreate(final String controllerId) {
<<<<<<< HEAD
        this.controllerId = controllerId;
=======
        this.controllerId = StringUtils.trimWhitespace(controllerId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    }

    public T status(final TargetUpdateStatus status) {
        this.status = status;
        return (T) this;
    }

    public T address(final String address) {
        // check if this is a real URI
        if (address != null) {
            try {
<<<<<<< HEAD
                URI.create(address);
=======
                URI.create(StringUtils.trimWhitespace(address));
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
            } catch (final IllegalArgumentException e) {
                throw new InvalidTargetAddressException(
                        "The given address " + address + " violates the RFC-2396 specification", e);
            }
        }
        this.address = address;
        return (T) this;
    }

    public T securityToken(final String securityToken) {
<<<<<<< HEAD
        this.securityToken = securityToken;
=======
        this.securityToken = StringUtils.trimWhitespace(securityToken);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public T lastTargetQuery(final Long lastTargetQuery) {
        this.lastTargetQuery = lastTargetQuery;
        return (T) this;
    }

    public TargetCreate controllerId(final String controllerId) {
<<<<<<< HEAD
        this.controllerId = controllerId;
=======
        this.controllerId = StringUtils.trimWhitespace(controllerId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (TargetCreate) this;
    }

    public String getControllerId() {
        return controllerId;
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<String> getSecurityToken() {
        return Optional.ofNullable(securityToken);
    }

    public Optional<Long> getLastTargetQuery() {
        return Optional.ofNullable(lastTargetQuery);
    }

    public Optional<TargetUpdateStatus> getStatus() {
        return Optional.ofNullable(status);
    }

}
