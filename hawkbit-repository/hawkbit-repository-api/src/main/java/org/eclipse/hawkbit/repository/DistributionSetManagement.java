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
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======
import javax.validation.constraints.NotEmpty;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import javax.validation.constraints.NotNull;

import org.eclipse.hawkbit.im.authentication.SpPermission.SpringEvalExpressions;
import org.eclipse.hawkbit.repository.builder.DistributionSetCreate;
import org.eclipse.hawkbit.repository.builder.DistributionSetUpdate;
import org.eclipse.hawkbit.repository.exception.EntityAlreadyExistsException;
import org.eclipse.hawkbit.repository.exception.EntityNotFoundException;
import org.eclipse.hawkbit.repository.exception.EntityReadOnlyException;
import org.eclipse.hawkbit.repository.exception.RSQLParameterSyntaxException;
import org.eclipse.hawkbit.repository.exception.RSQLParameterUnsupportedFieldException;
import org.eclipse.hawkbit.repository.exception.UnsupportedSoftwareModuleForThisDistributionSetException;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.DistributionSetFilter;
import org.eclipse.hawkbit.repository.model.DistributionSetFilter.DistributionSetFilterBuilder;
import org.eclipse.hawkbit.repository.model.DistributionSetMetadata;
import org.eclipse.hawkbit.repository.model.DistributionSetTag;
import org.eclipse.hawkbit.repository.model.DistributionSetTagAssignmentResult;
import org.eclipse.hawkbit.repository.model.MetaData;
import org.eclipse.hawkbit.repository.model.SoftwareModule;
import org.eclipse.hawkbit.repository.model.Tag;
import org.eclipse.hawkbit.repository.model.Target;
<<<<<<< HEAD
import org.hibernate.validator.constraints.NotEmpty;
=======
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Management service for {@link DistributionSet}s.
 *
 */
public interface DistributionSetManagement
        extends RepositoryManagement<DistributionSet, DistributionSetCreate, DistributionSetUpdate> {

    /**
     * Assigns {@link SoftwareModule} to existing {@link DistributionSet}.
     *
     * @param setId
     *            to assign and update
     * @param moduleIds
     *            to get assigned
     * @return the updated {@link DistributionSet}.
     * 
     * @throws EntityNotFoundException
     *             if (at least one) given module does not exist
     * 
     * @throws EntityReadOnlyException
     *             if use tries to change the {@link DistributionSet} s while
     *             the DS is already in use.
     * 
     * @throws UnsupportedSoftwareModuleForThisDistributionSetException
     *             is {@link SoftwareModule#getType()} is not supported by this
     *             {@link DistributionSet#getType()}.
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSet assignSoftwareModules(@NotNull Long setId, @NotEmpty Collection<Long> moduleIds);
=======
    DistributionSet assignSoftwareModules(long setId, @NotEmpty Collection<Long> moduleIds);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Assign a {@link DistributionSetTag} assignment to given
     * {@link DistributionSet}s.
     *
     * @param setIds
     *            to assign for
     * @param tagId
     *            to assign
     * @return list of assigned ds
     * 
     * @throws EntityNotFoundException
     *             if tag with given ID does not exist or (at least one) of the
     *             distribution sets.
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    List<DistributionSet> assignTag(@NotEmpty Collection<Long> setIds, @NotNull Long tagId);
=======
    List<DistributionSet> assignTag(@NotEmpty Collection<Long> setIds, long tagId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * creates a list of distribution set meta data entries.
     *
     * @param setId
     *            if the {@link DistributionSet} the metadata has to be created
     *            for
     * @param metadata
     *            the meta data entries to create or update
     * @return the updated or created distribution set meta data entries
     * 
     * @throws EntityNotFoundException
     *             if given set does not exist
     * @throws EntityAlreadyExistsException
     *             in case one of the meta data entry already exists for the
     *             specific key
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    List<DistributionSetMetadata> createMetaData(@NotNull Long setId, @NotEmpty Collection<MetaData> metadata);
=======
    List<DistributionSetMetadata> createMetaData(long setId, @NotEmpty Collection<MetaData> metadata);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * deletes a distribution set meta data entry.
     *
     * @param setId
     *            where meta data has to be deleted
     * @param key
     *            of the meta data element
     * 
     * @throws EntityNotFoundException
     *             if given set does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    void deleteMetaData(@NotNull Long setId, @NotEmpty String key);
=======
    void deleteMetaData(long setId, @NotEmpty String key);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * retrieves the distribution set for a given action.
     *
     * @param actionId
     *            the action associated with the distribution set
     * @return the distribution set which is associated with the action
     * 
     * @throws EntityNotFoundException
     *             if action with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Optional<DistributionSet> getByAction(@NotNull Long actionId);
=======
    Optional<DistributionSet> getByAction(long actionId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Find {@link DistributionSet} based on given ID including (lazy loaded)
     * details, e.g. {@link DistributionSet#getModules()}.
     *
     * Note: for performance reasons it is recommended to use {@link #get(Long)}
     * if details are not necessary.
     *
     * @param setId
     *            to look for.
     * @return {@link DistributionSet}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Optional<DistributionSet> getWithDetails(@NotNull Long setId);
=======
    Optional<DistributionSet> getWithDetails(long setId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Find distribution set by name and version.
     *
     * @param distributionName
     *            name of {@link DistributionSet}; case insensitive
     * @param version
     *            version of {@link DistributionSet}
     * @return the page with the found {@link DistributionSet}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Optional<DistributionSet> getByNameAndVersion(@NotEmpty String distributionName, @NotEmpty String version);

    /**
     * finds all meta data by the given distribution set id.
     * 
     * @param pageable
     *            the page request to page the result
     * @param setId
     *            the distribution set id to retrieve the meta data from
     *
     * @return a paged result of all meta data entries for a given distribution
     *         set id
     * 
     * @throws EntityNotFoundException
     *             if distribution set with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<DistributionSetMetadata> findMetaDataByDistributionSetId(@NotNull Pageable pageable, @NotNull Long setId);
=======
    Page<DistributionSetMetadata> findMetaDataByDistributionSetId(@NotNull Pageable pageable, long setId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds all meta data by the given distribution set id.
     * 
     * @param pageable
     *            the page request to page the result
     * @param setId
     *            the distribution set id to retrieve the meta data from
     * @param rsqlParam
     *            rsql query string
     *
     * @return a paged result of all meta data entries for a given distribution
     *         set id
     * 
     * @throws RSQLParameterUnsupportedFieldException
     *             if a field in the RSQL string is used but not provided by the
     *             given {@code fieldNameProvider}
     * @throws RSQLParameterSyntaxException
     *             if the RSQL syntax is wrong
     * 
     * @throws EntityNotFoundException
     *             of distribution set with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<DistributionSetMetadata> findMetaDataByDistributionSetIdAndRsql(@NotNull Pageable pageable,
            @NotNull Long setId, @NotNull String rsqlParam);
=======
    Page<DistributionSetMetadata> findMetaDataByDistributionSetIdAndRsql(@NotNull Pageable pageable, long setId,
            @NotNull String rsqlParam);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds all {@link DistributionSet}s.
     *
     * @param pageable
     *            the pagination parameter
     * @param complete
     *            to <code>true</code> for returning only completed distribution
     *            sets or <code>false</code> for only incomplete ones nor
     *            <code>null</code> to return both.
     *
     *
     * @return all found {@link DistributionSet}s
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Page<DistributionSet> findByCompleted(@NotNull Pageable pageable, Boolean complete);

    /**
     * method retrieves all {@link DistributionSet}s from the repository in the
     * following order:
     * <p>
     * 1) {@link DistributionSet}s which have the given {@link Target} as
     * {@link TargetInfo#getInstalledDistributionSet()}
     * <p>
     * 2) {@link DistributionSet}s which have the given {@link Target} as
     * {@link Target#getAssignedDistributionSet()}
     * <p>
     * 3) {@link DistributionSet}s which have no connection to the given
     * {@link Target} ordered by ID of the DistributionSet.
     *
     * @param pageable
     *            the page request to page the result set *
     * @param distributionSetFilterBuilder
     *            has details of filters to be applied
     * @param assignedOrInstalled
     *            the id of the Target to be ordered by
     * @return {@link DistributionSet}s
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Page<DistributionSet> findByFilterAndAssignedInstalledDsOrderedByLinkTarget(@NotNull Pageable pageable,
            @NotNull DistributionSetFilterBuilder distributionSetFilterBuilder, @NotEmpty String assignedOrInstalled);

    /**
     * retrieves {@link DistributionSet}s by filtering on the given parameters.
     *
     * @param pageable
     *            page parameter
     * @param distributionSetFilter
     *            has details of filters to be applied.
     * @return the page of found {@link DistributionSet}
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Page<DistributionSet> findByDistributionSetFilter(@NotNull Pageable pageable,
            @NotNull DistributionSetFilter distributionSetFilter);

    /**
     * retrieves {@link DistributionSet}s by filtering on the given parameters.
     *
     * @param pageable
     *            page parameter
     * @param tagId
     *            of the tag the DS are assigned to
     * @return the page of found {@link DistributionSet}
     * 
     * @throws RSQLParameterUnsupportedFieldException
     *             if a field in the RSQL string is used but not provided by the
     *             given {@code fieldNameProvider}
     * @throws RSQLParameterSyntaxException
     *             if the RSQL syntax is wrong
     * 
     * @throws EntityNotFoundException
     *             of distribution set tag with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<DistributionSet> findByTag(@NotNull Pageable pageable, @NotNull Long tagId);
=======
    Page<DistributionSet> findByTag(@NotNull Pageable pageable, long tagId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * retrieves {@link DistributionSet}s by filtering on the given parameters.
     *
     * @param pageable
     *            page parameter
     * @param rsqlParam
     *            rsql query string
     * @param tagId
     *            of the tag the DS are assigned to
     * @return the page of found {@link DistributionSet}
     * 
     * @throws EntityNotFoundException
     *             of distribution set tag with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<DistributionSet> findByRsqlAndTag(@NotNull Pageable pageable, @NotNull String rsqlParam, @NotNull Long tagId);
=======
    Page<DistributionSet> findByRsqlAndTag(@NotNull Pageable pageable, @NotNull String rsqlParam, long tagId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds a single distribution set meta data by its id.
     *
     * @param setId
     *            of the {@link DistributionSet}
     * @param key
     *            of the meta data element
     * @return the found DistributionSetMetadata
     * 
     * @throws EntityNotFoundException
     *             is set with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Optional<DistributionSetMetadata> getMetaDataByDistributionSetId(@NotNull Long setId, @NotEmpty String key);
=======
    Optional<DistributionSetMetadata> getMetaDataByDistributionSetId(long setId, @NotEmpty String key);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Checks if a {@link DistributionSet} is currently in use by a target in
     * the repository.
     *
     * @param setId
     *            to check
     * 
     * @return <code>true</code> if in use
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    boolean isInUse(@NotNull Long setId);
=======
    boolean isInUse(long setId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Toggles {@link DistributionSetTag} assignment to given
     * {@link DistributionSet}s by means that if some (or all) of the targets in
     * the list have the {@link Tag} not yet assigned, they will be. If all of
     * theme have the tag already assigned they will be removed instead.
     *
     * @param setIds
     *            to toggle for
     * @param tagName
     *            to toggle
     * @return {@link DistributionSetTagAssignmentResult} with all meta data of
     *         the assignment outcome.
     * 
     * @throws EntityNotFoundException
     *             if given tag does not exist or (at least one) module
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
    DistributionSetTagAssignmentResult toggleTagAssignment(@NotEmpty Collection<Long> setIds, @NotNull String tagName);

    /**
     * Unassigns a {@link SoftwareModule} form an existing
     * {@link DistributionSet}.
     *
     * @param setId
     *            to get unassigned form
     * @param moduleId
     *            to be unassigned
     * @return the updated {@link DistributionSet}.
     * 
     * @throws EntityNotFoundException
     *             if given module or DS does not exist
     * 
     * @throws EntityReadOnlyException
     *             if use tries to change the {@link DistributionSet} s while
     *             the DS is already in use.
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSet unassignSoftwareModule(@NotNull Long setId, @NotNull Long moduleId);
=======
    DistributionSet unassignSoftwareModule(long setId, long moduleId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Unassign a {@link DistributionSetTag} assignment to given
     * {@link DistributionSet}.
     *
     * @param setId
     *            to unassign for
     * @param tagId
     *            to unassign
     * @return the unassigned ds or <null> if no ds is unassigned
     * 
     * @throws EntityNotFoundException
     *             if set or tag with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSet unAssignTag(@NotNull Long setId, @NotNull Long tagId);
=======
    DistributionSet unAssignTag(long setId, long tagId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * updates a distribution set meta data value if corresponding entry exists.
     *
     * @param setId
     *            {@link DistributionSet} of the meta data entry to be updated
     * @param metadata
     *            meta data entry to be updated
     * @return the updated meta data entry
     * 
     * @throws EntityNotFoundException
     *             in case the meta data entry does not exists and cannot be
     *             updated
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    DistributionSetMetadata updateMetaData(@NotNull Long setId, @NotNull MetaData metadata);
=======
    DistributionSetMetadata updateMetaData(long setId, @NotNull MetaData metadata);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Count all {@link DistributionSet}s in the repository that are not marked
     * as deleted.
     * 
     * @param typeId
     *            to look for
     *
     * @return number of {@link DistributionSet}s
     * 
     * @throws EntityNotFoundException
     *             if type with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    long countByTypeId(@NotNull Long typeId);
=======
    long countByTypeId(long typeId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

}
