/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
<<<<<<< HEAD
=======
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import javax.validation.constraints.NotNull;

import org.eclipse.hawkbit.im.authentication.SpPermission.SpringEvalExpressions;
import org.eclipse.hawkbit.repository.builder.ActionStatusCreate;
import org.eclipse.hawkbit.repository.exception.EntityAlreadyExistsException;
import org.eclipse.hawkbit.repository.exception.EntityNotFoundException;
import org.eclipse.hawkbit.repository.exception.QuotaExceededException;
import org.eclipse.hawkbit.repository.model.Action;
import org.eclipse.hawkbit.repository.model.Action.Status;
import org.eclipse.hawkbit.repository.model.ActionStatus;
import org.eclipse.hawkbit.repository.model.SoftwareModule;
import org.eclipse.hawkbit.repository.model.SoftwareModuleMetadata;
import org.eclipse.hawkbit.repository.model.Target;
import org.eclipse.hawkbit.repository.model.TargetUpdateStatus;
import org.eclipse.hawkbit.tenancy.configuration.TenantConfigurationProperties.TenantConfigurationKey;
<<<<<<< HEAD
import org.hibernate.validator.constraints.NotEmpty;
=======
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Service layer for all operations of the DDI API (with access permissions only
 * for the controller).
 *
 */
public interface ControllerManagement {

    /**
     * Adds an {@link ActionStatus} for a cancel {@link Action} including
     * potential state changes for the target and the {@link Action} itself.
     * 
     * @param create
     *            to be added
     * @return the updated {@link Action}
     * 
     * @throws EntityAlreadyExistsException
     *             if a given entity already exists
     * 
     * @throws QuotaExceededException
     *             if more than the allowed number of status entries or messages
     *             per entry are inserted
     * @throws EntityNotFoundException
     *             if given action does not exist
     * @throws ConstraintViolationException
     *             if fields are not filled as specified. Check
     *             {@link ActionStatusCreate} for field constraints.
     * 
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Action addCancelActionStatus(@NotNull ActionStatusCreate create);
=======
    Action addCancelActionStatus(@NotNull @Valid ActionStatusCreate create);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Retrieves assigned {@link SoftwareModule} of a target.
     * 
     * @param moduleId
     *            of the {@link SoftwareModule}
     * @return {@link SoftwareModule} identified by ID
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Optional<SoftwareModule> getSoftwareModule(@NotNull Long moduleId);
=======
    Optional<SoftwareModule> getSoftwareModule(long moduleId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Retrieves {@link SoftwareModuleMetadata} where
     * {@link SoftwareModuleMetadata#isTargetVisible()}.
     * 
     * @param moduleId
     *            of the {@link SoftwareModule}
     * @return list of {@link SoftwareModuleMetadata} with maximum size of
     *         {@link RepositoryConstants#MAX_META_DATA_COUNT}
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
    Map<Long, List<SoftwareModuleMetadata>> findTargetVisibleMetaDataBySoftwareModuleId(
            @NotNull Collection<Long> moduleId);

    /**
     * Simple addition of a new {@link ActionStatus} entry to the {@link Action}
     * . No state changes.
     * 
     * @param create
     *            to add to the action
     * 
     * @return created {@link ActionStatus} entity
     * 
     * @throws QuotaExceededException
     *             if more than the allowed number of status entries or messages
     *             per entry are inserted
     * @throws EntityNotFoundException
     *             if given action does not exist
     * @throws ConstraintViolationException
     *             if fields are not filled as specified. Check
     *             {@link ActionStatusCreate} for field constraints.
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    ActionStatus addInformationalActionStatus(@NotNull ActionStatusCreate create);
=======
    ActionStatus addInformationalActionStatus(@NotNull @Valid ActionStatusCreate create);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Adds an {@link ActionStatus} entry for an update {@link Action} including
     * potential state changes for the target and the {@link Action} itself.
     *
     * @param create
     *            to be added
     * @return the updated {@link Action}
     *
     * @throws EntityAlreadyExistsException
     *             if a given entity already exists
     * @throws QuotaExceededException
     *             if more than the allowed number of status entries or messages
     *             per entry are inserted
     * @throws EntityNotFoundException
     *             if action status not exist
     * @throws ConstraintViolationException
     *             if fields are not filled as specified. Check
     *             {@link ActionStatusCreate} for field constraints.
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Action addUpdateActionStatus(@NotNull ActionStatusCreate create);
=======
    Action addUpdateActionStatus(@NotNull @Valid ActionStatusCreate create);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Retrieves oldest {@link Action} that is active and assigned to a
     * {@link Target}.
     * 
     * For performance reasons this method does not throw
     * {@link EntityNotFoundException} in case target with given controlelrId
     * does not exist but will return an {@link Optional#empty()} instead.
     *
     * @param controllerId
     *            identifies the target to retrieve the actions from
     * @return a list of actions assigned to given target which are active
     * 
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Optional<Action> findOldestActiveActionByTarget(@NotNull String controllerId);
=======
    Optional<Action> findOldestActiveActionByTarget(@NotEmpty String controllerId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Get the {@link Action} entity for given actionId with all lazy
     * attributes.
     *
     * @param actionId
     *            to be id of the action
     * @return the corresponding {@link Action}
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Optional<Action> findActionWithDetails(@NotNull Long actionId);
=======
    Optional<Action> findActionWithDetails(long actionId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Retrieves all the {@link ActionStatus} entries of the given
     * {@link Action}.
     *
     * @param pageReq
     *            pagination parameter
     * @param actionId
     *            to be filtered on
     * @return the corresponding {@link Page} of {@link ActionStatus}
     * 
     * @throws EntityNotFoundException
     *             if action with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Page<ActionStatus> findActionStatusByAction(@NotNull Pageable pageReq, @NotNull Long actionId);
=======
    Page<ActionStatus> findActionStatusByAction(@NotNull Pageable pageReq, long actionId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Register new target in the repository (plug-and-play) and in case it
     * already exists updates {@link Target#getAddress()} and
     * {@link Target#getLastTargetQuery()} and switches if
     * {@link TargetUpdateStatus#UNKNOWN} to
     * {@link TargetUpdateStatus#REGISTERED}.
     *
     * @param controllerId
     *            reference
     * @param address
     *            the client IP address of the target, might be {@code null}
     * @return target reference
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
    Target findOrRegisterTargetIfItDoesNotexist(@NotEmpty String controllerId, @NotNull URI address);

    /**
     * Retrieves last {@link Action} for a download of an artifact of given
     * module and target if exists and is not canceled.
     *
     * @param controllerId
     *            to look for
     * @param moduleId
     *            of the the {@link SoftwareModule} that should be assigned to
     *            the target
     * @return last {@link Action} for given combination
     * 
     * @throws EntityNotFoundException
     *             if target with given ID does not exist
     *
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Optional<Action> getActionForDownloadByTargetAndSoftwareModule(@NotEmpty String controllerId,
            @NotNull Long moduleId);
=======
    Optional<Action> getActionForDownloadByTargetAndSoftwareModule(@NotEmpty String controllerId, long moduleId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * @return current {@link TenantConfigurationKey#POLLING_TIME_INTERVAL}.
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
    String getPollingTime();

    /**
     * Checks if a given target has currently or has even been assigned to the
     * given artifact through the action history list. This can e.g. indicate if
     * a target is allowed to download a given artifact because it has currently
     * assigned or had ever been assigned to the target and so it's visible to a
     * specific target e.g. for downloading.
     * 
     * @param controllerId
     *            the ID of the target to check
     * @param sha1Hash
     *            of the artifact to verify if the given target had even been
     *            assigned to
     * @return {@code true} if the given target has currently or had ever a
     *         relation to the given artifact through the action history,
     *         otherwise {@code false}
     * 
     * @throws EntityNotFoundException
     *             if target with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
    boolean hasTargetArtifactAssigned(@NotEmpty String controllerId, @NotEmpty String sha1Hash);

    /**
     * Checks if a given target has currently or has even been assigned to the
     * given artifact through the action history list. This can e.g. indicate if
     * a target is allowed to download a given artifact because it has currently
     * assigned or had ever been assigned to the target and so it's visible to a
     * specific target e.g. for downloading.
     * 
     * @param targetId
     *            the ID of the target to check
     * @param sha1Hash
     *            of the artifact to verify if the given target had even been
     *            assigned to
     * @return {@code true} if the given target has currently or had ever a
     *         relation to the given artifact through the action history,
     *         otherwise {@code false}
     * 
     * @throws EntityNotFoundException
     *             if target with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    boolean hasTargetArtifactAssigned(@NotNull Long targetId, @NotEmpty String sha1Hash);
=======
    boolean hasTargetArtifactAssigned(long targetId, @NotEmpty String sha1Hash);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Registers retrieved status for given {@link Target} and {@link Action} if
     * it does not exist yet.
     *
     * @param actionId
     *            to the handle status for
     * @param message
     *            for the status
     * @return the update action in case the status has been changed to
     *         {@link Status#RETRIEVED}
     * 
     * @throws EntityNotFoundException
     *             if action with given ID does not exist
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    Action registerRetrieved(@NotNull Long actionId, String message);
=======
    Action registerRetrieved(long actionId, String message);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Updates attributes of the controller.
     *
     * @param controllerId
     *            to update
     * @param attributes
     *            to insert
     *
     * @return updated {@link Target}
     *
     * @throws EntityNotFoundException
     *             if target that has to be updated could not be found
     * @throws QuotaExceededException
     *             if maximum number of attribzes per target is exceeded
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
    Target updateControllerAttributes(@NotEmpty String controllerId, @NotNull Map<String, String> attributes);

    /**
     * Finds {@link Target} based on given controller ID returns found Target
     * without details, i.e. NO {@link Target#getTags()} and
     * {@link Target#getActions()} possible.
     *
     * @param controllerId
     *            to look for.
     * @return {@link Target} or {@code null} if it does not exist
     * @see Target#getControllerId()
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER + SpringEvalExpressions.HAS_AUTH_OR
            + SpringEvalExpressions.IS_SYSTEM_CODE)
    Optional<Target> getByControllerId(@NotEmpty String controllerId);

    /**
     * Finds {@link Target} based on given ID returns found Target without
     * details, i.e. NO {@link Target#getTags()} and {@link Target#getActions()}
     * possible.
     *
     * @param targetId
     *            to look for.
     * @return {@link Target} or {@code null} if it does not exist
     * @see Target#getId()
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER + SpringEvalExpressions.HAS_AUTH_OR
            + SpringEvalExpressions.IS_SYSTEM_CODE)
<<<<<<< HEAD
    Optional<Target> get(@NotNull Long targetId);
=======
    Optional<Target> get(long targetId);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    /**
     * Retrieves the specified number of messages from action history of the
     * given {@link Action} based on messageCount. Regardless of the value of
     * messageCount, in order to restrict resource utilization by controllers,
     * maximum number of messages that are retrieved from database is limited by
     * {@link RepositoryConstants#MAX_ACTION_HISTORY_MSG_COUNT}. messageCount <
     * 0, retrieves the maximum allowed number of action status messages from
     * history; messageCount = 0, does not retrieve any message; and
     * messageCount > 0, retrieves the specified number of messages, limited by
     * maximum allowed number. A controller sends the feedback for an
     * {@link ActionStatus} as a list of messages; while returning the messages,
     * even though the messages from multiple {@link ActionStatus} are retrieved
     * in descending order by the reported time
     * ({@link ActionStatus#getOccurredAt()}), i.e. latest ActionStatus first,
     * the sub-ordering of messages from within single {@link ActionStatus} is
     * unspecified.
     *
     * @param actionId
     *            to be filtered on
     * @param messageCount
     *            is the number of messages to return from history
     *
     * @return action history.
     */
    @PreAuthorize(SpringEvalExpressions.IS_CONTROLLER)
<<<<<<< HEAD
    List<String> getActionHistoryMessages(@NotNull Long actionId, final int messageCount);
=======
    List<String> getActionHistoryMessages(long actionId, int messageCount);
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
}
