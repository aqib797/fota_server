package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.hawkbit.repository.model.Action.ActionType;
import org.eclipse.hawkbit.repository.model.Rollout.RolloutStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaRollout.class)
public abstract class JpaRollout_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaNamedEntity_ {

	public static volatile SingularAttribute<JpaRollout, ActionType> actionType;
	public static volatile SingularAttribute<JpaRollout, Long> lastCheck;
	public static volatile SingularAttribute<JpaRollout, Boolean> deleted;
	public static volatile ListAttribute<JpaRollout, JpaRolloutGroup> rolloutGroups;
	public static volatile SingularAttribute<JpaRollout, JpaDistributionSet> distributionSet;
	public static volatile SingularAttribute<JpaRollout, String> targetFilterQuery;
	public static volatile SingularAttribute<JpaRollout, Long> forcedTime;
	public static volatile SingularAttribute<JpaRollout, Long> totalTargets;
	public static volatile SingularAttribute<JpaRollout, Integer> rolloutGroupsCreated;
	public static volatile SingularAttribute<JpaRollout, Long> startAt;
	public static volatile SingularAttribute<JpaRollout, RolloutStatus> status;

}

