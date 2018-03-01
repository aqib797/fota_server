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
import org.eclipse.hawkbit.repository.model.RolloutGroupConditions;
=======
import org.eclipse.hawkbit.repository.ValidString;
import org.eclipse.hawkbit.repository.model.RolloutGroupConditions;
import org.springframework.util.StringUtils;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

/**
 * Create builder DTO.
 *
 * @param <T>
 *            update or create builder interface
 */
public abstract class AbstractRolloutGroupCreate<T> extends AbstractNamedEntityBuilder<T> {
<<<<<<< HEAD
=======
    @ValidString
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    protected String targetFilterQuery;
    protected Float targetPercentage;
    protected RolloutGroupConditions conditions;

    public T targetFilterQuery(final String targetFilterQuery) {
<<<<<<< HEAD
        this.targetFilterQuery = targetFilterQuery;
=======
        this.targetFilterQuery = StringUtils.trimWhitespace(targetFilterQuery);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        return (T) this;
    }

    public T targetPercentage(final Float targetPercentage) {
        this.targetPercentage = targetPercentage;
        return (T) this;
    }

    public T conditions(final RolloutGroupConditions conditions) {
        this.conditions = conditions;
        return (T) this;
    }

}
