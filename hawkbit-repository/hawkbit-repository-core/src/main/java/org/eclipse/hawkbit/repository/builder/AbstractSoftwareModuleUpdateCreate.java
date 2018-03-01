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
public abstract class AbstractSoftwareModuleUpdateCreate<T> extends AbstractNamedEntityBuilder<T> {
<<<<<<< HEAD
    protected String version;
    protected String vendor;
    protected String type;

    public T type(final String type) {
        this.type = type;
=======
    @ValidString
    protected String version;

    @ValidString
    protected String vendor;

    @ValidString
    protected String type;

    public T type(final String type) {
        this.type = StringUtils.trimWhitespace(type);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    public T vendor(final String vendor) {
<<<<<<< HEAD
        this.vendor = vendor;
=======
        this.vendor = StringUtils.trimWhitespace(vendor);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getVendor() {
        return Optional.ofNullable(vendor);
    }

    public T version(final String version) {
<<<<<<< HEAD
        this.version = version;
=======
        this.version = StringUtils.trimWhitespace(version);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getVersion() {
        return Optional.ofNullable(version);
    }

}
