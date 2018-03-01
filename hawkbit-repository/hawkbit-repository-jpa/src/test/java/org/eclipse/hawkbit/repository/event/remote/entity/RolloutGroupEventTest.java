/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.event.remote.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.util.UUID;

import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.Rollout;
import org.eclipse.hawkbit.repository.model.RolloutGroup;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupSuccessCondition;
import org.eclipse.hawkbit.repository.model.RolloutGroupConditionBuilder;
import org.junit.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Test the remote entity events.
 */
@Features("Component Tests - Repository")
@Stories("Test RolloutGroupCreatedEvent and RolloutGroupUpdatedEvent")
public class RolloutGroupEventTest extends AbstractRemoteEntityEventTest<RolloutGroup> {

    @Test
    @Description("Verifies that the rollout group entity reloading by remote created event works")
    public void testRolloutGroupCreatedEvent() {
        final RolloutGroupCreatedEvent createdEvent = (RolloutGroupCreatedEvent) assertAndCreateRemoteEvent(
                RolloutGroupCreatedEvent.class);
        assertThat(createdEvent.getRolloutId()).isNotNull();
    }

    @Test
    @Description("Verifies that the rollout group entity reloading by remote updated event works")
    public void testRolloutGroupUpdatedEvent() {
        assertAndCreateRemoteEvent(RolloutGroupUpdatedEvent.class);
    }

    @Override
    protected RemoteEntityEvent<?> createRemoteEvent(final RolloutGroup baseEntity,
            final Class<? extends RemoteEntityEvent<?>> eventType) {

        Constructor<?> constructor = null;
        for (final Constructor<?> constructors : eventType.getDeclaredConstructors()) {
            if (constructors.getParameterCount() == 3) {
                constructor = constructors;
            }
        }

        if (constructor == null) {
            throw new IllegalArgumentException("No suitable constructor foundes");
        }

        try {
            return (RemoteEntityEvent<?>) constructor.newInstance(baseEntity, 1L, "Node");
        } catch (final ReflectiveOperationException e) {
            fail("Exception should not happen " + e.getMessage());
        }
        return null;
    }

    @Override
    protected RemoteEntityEvent<?> assertEntity(final RolloutGroup baseEntity, final RemoteEntityEvent<?> e) {
        final AbstractRolloutGroupEvent event = (AbstractRolloutGroupEvent) e;

        assertThat(event.getEntity()).isSameAs(baseEntity);
        assertThat(event.getRolloutId()).isEqualTo(1L);

        AbstractRolloutGroupEvent underTestCreatedEvent = (AbstractRolloutGroupEvent) createProtoStuffEvent(event);
        assertThat(underTestCreatedEvent.getEntity()).isEqualTo(baseEntity);
        assertThat(underTestCreatedEvent.getRolloutId()).isEqualTo(1L);

        underTestCreatedEvent = (AbstractRolloutGroupEvent) createJacksonEvent(event);
        assertThat(underTestCreatedEvent.getEntity()).isEqualTo(baseEntity);
        assertThat(underTestCreatedEvent.getRolloutId()).isEqualTo(1L);

        return underTestCreatedEvent;
    }

    @Override
    protected RolloutGroup createEntity() {
        testdataFactory.createTarget(UUID.randomUUID().toString());

        final DistributionSet ds = distributionSetManagement.create(entityFactory.distributionSet()
                .create().name("incomplete").version("2").description("incomplete").type("os"));

        final Rollout entity = rolloutManagement.create(
                entityFactory.rollout().create().name("exampleRollout").targetFilterQuery("controllerId==*").set(ds),
                10, new RolloutGroupConditionBuilder().withDefaults()
                        .successCondition(RolloutGroupSuccessCondition.THRESHOLD, "10").build());

        return rolloutGroupManagement.findByRollout(PAGE, entity.getId()).getContent().get(0);
    }

}
