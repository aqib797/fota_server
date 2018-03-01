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
public class AbstractTagUpdateCreate<T> extends AbstractNamedEntityBuilder<T> {
<<<<<<< HEAD
    protected String colour;

    public T colour(final String colour) {
        this.colour = colour;
=======
    @ValidString
    protected String colour;

    public T colour(final String colour) {
        this.colour = StringUtils.trimWhitespace(colour);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public Optional<String> getColour() {
        return Optional.ofNullable(colour);
    }

}
