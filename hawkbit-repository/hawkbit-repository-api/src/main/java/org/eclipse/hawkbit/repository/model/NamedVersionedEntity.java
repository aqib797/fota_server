/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.model;

/**
 * Entities that have a name and a description.
 *
 */
public interface NamedVersionedEntity extends NamedEntity {
    /**
     * Maximum length of version.
     */
    int VERSION_MAX_SIZE = 64;

    /**
     * @return the version of entity.
     */
    String getVersion();
}
