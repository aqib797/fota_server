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
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import javax.validation.constraints.NotNull;

import org.eclipse.hawkbit.im.authentication.SpPermission.SpringEvalExpressions;
import org.eclipse.hawkbit.repository.builder.SoftwareModuleCreate;
import org.eclipse.hawkbit.repository.builder.SoftwareModuleMetadataCreate;
import org.eclipse.hawkbit.repository.builder.SoftwareModuleMetadataUpdate;
import org.eclipse.hawkbit.repository.builder.SoftwareModuleUpdate;
import org.eclipse.hawkbit.repository.exception.EntityAlreadyExistsException;
import org.eclipse.hawkbit.repository.exception.EntityNotFoundException;
import org.eclipse.hawkbit.repository.exception.RSQLParameterSyntaxException;
import org.eclipse.hawkbit.repository.exception.RSQLParameterUnsupportedFieldException;
import org.eclipse.hawkbit.repository.model.AssignedSoftwareModule;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.SoftwareModule;
import org.eclipse.hawkbit.repository.model.SoftwareModuleMetadata;
import org.eclipse.hawkbit.repository.model.SoftwareModuleType;
<<<<<<< HEAD
import org.hibernate.validator.constraints.NotEmpty;
=======
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Service for managing {@link SoftwareModule}s.
 *
 */
public interface SoftwareModuleManagement
        extends RepositoryManagement<SoftwareModule, SoftwareModuleCreate, SoftwareModuleUpdate> {

    /**
     * Counts {@link SoftwareModule}s with given
     * {@link SoftwareModule#getName()} or {@link SoftwareModule#getVersion()}
     * and {@link SoftwareModule#getType()} that are not marked as deleted.
     *
     * @param searchText
     *            to search for in name and version
     * @param typeId
     *            to filter the result by type
     * @return number of found {@link SoftwareModule}s
     * 
     * @throws EntityNotFoundException
     *             if software module type with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    long countByTextAndType(String searchText, Long typeId);

    /**
     * creates a list of software module meta data entries.
     * 
     * @param metadata
     *            the meta data entries to create
     * @return the updated or created software module meta data entries
     * @throws EntityAlreadyExistsException
     *             in case one of the meta data entry already exists for the
     *             specific key
     * @throws EntityNotFoundException
     *             if software module with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    List<SoftwareModuleMetadata> createMetaData(@NotNull Collection<SoftwareModuleMetadataCreate> metadata);
=======
    List<SoftwareModuleMetadata> createMetaData(@NotNull @Valid Collection<SoftwareModuleMetadataCreate> metadata);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * creates or updates a single software module meta data entry.
     * 
     * @param metadata
     *            the meta data entry to create
     * @return the updated or created software module meta data entry
     * @throws EntityAlreadyExistsException
     *             in case the meta data entry already exists for the specific
     *             key
     * @throws EntityNotFoundException
     *             if software module with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    SoftwareModuleMetadata createMetaData(@NotNull SoftwareModuleMetadataCreate metadata);
=======
    SoftwareModuleMetadata createMetaData(@NotNull @Valid SoftwareModuleMetadataCreate metadata);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * deletes a software module meta data entry.
     *
     * @param moduleId
     *            where meta data has to be deleted
     * @param key
     *            of the metda data element
     * 
     * @throws EntityNotFoundException
     *             of module or metadata entry does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    void deleteMetaData(@NotNull Long moduleId, @NotEmpty String key);

    /**
=======
    void deleteMetaData(long moduleId, @NotEmpty String key);

    /**
     * returns all modules assigned to given {@link DistributionSet}.
     * 
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
     * @param pageable
     *            the page request to page the result set
     * @param setId
     *            to search for
     * @return all {@link SoftwareModule}s that are assigned to given
     *         {@link DistributionSet}.
     * 
     * @throws EntityNotFoundException
     *             if distribution set with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<SoftwareModule> findByAssignedTo(@NotNull Pageable pageable, @NotNull Long setId);
=======
    Page<SoftwareModule> findByAssignedTo(@NotNull Pageable pageable, long setId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Filter {@link SoftwareModule}s with given
     * {@link SoftwareModule#getName()} or {@link SoftwareModule#getVersion()}
     * and {@link SoftwareModule#getType()} that are not marked as deleted.
     *
     * @param pageable
     *            page parameter
     * @param searchText
     *            to be filtered as "like" on {@link SoftwareModule#getName()}
     * @param typeId
     *            to be filtered as "like" on {@link SoftwareModule#getType()}
     * @return the page of found {@link SoftwareModule}
     * 
     * @throws EntityNotFoundException
     *             if given software module type does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Slice<SoftwareModule> findByTextAndType(@NotNull Pageable pageable, String searchText, Long typeId);

    /**
     * retrieves {@link SoftwareModule} by their name AND version AND type..
     *
     * @param name
     *            of the {@link SoftwareModule}
     * @param version
     *            of the {@link SoftwareModule}
     * @param typeId
     *            of the {@link SoftwareModule}
     * @return the found {@link SoftwareModule}
     * 
     * @throws EntityNotFoundException
     *             if software module type with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Optional<SoftwareModule> getByNameAndVersionAndType(@NotEmpty String name, @NotEmpty String version,
            @NotNull Long typeId);
=======
    Optional<SoftwareModule> getByNameAndVersionAndType(@NotEmpty String name, @NotEmpty String version, long typeId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds a single software module meta data by its id.
     *
     * @param moduleId
     *            where meta data has to be found
     * @param key
     *            of the meta data element
     * @return the found SoftwareModuleMetadata or {@code null} if not exits
     * 
     * @throws EntityNotFoundException
     *             is module with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Optional<SoftwareModuleMetadata> getMetaDataBySoftwareModuleId(@NotNull Long moduleId, @NotEmpty String key);
=======
    Optional<SoftwareModuleMetadata> getMetaDataBySoftwareModuleId(long moduleId, @NotEmpty String key);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds all meta data by the given software module id.
     * 
     * @param pageable
     *            the page request to page the result
     * @param moduleId
     *            the software module id to retrieve the meta data from
     *
     * @return a paged result of all meta data entries for a given software
     *         module id
     * 
     * @throws EntityNotFoundException
     *             if software module with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<SoftwareModuleMetadata> findMetaDataBySoftwareModuleId(@NotNull Pageable pageable, @NotNull Long moduleId);
=======
    Page<SoftwareModuleMetadata> findMetaDataBySoftwareModuleId(@NotNull Pageable pageable, long moduleId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds all meta data by the given software module id where
     * {@link SoftwareModuleMetadata#isTargetVisible()}.
     * 
     * @param pageable
     *            the page request to page the result
     * @param moduleId
     *            the software module id to retrieve the meta data from
     *
     * @return a paged result of all meta data entries for a given software
     *         module id
     * 
     * @throws EntityNotFoundException
     *             if software module with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Page<SoftwareModuleMetadata> findMetaDataBySoftwareModuleIdAndTargetVisible(@NotNull Pageable pageable,
<<<<<<< HEAD
            @NotNull Long moduleId);
=======
            long moduleId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * finds all meta data by the given software module id.
     * 
     * @param pageable
     *            the page request to page the result
     * @param moduleId
     *            the software module id to retrieve the meta data from
     * @param rsqlParam
     *            filter definition in RSQL syntax
     *
     * @return a paged result of all meta data entries for a given software
     *         module id
     * 
     * @throws RSQLParameterUnsupportedFieldException
     *             if a field in the RSQL string is used but not provided by the
     *             given {@code fieldNameProvider}
     * @throws RSQLParameterSyntaxException
     *             if the RSQL syntax is wrong
     * @throws EntityNotFoundException
     *             if software module with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Page<SoftwareModuleMetadata> findMetaDataByRsql(@NotNull Pageable pageable, @NotNull Long moduleId,
=======
    Page<SoftwareModuleMetadata> findMetaDataByRsql(@NotNull Pageable pageable, long moduleId,
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
            @NotNull String rsqlParam);

    /**
     * Filter {@link SoftwareModule}s with given
     * {@link SoftwareModule#getName()} or {@link SoftwareModule#getVersion()}
     * search text and {@link SoftwareModule#getType()} that are not marked as
     * deleted and sort them by means of given distribution set related modules
     * on top of the list.
     * 
     * After that the modules are sorted by {@link SoftwareModule#getName()} and
     * {@link SoftwareModule#getVersion()} in ascending order.
     *
     * @param pageable
     *            page parameter
     * @param orderByDistributionId
     *            the ID of distribution set to be ordered on top
     * @param searchText
     *            filtered as "like" on {@link SoftwareModule#getName()}
     * @param typeId
     *            filtered as "equal" on {@link SoftwareModule#getType()}
     * @return the page of found {@link SoftwareModule}
     * 
     * @throws EntityNotFoundException
     *             if given software module type does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
    Slice<AssignedSoftwareModule> findAllOrderBySetAssignmentAndModuleNameAscModuleVersionAsc(
<<<<<<< HEAD
            @NotNull Pageable pageable, @NotNull Long orderByDistributionId, String searchText, Long typeId);
=======
            @NotNull Pageable pageable, long orderByDistributionId, String searchText, Long typeId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * retrieves the {@link SoftwareModule}s by their {@link SoftwareModuleType}
     * .
     *
     * @param pageable
     *            page parameters
     * @param typeId
     *            to be filtered on
     * @return the found {@link SoftwareModule}s
     * 
     * @throws EntityNotFoundException
     *             if software module type with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_READ_REPOSITORY)
<<<<<<< HEAD
    Slice<SoftwareModule> findByType(@NotNull Pageable pageable, @NotNull Long typeId);
=======
    Slice<SoftwareModule> findByType(@NotNull Pageable pageable, long typeId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * updates a distribution set meta data value if corresponding entry exists.
     * 
     * @param update
     *            the meta data entry to be updated
     * 
     * @return the updated meta data entry
     * 
     * @throws EntityNotFoundException
     *             in case the meta data entry does not exists and cannot be
     *             updated
     */
    @PreAuthorize(SpringEvalExpressions.HAS_AUTH_UPDATE_REPOSITORY)
<<<<<<< HEAD
    SoftwareModuleMetadata updateMetaData(@NotNull SoftwareModuleMetadataUpdate update);
=======
    SoftwareModuleMetadata updateMetaData(@NotNull @Valid SoftwareModuleMetadataUpdate update);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
}
