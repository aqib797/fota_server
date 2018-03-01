/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.rest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.DistributionSetType;
import org.eclipse.hawkbit.repository.model.RolloutGroup;
import org.eclipse.hawkbit.repository.model.RolloutGroupConditions;
import org.eclipse.hawkbit.repository.model.SoftwareModule;
import org.eclipse.hawkbit.repository.model.SoftwareModuleType;
import org.eclipse.hawkbit.repository.model.Tag;
import org.eclipse.hawkbit.repository.model.Target;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Builder class for building certain json strings.
 *
 *
 *
 */
public abstract class JsonBuilder {

    public static String ids(final Collection<Long> ids) {
        final JSONArray list = new JSONArray();
        for (final Long smID : ids) {
            list.put(new JSONObject().put("id", smID));
        }

        return list.toString();
    }

    public static String controllerIds(final Collection<String> ids) {
        final JSONArray list = new JSONArray();
        for (final String smID : ids) {
            list.put(new JSONObject().put("controllerId", smID));
        }

        return list.toString();
    }

    public static String tags(final List<Tag> tags) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final Tag tag : tags) {
            createTagLine(builder, tag);

            if (++i < tags.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    private static void createTagLine(final StringBuilder builder, final Tag tag) {
        builder.append(new JSONObject().put("name", tag.getName()).put("description", tag.getDescription())
                .put("colour", tag.getColour()).put("createdAt", "0").put("updatedAt", "0")
                .put("createdBy", "fghdfkjghdfkjh").put("updatedBy", "fghdfkjghdfkjh").toString());
    }

    public static String tag(final Tag tag) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        createTagLine(builder, tag);

        return builder.toString();

    }

    public static String softwareModules(final List<SoftwareModule> modules) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final SoftwareModule module : modules) {
            builder.append(new JSONObject().put("name", module.getName()).put("description", module.getDescription())
                    .put("type", module.getType().getKey()).put("id", Long.MAX_VALUE).put("vendor", module.getVendor())
                    .put("version", module.getVersion()).put("createdAt", "0").put("updatedAt", "0")
                    .put("createdBy", "fghdfkjghdfkjh").put("updatedBy", "fghdfkjghdfkjh").toString());

            if (++i < modules.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    public static String softwareModulesCreatableFieldsOnly(final List<SoftwareModule> modules) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final SoftwareModule module : modules) {
            builder.append(new JSONObject().put("name", module.getName()).put("description", module.getDescription())
                    .put("type", module.getType().getKey()).put("vendor", module.getVendor())
                    .put("version", module.getVersion()).toString());

            if (++i < modules.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    public static String softwareModuleUpdatableFieldsOnly(final SoftwareModule module) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append(new JSONObject().put("description", module.getDescription()).put("vendor", module.getVendor())
                .toString());

        return builder.toString();

    }

    public static String softwareModuleTypes(final List<SoftwareModuleType> types) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final SoftwareModuleType module : types) {
            builder.append(new JSONObject().put("name", module.getName()).put("description", module.getDescription())
                    .put("id", Long.MAX_VALUE).put("key", module.getKey())
                    .put("maxAssignments", module.getMaxAssignments()).put("createdAt", "0").put("updatedAt", "0")
                    .put("createdBy", "fghdfkjghdfkjh").put("updatedBy", "fghdfkjghdfkjh").toString());

            if (++i < types.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    public static String softwareModuleTypesCreatableFieldsOnly(final List<SoftwareModuleType> types)
            throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final SoftwareModuleType module : types) {
            builder.append(new JSONObject().put("name", module.getName()).put("description", module.getDescription())
                    .put("key", module.getKey()).put("maxAssignments", module.getMaxAssignments()).toString());

            if (++i < types.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    /**
     * builds a json string for the feedback for the execution "proceeding".
     *
     * @param id
     *            of the Action feedback refers to
     * @return the built string
     * @throws JSONException
     */
    public static String deploymentActionInProgressFeedback(final String id) throws JSONException {
        return deploymentActionFeedback(id, "proceeding");
    }

    /**
     * builds a certain json string for a action feedback.
     *
     * @param id
     *            of the action the feedback refers to
     * @param execution
     *            see ExecutionStatus
     * @return the build json string
     * @throws JSONException
     */
    public static String deploymentActionFeedback(final String id, final String execution) throws JSONException {
<<<<<<< HEAD
        return deploymentActionFeedback(id, execution, "none", Arrays.asList(RandomStringUtils.randomAscii(1000)));
=======
        return deploymentActionFeedback(id, execution, "none",
                Arrays.asList(RandomStringUtils.randomAlphanumeric(1000)));
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    }

    public static String deploymentActionFeedback(final String id, final String execution, final String message)
            throws JSONException {
        return deploymentActionFeedback(id, execution, "none", Arrays.asList(message));

    }

    public static String deploymentActionFeedback(final String id, final String execution, final String finished,
            final String message) throws JSONException {
        return deploymentActionFeedback(id, execution, finished, Arrays.asList(message));
    }

    public static String deploymentActionFeedback(final String id, final String execution, final String finished,
            final Collection<String> messages) throws JSONException {

        return new JSONObject().put("id", id).put("time", "20140511T121314")
                .put("status",
                        new JSONObject().put("execution", execution)
                                .put("result",
                                        new JSONObject().put("finished", finished).put("progress",
                                                new JSONObject().put("cnt", 2).put("of", 5)))
                                .put("details", messages))
                .toString();
    }

    /**
     * Build an invalid request body with missing result for feedback message.
     * 
     * @param id
     *            id of the action
     * @param execution
     *            the execution
     * @param message
     *            the message
     * @return a invalid request body
     * @throws JSONException
     */
    public static String missingResultInFeedback(final String id, final String execution, final String message)
            throws JSONException {
        final List<String> messages = new ArrayList<>();
        messages.add(message);
        return new JSONObject().put("id", id).put("time", "20140511T121314")
                .put("status", new JSONObject().put("execution", execution).put("details", messages)).toString();
    }

    /**
     * Build an invalid request body with missing finished result for feedback
     * message.
     * 
     * @param id
     *            id of the action
     * @param execution
     *            the execution
     * @param message
     *            the message
     * @return a invalid request body
     * @throws JSONException
     */
    public static String missingFinishedResultInFeedback(final String id, final String execution, final String message)
            throws JSONException {
        final List<String> messages = new ArrayList<>();
        messages.add(message);
        return new JSONObject().put("id", id).put("time", "20140511T121314").put("status",
                new JSONObject().put("execution", execution).put("result", new JSONObject()).put("details", messages))
                .toString();
    }

    /**
     * @param types
     * @return
     */
    public static String distributionSetTypes(final List<DistributionSetType> types) {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final DistributionSetType module : types) {

            final JSONArray osmTypes = new JSONArray();
            module.getOptionalModuleTypes().forEach(smt -> osmTypes.put(new JSONObject().put("id", smt.getId())));

            final JSONArray msmTypes = new JSONArray();
            module.getMandatoryModuleTypes().forEach(smt -> msmTypes.put(new JSONObject().put("id", smt.getId())));

            builder.append(new JSONObject().put("name", module.getName()).put("description", module.getDescription())
                    .put("id", Long.MAX_VALUE).put("key", module.getKey()).put("createdAt", "0").put("updatedAt", "0")
                    .put("createdBy", "fghdfkjghdfkjh").put("optionalmodules", osmTypes)
                    .put("mandatorymodules", msmTypes).put("updatedBy", "fghdfkjghdfkjh").toString());

            if (++i < types.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public static String distributionSetTypesCreateValidFieldsOnly(final List<DistributionSetType> types) {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final DistributionSetType module : types) {

            final JSONArray osmTypes = new JSONArray();
            module.getOptionalModuleTypes().forEach(smt -> osmTypes.put(new JSONObject().put("id", smt.getId())));

            final JSONArray msmTypes = new JSONArray();
            module.getMandatoryModuleTypes().forEach(smt -> msmTypes.put(new JSONObject().put("id", smt.getId())));

            builder.append(new JSONObject().put("name", module.getName()).put("description", module.getDescription())
                    .put("key", module.getKey()).put("optionalmodules", osmTypes).put("mandatorymodules", msmTypes)
                    .toString());

            if (++i < types.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public static String distributionSets(final List<DistributionSet> sets) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final DistributionSet set : sets) {
            builder.append(distributionSet(set));

            if (++i < sets.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    public static String distributionSetsCreateValidFieldsOnly(final List<DistributionSet> sets) throws JSONException {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final DistributionSet set : sets) {
            builder.append(distributionSetCreateValidFieldsOnly(set));
            if (++i < sets.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();

    }

    public static String distributionSetCreateValidFieldsOnly(final DistributionSet set) throws JSONException {

        final List<JSONObject> modules = set.getModules().stream()
                .map(module -> new JSONObject().put("id", module.getId())).collect(Collectors.toList());

        return new JSONObject().put("name", set.getName()).put("description", set.getDescription())
                .put("type", set.getType() == null ? null : set.getType().getKey()).put("version", set.getVersion())
                .put("requiredMigrationStep", set.isRequiredMigrationStep()).put("modules", modules).toString();

    }

    public static String distributionSetUpdateValidFieldsOnly(final DistributionSet set) throws JSONException {

        final List<JSONObject> modules = set.getModules().stream()
                .map(module -> new JSONObject().put("id", module.getId())).collect(Collectors.toList());

        return new JSONObject().put("name", set.getName()).put("description", set.getDescription())
                .put("version", set.getVersion()).put("requiredMigrationStep", set.isRequiredMigrationStep())
                .toString();

    }

    public static String distributionSet(final DistributionSet set) throws JSONException {

        final List<JSONObject> modules = set.getModules().stream()
                .map(module -> new JSONObject().put("id", module.getId())).collect(Collectors.toList());

        return new JSONObject().put("name", set.getName()).put("description", set.getDescription())
                .put("type", set.getType() == null ? null : set.getType().getKey()).put("id", Long.MAX_VALUE)
                .put("version", set.getVersion()).put("createdAt", "0").put("updatedAt", "0")
                .put("createdBy", "fghdfkjghdfkjh").put("updatedBy", "fghdfkjghdfkjh")
                .put("requiredMigrationStep", set.isRequiredMigrationStep()).put("modules", modules).toString();

    }

    public static String targets(final List<Target> targets, final boolean withToken) {
        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        int i = 0;
        for (final Target target : targets) {
            final String address = target.getAddress() != null ? target.getAddress().toString() : null;

            final String token = withToken ? target.getSecurityToken() : null;

            builder.append(new JSONObject().put("controllerId", target.getControllerId())
                    .put("description", target.getDescription()).put("name", target.getName()).put("createdAt", "0")
                    .put("updatedAt", "0").put("createdBy", "fghdfkjghdfkjh").put("updatedBy", "fghdfkjghdfkjh")
                    .put("address", address).put("securityToken", token).toString());

            if (++i < targets.size()) {
                builder.append(",");
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public static String rollout(final String name, final String description, final int groupSize,
            final long distributionSetId, final String targetFilterQuery, final RolloutGroupConditions conditions) {
        return rollout(name, description, groupSize, distributionSetId, targetFilterQuery, conditions, null);
    }

    public static String rollout(final String name, final String description, final Integer groupSize,
            final long distributionSetId, final String targetFilterQuery, final RolloutGroupConditions conditions,
            final List<RolloutGroup> groups) {
        final JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("amountGroups", groupSize);
        json.put("distributionSetId", distributionSetId);
        json.put("targetFilterQuery", targetFilterQuery);

        if (conditions != null) {
            final JSONObject successCondition = new JSONObject();
            json.put("successCondition", successCondition);
            successCondition.put("condition", conditions.getSuccessCondition().toString());
            successCondition.put("expression", conditions.getSuccessConditionExp());

            final JSONObject successAction = new JSONObject();
            json.put("successAction", successAction);
            successAction.put("action", conditions.getSuccessAction().toString());
            successAction.put("expression", conditions.getSuccessActionExp());

            final JSONObject errorCondition = new JSONObject();
            json.put("errorCondition", errorCondition);
            errorCondition.put("condition", conditions.getErrorCondition().toString());
            errorCondition.put("expression", conditions.getErrorConditionExp());

            final JSONObject errorAction = new JSONObject();
            json.put("errorAction", errorAction);
            errorAction.put("action", conditions.getErrorAction().toString());
            errorAction.put("expression", conditions.getErrorActionExp());
        }

        if (groups != null) {
            final JSONArray jsonGroups = new JSONArray();

            for (final RolloutGroup group : groups) {
                final JSONObject jsonGroup = new JSONObject();
                jsonGroup.put("name", group.getName());
                jsonGroup.put("description", group.getDescription());
                jsonGroup.put("targetFilterQuery", group.getTargetFilterQuery());
                jsonGroup.put("targetPercentage", group.getTargetPercentage());

                if (group.getSuccessCondition() != null) {
                    final JSONObject successCondition = new JSONObject();
                    jsonGroup.put("successCondition", successCondition);
                    successCondition.put("condition", group.getSuccessCondition().toString());
                    successCondition.put("expression", group.getSuccessConditionExp());
                }
                if (group.getSuccessAction() != null) {
                    final JSONObject successAction = new JSONObject();
                    jsonGroup.put("successAction", successAction);
                    successAction.put("action", group.getSuccessAction().toString());
                    successAction.put("expression", group.getSuccessActionExp());
                }
                if (group.getErrorCondition() != null) {
                    final JSONObject errorCondition = new JSONObject();
                    jsonGroup.put("errorCondition", errorCondition);
                    errorCondition.put("condition", group.getErrorCondition().toString());
                    errorCondition.put("expression", group.getErrorConditionExp());
                }
                if (group.getErrorAction() != null) {
                    final JSONObject errorAction = new JSONObject();
                    jsonGroup.put("errorAction", errorAction);
                    errorAction.put("action", group.getErrorAction().toString());
                    errorAction.put("expression", group.getErrorActionExp());
                }

                jsonGroups.put(jsonGroup);
            }

            json.put("groups", jsonGroups);

        }

        return json.toString();
    }

    public static String cancelActionFeedback(final String id, final String execution) throws JSONException {
<<<<<<< HEAD
        return cancelActionFeedback(id, execution, RandomStringUtils.randomAscii(1000));
=======
        return cancelActionFeedback(id, execution, RandomStringUtils.randomAlphanumeric(1000));
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

    }

    public static String cancelActionFeedback(final String id, final String execution, final String message)
            throws JSONException {
        final List<String> messages = new ArrayList<>();
        messages.add(message);
        return new JSONObject().put("id", id).put("time", "20140511T121314")
                .put("status",
                        new JSONObject().put("execution", execution)
                                .put("result", new JSONObject().put("finished", "success")).put("details", messages))
                .toString();

    }

    public static String configData(final String id, final Map<String, String> attributes, final String execution)
            throws JSONException {
        return new JSONObject().put("id", id).put("time", "20140511T121314")
                .put("status",
                        new JSONObject().put("execution", execution)
                                .put("result", new JSONObject().put("finished", "success"))
                                .put("details", new ArrayList<String>()))
                .put("data", attributes).toString();

    }

}
