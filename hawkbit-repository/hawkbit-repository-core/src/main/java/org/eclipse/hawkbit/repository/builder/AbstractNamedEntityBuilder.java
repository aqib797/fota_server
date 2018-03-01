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
public abstract class AbstractNamedEntityBuilder<T> extends AbstractBaseEntityBuilder {

    protected String name;
    protected String description;

    public T name(final String name) {
        this.name = name;
=======
import org.eclipse.hawkbit.repository.ValidString;
import org.springframework.util.StringUtils;

public abstract class AbstractNamedEntityBuilder<T> extends AbstractBaseEntityBuilder {

    @ValidString
    protected String name;

    @ValidString
    protected String description;

    public T name(final String name) {
        this.name = StringUtils.trimWhitespace(name);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public T description(final String description) {
<<<<<<< HEAD
        this.description = description;
=======
        this.description = StringUtils.trimWhitespace(description);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

}
