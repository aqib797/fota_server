/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.builder;

<<<<<<< HEAD
import org.eclipse.hawkbit.repository.model.Target;
import org.hibernate.validator.constraints.NotEmpty;
=======
import javax.validation.constraints.NotEmpty;

import org.eclipse.hawkbit.repository.model.Target;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

/**
 * Builder for {@link Target}.
 *
 */
public interface TargetBuilder {

    /**
     * @param controllerId
     *            of the updatable entity
     * @return builder instance
     */
    TargetUpdate update(@NotEmpty String controllerId);

    /**
     * @return builder instance
     */
    TargetCreate create();
}
