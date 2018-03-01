/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository;

import java.util.Collection;
import java.util.Optional;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;
=======
import javax.validation.constraints.NotEmpty;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

import org.eclipse.hawkbit.im.authentication.SpPermission.SpringEvalExpressions;
import org.eclipse.hawkbit.repository.builder.DistributionSetTypeCreate;
import org.eclipse.hawkbit.repository.builder.DistributionSetTypeUpdate;
import org.eclipse.hawkbit.repository.exception.EntityNotFoundException;
import org.eclipse.hawkbit.repository.exception.EntityReadOnlyException;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.DistributionSetType;
import org.eclipse.hawkbit.repository.model.SoftwareModuleType;
<<<<<<< HEAD
import org.hibernate.validator.constraints.NotEmpty;
=======
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Management service for {@link DistributionSetType}s.
 *
 */
public interface DistributionSetTypeManagement
        extends RepositoryManagement<DistributionSetType, DistributionSetTypeCreate, DistributionSetTypeUpdate> {

    /**
     * @param key
     *            as {@link DistributionSetType#getKey()}
     * @return {@link DistributionSetType}
     */

    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Optional<DistributionSetType> getByKey(@NotEmpty String key);

    /**
     * @param name
     *            as {@link DistributionSetType#getName()}
     * @return {@link DistributionSetType}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Optional<DistributionSetType> getByName(@NotEmpty String name);

    /**
     * Assigns {@link DistributionSetType#getMandatoryModuleTypes()}.
     * 
     * @param dsTypeId
     *            to update
     * @param softwareModuleTypeIds
     *            to assign
     * @return updated {@link DistributionSetType}
     * 
     * @throws EntityNotFoundException
     *             in case the {@link DistributionSetType} or at least one of
     *             the {@link SoftwareModuleType}s do not exist
     * 
     * @throws EntityReadOnlyException
     *             if the {@link DistributionSetType} while it is already in use
     *             by a {@link DistributionSet}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSetType assignOptionalSoftwareModuleTypes(@NotNull Long dsTypeId,
=======
    DistributionSetType assignOptionalSoftwareModuleTypes(long dsTypeId,
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
            @NotEmpty Collection<Long> softwareModuleTypeIds);

    /**
     * Assigns {@link DistributionSetType#getOptionalModuleTypes()}.
     * 
     * @param dsTypeId
     *            to update
     * @param softwareModuleTypes
     *            to assign
     * @return updated {@link DistributionSetType}
     * 
     * @throws EntityNotFoundException
     *             in case the {@link DistributionSetType} or at least one of
     *             the {@link SoftwareModuleType}s do not exist
     * 
     * @throws EntityReadOnlyException
     *             if the {@link DistributionSetType} while it is already in use
     *             by a {@link DistributionSet}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSetType assignMandatorySoftwareModuleTypes(@NotNull Long dsTypeId,
=======
    DistributionSetType assignMandatorySoftwareModuleTypes(long dsTypeId,
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
            @NotEmpty Collection<Long> softwareModuleTypes);

    /**
     * Unassigns a {@link SoftwareModuleType} from the
     * {@link DistributionSetType}. Does nothing if {@link SoftwareModuleType}
     * has not been assigned in the first place.
     * 
     * @param dsTypeId
     *            to update
     * @param softwareModuleId
     *            to unassign
     * @return updated {@link DistributionSetType}
     * 
     * @throws EntityNotFoundException
     *             in case the {@link DistributionSetType} does not exist
     * 
     * @throws EntityReadOnlyException
     *             if the {@link DistributionSetType} while it is already in use
     *             by a {@link DistributionSet}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSetType unassignSoftwareModuleType(@NotNull Long dsTypeId, @NotNull Long softwareModuleId);
=======
    DistributionSetType unassignSoftwareModuleType(long dsTypeId, long softwareModuleId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

}
