/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Condition;
import org.eclipse.hawkbit.repository.DistributionSetManagement;
import org.eclipse.hawkbit.repository.builder.DistributionSetCreate;
import org.eclipse.hawkbit.repository.event.remote.entity.DistributionSetCreatedEvent;
import org.eclipse.hawkbit.repository.event.remote.entity.DistributionSetTagCreatedEvent;
import org.eclipse.hawkbit.repository.event.remote.entity.DistributionSetUpdatedEvent;
import org.eclipse.hawkbit.repository.event.remote.entity.SoftwareModuleCreatedEvent;
import org.eclipse.hawkbit.repository.exception.EntityAlreadyExistsException;
import org.eclipse.hawkbit.repository.exception.EntityNotFoundException;
import org.eclipse.hawkbit.repository.exception.EntityReadOnlyException;
import org.eclipse.hawkbit.repository.exception.UnsupportedSoftwareModuleForThisDistributionSetException;
import org.eclipse.hawkbit.repository.jpa.model.JpaDistributionSet;
import org.eclipse.hawkbit.repository.jpa.model.JpaDistributionSetMetadata;
import org.eclipse.hawkbit.repository.model.Action.Status;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.DistributionSetAssignmentResult;
import org.eclipse.hawkbit.repository.model.DistributionSetFilter.DistributionSetFilterBuilder;
import org.eclipse.hawkbit.repository.model.DistributionSetMetadata;
import org.eclipse.hawkbit.repository.model.DistributionSetTag;
import org.eclipse.hawkbit.repository.model.DistributionSetType;
import org.eclipse.hawkbit.repository.model.SoftwareModule;
import org.eclipse.hawkbit.repository.model.Target;
import org.eclipse.hawkbit.repository.test.matcher.Expect;
import org.eclipse.hawkbit.repository.test.matcher.ExpectEvents;
import org.eclipse.hawkbit.repository.test.util.WithUser;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * {@link DistributionSetManagement} tests.
 *
 */
@Features("Component Tests - Repository")
@Stories("DistributionSet Management")
public class DistributionSetManagementTest extends AbstractJpaIntegrationTest {
<<<<<<< HEAD
=======

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    @Test
    @Description("Verifies that management get access react as specfied on calls for non existing entities by means "
            + "of Optional not present.")
    @ExpectEvents({ @Expect(type = DistributionSetCreatedEvent.class, count = 1),
            @Expect(type = SoftwareModuleCreatedEvent.class, count = 3) })
    public void nonExistingEntityAccessReturnsNotPresent() {
        final DistributionSet set = testdataFactory.createDistributionSet();
        assertThat(distributionSetManagement.get(NOT_EXIST_IDL)).isNotPresent();
        assertThat(distributionSetManagement.getWithDetails(NOT_EXIST_IDL)).isNotPresent();
        assertThat(distributionSetManagement.getByNameAndVersion(NOT_EXIST_ID, NOT_EXIST_ID)).isNotPresent();
        assertThat(distributionSetManagement.getMetaDataByDistributionSetId(set.getId(), NOT_EXIST_ID)).isNotPresent();
<<<<<<< HEAD

=======
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    }

    @Test
    @Description("Verifies that management queries react as specfied on calls for non existing entities "
            + " by means of throwing EntityNotFoundException.")
    @ExpectEvents({ @Expect(type = DistributionSetCreatedEvent.class, count = 1),
            @Expect(type = DistributionSetTagCreatedEvent.class, count = 1),
            @Expect(type = SoftwareModuleCreatedEvent.class, count = 4) })
    public void entityQueriesReferringToNotExistingEntitiesThrowsException() {
        final DistributionSet set = testdataFactory.createDistributionSet();
        final DistributionSetTag dsTag = testdataFactory.createDistributionSetTags(1).get(0);
        final SoftwareModule module = testdataFactory.createSoftwareModuleApp();

        verifyThrownExceptionBy(
                () -> distributionSetManagement.assignSoftwareModules(NOT_EXIST_IDL, Arrays.asList(module.getId())),
                "DistributionSet");
        verifyThrownExceptionBy(
                () -> distributionSetManagement.assignSoftwareModules(set.getId(), Arrays.asList(NOT_EXIST_IDL)),
                "SoftwareModule");

        verifyThrownExceptionBy(() -> distributionSetManagement.countByTypeId(NOT_EXIST_IDL), "DistributionSet");

        verifyThrownExceptionBy(() -> distributionSetManagement.unassignSoftwareModule(NOT_EXIST_IDL, module.getId()),
                "DistributionSet");
        verifyThrownExceptionBy(() -> distributionSetManagement.unassignSoftwareModule(set.getId(), NOT_EXIST_IDL),
                "SoftwareModule");

        verifyThrownExceptionBy(() -> distributionSetManagement.assignTag(Arrays.asList(set.getId()), NOT_EXIST_IDL),
                "DistributionSetTag");

        verifyThrownExceptionBy(() -> distributionSetManagement.assignTag(Arrays.asList(NOT_EXIST_IDL), dsTag.getId()),
                "DistributionSet");

        verifyThrownExceptionBy(() -> distributionSetManagement.findByTag(PAGE, NOT_EXIST_IDL), "DistributionSetTag");
        verifyThrownExceptionBy(() -> distributionSetManagement.findByRsqlAndTag(PAGE, "name==*", NOT_EXIST_IDL),
                "DistributionSetTag");

        verifyThrownExceptionBy(
                () -> distributionSetManagement.toggleTagAssignment(Arrays.asList(NOT_EXIST_IDL), dsTag.getName()),
                "DistributionSet");
        verifyThrownExceptionBy(
                () -> distributionSetManagement.toggleTagAssignment(Arrays.asList(set.getId()), NOT_EXIST_ID),
                "DistributionSetTag");

        verifyThrownExceptionBy(() -> distributionSetManagement.unAssignTag(set.getId(), NOT_EXIST_IDL),
                "DistributionSetTag");

        verifyThrownExceptionBy(() -> distributionSetManagement.unAssignTag(NOT_EXIST_IDL, dsTag.getId()),
                "DistributionSet");

        verifyThrownExceptionBy(
                () -> distributionSetManagement
                        .create(entityFactory.distributionSet().create().name("xxx").type(NOT_EXIST_ID)),
                "DistributionSetType");

        verifyThrownExceptionBy(() -> distributionSetManagement.createMetaData(NOT_EXIST_IDL,
                Arrays.asList(entityFactory.generateMetadata("123", "123"))), "DistributionSet");

        verifyThrownExceptionBy(() -> distributionSetManagement.delete(Arrays.asList(NOT_EXIST_IDL)),
                "DistributionSet");
        verifyThrownExceptionBy(() -> distributionSetManagement.delete(NOT_EXIST_IDL), "DistributionSet");
        verifyThrownExceptionBy(() -> distributionSetManagement.deleteMetaData(NOT_EXIST_IDL, "xxx"),
                "DistributionSet");
        verifyThrownExceptionBy(() -> distributionSetManagement.deleteMetaData(set.getId(), NOT_EXIST_ID),
                "DistributionSetMetadata");

        verifyThrownExceptionBy(() -> distributionSetManagement.getByAction(NOT_EXIST_IDL), "Action");

        verifyThrownExceptionBy(() -> distributionSetManagement.getMetaDataByDistributionSetId(NOT_EXIST_IDL, "xxx"),
                "DistributionSet");

        verifyThrownExceptionBy(() -> distributionSetManagement.findMetaDataByDistributionSetId(PAGE, NOT_EXIST_IDL),
                "DistributionSet");

        verifyThrownExceptionBy(
                () -> distributionSetManagement.findMetaDataByDistributionSetIdAndRsql(PAGE, NOT_EXIST_IDL, "name==*"),
                "DistributionSet");

        assertThatThrownBy(() -> distributionSetManagement.isInUse(NOT_EXIST_IDL))
                .isInstanceOf(EntityNotFoundException.class).hasMessageContaining(NOT_EXIST_ID)
                .hasMessageContaining("DistributionSet");

        verifyThrownExceptionBy(
                () -> distributionSetManagement.update(entityFactory.distributionSet().update(NOT_EXIST_IDL)),
                "DistributionSet");

        verifyThrownExceptionBy(() -> distributionSetManagement.updateMetaData(NOT_EXIST_IDL,
                entityFactory.generateMetadata("xxx", "xxx")), "DistributionSet");

        verifyThrownExceptionBy(() -> distributionSetManagement.updateMetaData(set.getId(),
                entityFactory.generateMetadata(NOT_EXIST_ID, "xxx")), "DistributionSetMetadata");
    }

    @Test
    @Description("Verify that a DistributionSet with invalid properties cannot be created or updated")
    @ExpectEvents({ @Expect(type = DistributionSetCreatedEvent.class, count = 1),
            @Expect(type = SoftwareModuleCreatedEvent.class, count = 3),
            @Expect(type = DistributionSetUpdatedEvent.class, count = 0) })
    public void createAndUpdateDistributionSetWithInvalidFields() {
        final DistributionSet set = testdataFactory.createDistributionSet();

        createAndUpdateDistributionSetWithInvalidDescription(set);
        createAndUpdateDistributionSetWithInvalidName(set);
        createAndUpdateDistributionSetWithInvalidVersion(set);
    }

    @Step
    private void createAndUpdateDistributionSetWithInvalidDescription(final DistributionSet set) {

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement.create(entityFactory.distributionSet().create().name("a")
                        .version("a").description(RandomStringUtils.randomAlphanumeric(513))))
                .as("entity with too long description should not be created");

        assertThatExceptionOfType(ConstraintViolationException.class)
<<<<<<< HEAD
=======
                .isThrownBy(() -> distributionSetManagement.create(
                        entityFactory.distributionSet().create().name("a").version("a").description(INVALID_TEXT_HTML)))
                .as("entity with invalid description should not be created");

        assertThatExceptionOfType(ConstraintViolationException.class)
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
                .isThrownBy(() -> distributionSetManagement.update(entityFactory.distributionSet().update(set.getId())
                        .description(RandomStringUtils.randomAlphanumeric(513))))
                .as("entity with too long description should not be updated");

<<<<<<< HEAD
=======
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement
                        .update(entityFactory.distributionSet().update(set.getId()).description(INVALID_TEXT_HTML)))
                .as("entity with invalid characters should not be updated");

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
    }

    @Step
    private void createAndUpdateDistributionSetWithInvalidName(final DistributionSet set) {

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement.create(entityFactory.distributionSet().create().version("a")
                        .name(RandomStringUtils.randomAlphanumeric(65))))
                .as("entity with too long name should not be created");

        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(
                () -> distributionSetManagement.create(entityFactory.distributionSet().create().version("a").name("")))
<<<<<<< HEAD
                .as("entity with too long name should not be created");
=======
                .as("entity with too short name should not be created");

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement
                        .create(entityFactory.distributionSet().create().version("a").name(INVALID_TEXT_HTML)))
                .as("entity with invalid characters in name should not be created");
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement.update(entityFactory.distributionSet().update(set.getId())
                        .name(RandomStringUtils.randomAlphanumeric(65))))
                .as("entity with too long name should not be updated");

<<<<<<< HEAD
=======
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement
                        .update(entityFactory.distributionSet().update(set.getId()).name(INVALID_TEXT_HTML)))
                .as("entity with invalid characters should not be updated");

>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(
                () -> distributionSetManagement.update(entityFactory.distributionSet().update(set.getId()).name("")))
                .as("entity with too short name should not be updated");

    }

    @Step
    private void createAndUpdateDistributionSetWithInvalidVersion(final DistributionSet set) {

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement.create(entityFactory.distributionSet().create().name("a")
                        .version(RandomStringUtils.randomAlphanumeric(65))))
                .as("entity with too long name should not be created");

        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(
                () -> distributionSetManagement.create(entityFactory.distributionSet().create().name("a").version("")))
                .as("entity with too long name should not be created");

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> distributionSetManagement.update(entityFactory.distributionSet().update(set.getId())
                        .version(RandomStringUtils.randomAlphanumeric(65))))
                .as("entity with too long name should not be updated");

        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(
                () -> distributionSetManagement.update(entityFactory.distributionSet().update(set.getId()).version("")))
                .as("entity with too short name should not be updated");

    }

    @Test
    @Description("Ensures that it is not possible to create a DS that already exists (unique constraint is on name,version for DS).")
    public void createDuplicateDistributionSetsFailsWithException() {
        testdataFactory.createDistributionSet("a");

        assertThatThrownBy(() -> testdataFactory.createDistributionSet("a"))
                .isInstanceOf(EntityAlreadyExistsException.class);
    }

    @Test
    @Description("Verifies that a DS is of default type if not specified explicitly at creation time.")
    public void createDistributionSetWithImplicitType() {
        final DistributionSet set = distributionSetManagement
                .create(entityFactory.distributionSet().create().name("newtypesoft").version("1"));

        assertThat(set.getType()).as("Type should be equal to default type of tenant")
                .isEqualTo(systemManagement.getTenantMetadata().getDefaultDsType());

    }

    @Test
    @Description("Verifies that a DS cannot be created if another DS with same name and version exists.")
    public void createDistributionSetWithDuplicateNameAndVersionFails() {
        distributionSetManagement.create(entityFactory.distributionSet().create().name("newtypesoft").version("1"));

        assertThatExceptionOfType(EntityAlreadyExistsException.class).isThrownBy(() -> distributionSetManagement
                .create(entityFactory.distributionSet().create().name("newtypesoft").version("1")));

    }

    @Test
    @Description("Verifies that multiple DS are of default type if not specified explicitly at creation time.")
    public void createMultipleDistributionSetsWithImplicitType() {

        final List<DistributionSetCreate> creates = Lists.newArrayListWithExpectedSize(10);
        for (int i = 0; i < 10; i++) {
            creates.add(entityFactory.distributionSet().create().name("newtypesoft" + i).version("1" + i));
        }

        final List<DistributionSet> sets = distributionSetManagement.create(creates);

        assertThat(sets).as("Type should be equal to default type of tenant").are(new Condition<DistributionSet>() {
            @Override
            public boolean matches(final DistributionSet value) {
                return value.getType().equals(systemManagement.getTenantMetadata().getDefaultDsType());
            }
        });

    }

    @Test
    @Description("Checks that metadata for a distribution set can be created.")
    public void createDistributionSetMetadata() {
        final String knownKey = "dsMetaKnownKey";
        final String knownValue = "dsMetaKnownValue";

        final DistributionSet ds = testdataFactory.createDistributionSet("testDs");

        final DistributionSetMetadata metadata = new JpaDistributionSetMetadata(knownKey, ds, knownValue);
        final JpaDistributionSetMetadata createdMetadata = (JpaDistributionSetMetadata) createDistributionSetMetadata(
                ds.getId(), metadata);

        assertThat(createdMetadata).isNotNull();
        assertThat(createdMetadata.getId().getKey()).isEqualTo(knownKey);
        assertThat(createdMetadata.getDistributionSet().getId()).isEqualTo(ds.getId());
        assertThat(createdMetadata.getValue()).isEqualTo(knownValue);
    }

    @Test
    @Description("Ensures that distribution sets can assigned and unassigned to a  distribution set tag.")
    public void assignAndUnassignDistributionSetToTag() {
        final List<Long> assignDS = Lists.newArrayListWithExpectedSize(4);
        for (int i = 0; i < 4; i++) {
            assignDS.add(testdataFactory.createDistributionSet("DS" + i, "1.0", Collections.emptyList()).getId());
        }

        final DistributionSetTag tag = distributionSetTagManagement.create(entityFactory.tag().create().name("Tag1"));

        final List<DistributionSet> assignedDS = distributionSetManagement.assignTag(assignDS, tag.getId());
        assertThat(assignedDS.size()).as("assigned ds has wrong size").isEqualTo(4);
        assignedDS.stream().map(c -> (JpaDistributionSet) c)
                .forEach(ds -> assertThat(ds.getTags().size()).as("ds has wrong tag size").isEqualTo(1));

        DistributionSetTag findDistributionSetTag = distributionSetTagManagement.getByName("Tag1").get();

        assertThat(assignedDS.size()).as("assigned ds has wrong size")
                .isEqualTo(distributionSetManagement.findByTag(PAGE, tag.getId()).getNumberOfElements());

        final JpaDistributionSet unAssignDS = (JpaDistributionSet) distributionSetManagement
                .unAssignTag(assignDS.get(0), findDistributionSetTag.getId());
        assertThat(unAssignDS.getId()).as("unassigned ds is wrong").isEqualTo(assignDS.get(0));
        assertThat(unAssignDS.getTags().size()).as("unassigned ds has wrong tag size").isEqualTo(0);
        findDistributionSetTag = distributionSetTagManagement.getByName("Tag1").get();
        assertThat(distributionSetManagement.findByTag(PAGE, tag.getId()).getNumberOfElements())
                .as("ds tag ds has wrong ds size").isEqualTo(3);

        assertThat(distributionSetManagement.findByRsqlAndTag(PAGE, "name==" + unAssignDS.getName(), tag.getId())
                .getNumberOfElements()).as("ds tag ds has wrong ds size").isEqualTo(0);
        assertThat(distributionSetManagement.findByRsqlAndTag(PAGE, "name!=" + unAssignDS.getName(), tag.getId())
                .getNumberOfElements()).as("ds tag ds has wrong ds size").isEqualTo(3);
    }

    @Test
    @Description("Ensures that updates concerning the internal software structure of a DS are not possible if the DS is already assigned.")
    public void updateDistributionSetForbiddedWithIllegalUpdate() {
        // prepare data
        final Target target = testdataFactory.createTarget();

        DistributionSet ds = testdataFactory.createDistributionSet("ds-1");

        final SoftwareModule ah2 = testdataFactory.createSoftwareModuleApp();
        final SoftwareModule os2 = testdataFactory.createSoftwareModuleOs();

        // update is allowed as it is still not assigned to a target
        ds = distributionSetManagement.assignSoftwareModules(ds.getId(), Sets.newHashSet(ah2.getId()));

        // assign target
        assignDistributionSet(ds.getId(), target.getControllerId());
        ds = distributionSetManagement.getWithDetails(ds.getId()).get();

        final Long dsId = ds.getId();
        // not allowed as it is assigned now
        assertThatThrownBy(() -> distributionSetManagement.assignSoftwareModules(dsId, Sets.newHashSet(os2.getId())))
                .isInstanceOf(EntityReadOnlyException.class);

        // not allowed as it is assigned now
        final Long appId = ds.findFirstModuleByType(appType).get().getId();
        assertThatThrownBy(() -> distributionSetManagement.unassignSoftwareModule(dsId, appId))
                .isInstanceOf(EntityReadOnlyException.class);
    }

    @Test
    @Description("Ensures that it is not possible to add a software module that is not defined of the DS's type.")
    public void updateDistributionSetUnsupportedModuleFails() {
        final DistributionSet set = distributionSetManagement
                .create(entityFactory.distributionSet().create().name("agent-hub2")
                        .version(
                                "1.0.5")
                        .type(distributionSetTypeManagement.create(entityFactory.distributionSetType().create()
                                .key("test").name("test").mandatory(Arrays.asList(osType.getId()))).getKey()));

        final SoftwareModule module = softwareModuleManagement.create(
                entityFactory.softwareModule().create().name("agent-hub2").version("1.0.5").type(appType.getKey()));

        // update data
        assertThatThrownBy(
                () -> distributionSetManagement.assignSoftwareModules(set.getId(), Sets.newHashSet(module.getId())))
                        .isInstanceOf(UnsupportedSoftwareModuleForThisDistributionSetException.class);
    }

    @Test
    @Description("Legal updates of a DS, e.g. name or description and module addition, removal while still unassigned.")
    public void updateDistributionSet() {
        // prepare data
        DistributionSet ds = testdataFactory.createDistributionSet("");
        final SoftwareModule os = testdataFactory.createSoftwareModuleOs();

        // update data
        // legal update of module addition
        distributionSetManagement.assignSoftwareModules(ds.getId(), Sets.newHashSet(os.getId()));
        ds = distributionSetManagement.getWithDetails(ds.getId()).get();
        assertThat(ds.findFirstModuleByType(osType).get()).isEqualTo(os);

        // legal update of module removal
        distributionSetManagement.unassignSoftwareModule(ds.getId(), ds.findFirstModuleByType(appType).get().getId());
        ds = distributionSetManagement.getWithDetails(ds.getId()).get();
        assertThat(ds.findFirstModuleByType(appType).isPresent()).isFalse();

        // Update description
        distributionSetManagement.update(entityFactory.distributionSet().update(ds.getId()).name("a new name")
                .description("a new description").version("a new version").requiredMigrationStep(true));
        ds = distributionSetManagement.getWithDetails(ds.getId()).get();
        assertThat(ds.getDescription()).isEqualTo("a new description");
        assertThat(ds.getName()).isEqualTo("a new name");
        assertThat(ds.getVersion()).isEqualTo("a new version");
        assertThat(ds.isRequiredMigrationStep()).isTrue();
    }

    @Test
    @WithUser(allSpPermissions = true)
    @Description("Checks that metadata for a distribution set can be updated.")
    public void updateDistributionSetMetadata() throws InterruptedException {
        final String knownKey = "myKnownKey";
        final String knownValue = "myKnownValue";
        final String knownUpdateValue = "myNewUpdatedValue";

        // create a DS
        final DistributionSet ds = testdataFactory.createDistributionSet("testDs");
        // initial opt lock revision must be zero
        assertThat(ds.getOptLockRevision()).isEqualTo(1);

        // create an DS meta data entry
        createDistributionSetMetadata(ds.getId(), new JpaDistributionSetMetadata(knownKey, ds, knownValue));

        DistributionSet changedLockRevisionDS = distributionSetManagement.get(ds.getId()).get();
        assertThat(changedLockRevisionDS.getOptLockRevision()).isEqualTo(2);

        Thread.sleep(100);

        // update the DS metadata
        final JpaDistributionSetMetadata updated = (JpaDistributionSetMetadata) distributionSetManagement
                .updateMetaData(ds.getId(), entityFactory.generateMetadata(knownKey, knownUpdateValue));
        // we are updating the sw meta data so also modifying the base software
        // module so opt lock
        // revision must be three
        changedLockRevisionDS = distributionSetManagement.get(ds.getId()).get();
        assertThat(changedLockRevisionDS.getOptLockRevision()).isEqualTo(3);
        assertThat(changedLockRevisionDS.getLastModifiedAt()).isGreaterThan(0L);

        // verify updated meta data contains the updated value
        assertThat(updated).isNotNull();
        assertThat(updated.getValue()).isEqualTo(knownUpdateValue);
        assertThat(updated.getId().getKey()).isEqualTo(knownKey);
        assertThat(updated.getDistributionSet().getId()).isEqualTo(ds.getId());
    }

    @Test
    @Description("Tests that a DS queue is possible where the result is ordered by the target assignment, i.e. assigned first in the list.")
    public void findDistributionSetsAllOrderedByLinkTarget() {

        final List<DistributionSet> buildDistributionSets = testdataFactory.createDistributionSets("dsOrder", 10);

        final List<Target> buildTargetFixtures = testdataFactory.createTargets(5, "tOrder", "someDesc");

        final Iterator<DistributionSet> dsIterator = buildDistributionSets.iterator();
        final Iterator<Target> tIterator = buildTargetFixtures.iterator();
        final DistributionSet dsFirst = dsIterator.next();
        final DistributionSet dsSecond = dsIterator.next();
        final DistributionSet dsThree = dsIterator.next();
        final DistributionSet dsFour = dsIterator.next();
        final Target tFirst = tIterator.next();
        final Target tSecond = tIterator.next();

        // set assigned
        assignDistributionSet(dsSecond.getId(), tSecond.getControllerId());
        assignDistributionSet(dsThree.getId(), tFirst.getControllerId());
        // set installed
        testdataFactory.sendUpdateActionStatusToTargets(Collections.singleton(tSecond), Status.FINISHED,
                Arrays.asList("some message"));

        assignDistributionSet(dsFour.getId(), tSecond.getControllerId());

        final DistributionSetFilterBuilder distributionSetFilterBuilder = new DistributionSetFilterBuilder()
                .setIsDeleted(false).setIsComplete(true).setSelectDSWithNoTag(Boolean.FALSE);

        // target first only has an assigned DS-three so check order correct
        final List<DistributionSet> tFirstPin = distributionSetManagement
                .findByFilterAndAssignedInstalledDsOrderedByLinkTarget(PAGE, distributionSetFilterBuilder,
                        tFirst.getControllerId())
                .getContent();
        assertThat(tFirstPin.get(0)).isEqualTo(dsThree);
        assertThat(tFirstPin).hasSize(10);

        // target second has installed DS-2 and assigned DS-4 so check order
        // correct
        final List<DistributionSet> tSecondPin = distributionSetManagement
                .findByFilterAndAssignedInstalledDsOrderedByLinkTarget(PAGE, distributionSetFilterBuilder,
                        tSecond.getControllerId())
                .getContent();
        assertThat(tSecondPin.get(0)).isEqualTo(dsSecond);
        assertThat(tSecondPin.get(1)).isEqualTo(dsFour);
        assertThat(tFirstPin).hasSize(10);
    }

    @Test
    @Description("searches for distribution sets based on the various filter options, e.g. name, version, desc., tags.")
    public void searchDistributionSetsOnFilters() {
        DistributionSetTag dsTagA = distributionSetTagManagement
                .create(entityFactory.tag().create().name("DistributionSetTag-A"));
        final DistributionSetTag dsTagB = distributionSetTagManagement
                .create(entityFactory.tag().create().name("DistributionSetTag-B"));
        final DistributionSetTag dsTagC = distributionSetTagManagement
                .create(entityFactory.tag().create().name("DistributionSetTag-C"));
        distributionSetTagManagement.create(entityFactory.tag().create().name("DistributionSetTag-D"));

        List<DistributionSet> ds5Group1 = testdataFactory.createDistributionSets("", 5);
        List<DistributionSet> dsGroup2 = testdataFactory.createDistributionSets("test2", 5);
        DistributionSet dsDeleted = testdataFactory.createDistributionSet("deleted");
        final DistributionSet dsInComplete = distributionSetManagement.create(entityFactory.distributionSet().create()
                .name("notcomplete").version("1").type(standardDsType.getKey()));

        DistributionSetType newType = distributionSetTypeManagement
                .create(entityFactory.distributionSetType().create().key("foo").name("bar").description("test"));

        distributionSetTypeManagement.assignMandatorySoftwareModuleTypes(newType.getId(),
                Arrays.asList(osType.getId()));
        newType = distributionSetTypeManagement.assignOptionalSoftwareModuleTypes(newType.getId(),
                Arrays.asList(appType.getId(), runtimeType.getId()));

        final DistributionSet dsNewType = distributionSetManagement.create(
                entityFactory.distributionSet().create().name("newtype").version("1").type(newType.getKey()).modules(
                        dsDeleted.getModules().stream().map(SoftwareModule::getId).collect(Collectors.toList())));

        assignDistributionSet(dsDeleted, testdataFactory.createTargets(5));
        distributionSetManagement.delete(dsDeleted.getId());
        dsDeleted = distributionSetManagement.get(dsDeleted.getId()).get();

        ds5Group1 = toggleTagAssignment(ds5Group1, dsTagA).getAssignedEntity();
        dsTagA = distributionSetTagRepository.findByNameEquals(dsTagA.getName()).get();
        ds5Group1 = toggleTagAssignment(ds5Group1, dsTagB).getAssignedEntity();
        dsTagA = distributionSetTagRepository.findByNameEquals(dsTagA.getName()).get();
        dsGroup2 = toggleTagAssignment(dsGroup2, dsTagA).getAssignedEntity();
        dsTagA = distributionSetTagRepository.findByNameEquals(dsTagA.getName()).get();

        // check setup
        assertThat(distributionSetRepository.findAll()).hasSize(13);

        // Find all
        List<DistributionSet> expected = Lists.newArrayListWithExpectedSize(13);
        expected.addAll(ds5Group1);
        expected.addAll(dsGroup2);
        expected.add(dsDeleted);
        expected.add(dsInComplete);
        expected.add(dsNewType);

        assertThat(distributionSetManagement
                .findByDistributionSetFilter(PAGE, getDistributionSetFilterBuilder().build()).getContent()).hasSize(13)
                        .containsOnly(expected.toArray(new DistributionSet[0]));

        DistributionSetFilterBuilder distributionSetFilterBuilder;

        // search for not deleted
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsDeleted(Boolean.TRUE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsDeleted(false);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(12);

        // search for completed
        expected = new ArrayList<>();
        expected.addAll(ds5Group1);
        expected.addAll(dsGroup2);
        expected.add(dsDeleted);
        expected.add(dsNewType);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(true);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(12).containsOnly(expected.toArray(new DistributionSet[0]));

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.FALSE);
        expected = new ArrayList<>();
        expected.add(dsInComplete);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1).containsOnly(expected.toArray(new DistributionSet[0]));

        // search for type
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setType(newType);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setType(standardDsType);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(12);

        // search for text
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setSearchText("%test2");
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(5);

        // search for tags
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setTagNames(Arrays.asList(dsTagA.getName()));
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(10);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setTagNames(Arrays.asList(dsTagB.getName()));
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(5);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder()
                .setTagNames(Arrays.asList(dsTagA.getName(), dsTagB.getName()));
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(10);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder()
                .setTagNames(Arrays.asList(dsTagC.getName(), dsTagB.getName()));
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(5);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setTagNames(Arrays.asList(dsTagC.getName()));
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

        // combine deleted and complete
        expected = new ArrayList<>();
        expected.addAll(ds5Group1);
        expected.addAll(dsGroup2);
        expected.add(dsNewType);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.TRUE)
                .setIsDeleted(Boolean.FALSE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(11).containsOnly(expected.toArray(new DistributionSet[0]));

        expected = Arrays.asList(dsInComplete);
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.FALSE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1).containsOnly(expected.toArray(new DistributionSet[0]));

        expected = Arrays.asList(dsDeleted);
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.TRUE)
                .setIsDeleted(Boolean.TRUE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1).containsOnly(expected.toArray(new DistributionSet[0]));

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsDeleted(Boolean.TRUE)
                .setIsComplete(Boolean.FALSE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

        // combine deleted and complete and type
        expected = new ArrayList<>();
        expected.addAll(ds5Group1);
        expected.addAll(dsGroup2);
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsDeleted(Boolean.FALSE)
                .setIsComplete(Boolean.TRUE).setType(standardDsType);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(10).containsOnly(expected.toArray(new DistributionSet[0]));

        expected = Arrays.asList(dsDeleted);
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.TRUE)
                .setType(standardDsType).setIsDeleted(Boolean.TRUE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1).containsOnly(expected.toArray(new DistributionSet[0]));

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsDeleted(Boolean.TRUE)
                .setIsComplete(Boolean.FALSE).setType(standardDsType);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

        expected = Arrays.asList(dsNewType);
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.TRUE).setType(newType);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(1).containsOnly(expected.toArray(new DistributionSet[0]));

        // combine deleted and complete and type and text
        expected = dsGroup2;
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.TRUE)
                .setType(standardDsType).setSearchText("%test2");
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(5).containsOnly(expected.toArray(new DistributionSet[0]));

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(Boolean.TRUE)
                .setIsDeleted(Boolean.TRUE).setType(standardDsType).setSearchText("%test2");
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setType(standardDsType).setSearchText("%test2")
                .setIsComplete(false).setIsDeleted(false);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setType(newType).setSearchText("%test2")
                .setIsComplete(Boolean.TRUE).setIsDeleted(false);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

        // combine deleted and complete and type and text and tag
        expected = dsGroup2;
        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setIsComplete(true).setType(standardDsType)
                .setSearchText("%test2").setTagNames(Arrays.asList(dsTagA.getName()));
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(5).containsOnly(expected.toArray(new DistributionSet[0]));

        distributionSetFilterBuilder = getDistributionSetFilterBuilder().setType(standardDsType).setSearchText("%test2")
                .setTagNames(Arrays.asList(dsTagA.getName())).setIsComplete(Boolean.FALSE).setIsDeleted(Boolean.FALSE);
        assertThat(distributionSetManagement.findByDistributionSetFilter(PAGE, distributionSetFilterBuilder.build())
                .getContent()).hasSize(0);

    }

    private DistributionSetFilterBuilder getDistributionSetFilterBuilder() {
        return new DistributionSetFilterBuilder();
    }

    @Test
    @Description("Simple DS load without the related data that should be loaded lazy.")
    public void findDistributionSetsWithoutLazy() {
        testdataFactory.createDistributionSets(20);

        assertThat(distributionSetManagement.findByCompleted(PAGE, true)).hasSize(20);
    }

    @Test
    @Description("Deltes a DS that is no in use. Expected behaviour is a hard delete on the database.")
    public void deleteUnassignedDistributionSet() {
        final DistributionSet ds1 = testdataFactory.createDistributionSet("ds-1");
        testdataFactory.createDistributionSet("ds-2");

        // delete a ds
        assertThat(distributionSetRepository.findAll()).hasSize(2);
        distributionSetManagement.delete(ds1.getId());
        // not assigned so not marked as deleted but fully deleted
        assertThat(distributionSetRepository.findAll()).hasSize(1);
        assertThat(distributionSetManagement.findByCompleted(PAGE, true).getTotalElements()).isEqualTo(1);
    }

    @Test
    @Description("Queries and loads the metadata related to a given software module.")
    public void findAllDistributionSetMetadataByDsId() {
        // create a DS
        final DistributionSet ds1 = testdataFactory.createDistributionSet("testDs1");
        final DistributionSet ds2 = testdataFactory.createDistributionSet("testDs2");

        for (int index = 0; index < 10; index++) {
            createDistributionSetMetadata(ds1.getId(),
                    new JpaDistributionSetMetadata("key" + index, ds1, "value" + index));
        }

        for (int index = 0; index < 20; index++) {
            createDistributionSetMetadata(ds2.getId(),
                    new JpaDistributionSetMetadata("key" + index, ds2, "value" + index));
        }

        final Page<DistributionSetMetadata> metadataOfDs1 = distributionSetManagement
                .findMetaDataByDistributionSetId(new PageRequest(0, 100), ds1.getId());

        final Page<DistributionSetMetadata> metadataOfDs2 = distributionSetManagement
                .findMetaDataByDistributionSetId(new PageRequest(0, 100), ds2.getId());

        assertThat(metadataOfDs1.getNumberOfElements()).isEqualTo(10);
        assertThat(metadataOfDs1.getTotalElements()).isEqualTo(10);

        assertThat(metadataOfDs2.getNumberOfElements()).isEqualTo(20);
        assertThat(metadataOfDs2.getTotalElements()).isEqualTo(20);
    }

    @Test
    @Description("Deletes a DS that is in use by either target assignment or rollout. Expected behaviour is a soft delete on the database, i.e. only marked as "
            + "deleted, kept as reference but unavailable for future use..")
    public void deleteAssignedDistributionSet() {
        testdataFactory.createDistributionSet("ds-1");
        testdataFactory.createDistributionSet("ds-2");
        final DistributionSet dsToTargetAssigned = testdataFactory.createDistributionSet("ds-3");
        final DistributionSet dsToRolloutAssigned = testdataFactory.createDistributionSet("ds-4");

        // create assigned DS
        final Target savedTarget = testdataFactory.createTarget();
        assignDistributionSet(dsToTargetAssigned.getId(), savedTarget.getControllerId());

        // create assigned rollout
        testdataFactory.createRolloutByVariables("test", "test", 5, "name==*", dsToRolloutAssigned, "50", "5");

        // delete assigned ds
        assertThat(distributionSetRepository.findAll()).hasSize(4);
        distributionSetManagement.delete(Arrays.asList(dsToTargetAssigned.getId(), dsToRolloutAssigned.getId()));

        // not assigned so not marked as deleted
        assertThat(distributionSetRepository.findAll()).hasSize(4);
        assertThat(distributionSetManagement.findByCompleted(PAGE, true).getTotalElements()).isEqualTo(2);
    }

    @Test
    @Description("Verify that the DistributionSetAssignmentResult not contains already assigned targets.")
    public void verifyDistributionSetAssignmentResultNotContainsAlreadyAssignedTargets() {
        final DistributionSet dsToTargetAssigned = testdataFactory.createDistributionSet("ds-3");

        // create assigned DS
        final Target savedTarget = testdataFactory.createTarget();
        DistributionSetAssignmentResult assignmentResult = assignDistributionSet(dsToTargetAssigned.getId(),
                savedTarget.getControllerId());
        assertThat(assignmentResult.getAssignedEntity()).hasSize(1);

        assignmentResult = assignDistributionSet(dsToTargetAssigned.getId(), savedTarget.getControllerId());
        assertThat(assignmentResult.getAssignedEntity()).hasSize(0);

        assertThat(distributionSetRepository.findAll()).hasSize(1);
    }

    @Test
    @Description("Verify that the find all by ids contains the entities which are looking for")
    @ExpectEvents({ @Expect(type = DistributionSetCreatedEvent.class, count = 12),
            @Expect(type = SoftwareModuleCreatedEvent.class, count = 36) })
    public void verifyFindDistributionSetAllById() {
        final List<Long> searchIds = new ArrayList<>();
        searchIds.add(testdataFactory.createDistributionSet("ds-4").getId());
        searchIds.add(testdataFactory.createDistributionSet("ds-5").getId());
        searchIds.add(testdataFactory.createDistributionSet("ds-6").getId());
        for (int i = 0; i < 9; i++) {
            testdataFactory.createDistributionSet("test" + i);
        }

        final List<DistributionSet> foundDs = distributionSetManagement.get(searchIds);

        assertThat(foundDs).hasSize(3);

        final List<Long> collect = foundDs.stream().map(DistributionSet::getId).collect(Collectors.toList());
        assertThat(collect).containsAll(searchIds);
    }

}
