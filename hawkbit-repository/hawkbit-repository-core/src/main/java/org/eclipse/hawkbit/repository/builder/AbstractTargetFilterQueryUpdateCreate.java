/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.builder;

import java.util.Optional;

<<<<<<< HEAD
=======
import org.eclipse.hawkbit.repository.ValidString;
import org.springframework.util.StringUtils;

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
/**
 * Create and update builder DTO.
 *
 * @param <T>
 *            update or create builder interface
 */
public abstract class AbstractTargetFilterQueryUpdateCreate<T> extends AbstractBaseEntityBuilder {
<<<<<<< HEAD
    protected String name;
    protected String query;
=======
    @ValidString
    protected String name;

    @ValidString
    protected String query;

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    protected Long set;

    public T set(final long set) {
        this.set = set;
        return (T) this;
    }

    public Optional<Long> getSet() {
        return Optional.ofNullable(set);
    }

    public T name(final String name) {
<<<<<<< HEAD
        this.name = name;
=======
        this.name = StringUtils.trimWhitespace(name);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public T query(final String query) {
<<<<<<< HEAD
        this.query = query;
=======
        this.query = StringUtils.trimWhitespace(query);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getQuery() {
        return Optional.ofNullable(query);
    }
}
