package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.hawkbit.repository.model.Action.ActionType;
import org.eclipse.hawkbit.repository.model.Action.Status;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaAction.class)
public abstract class JpaAction_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaTenantAwareBaseEntity_ {

	public static volatile SingularAttribute<JpaAction, ActionType> actionType;
	public static volatile SingularAttribute<JpaAction, JpaDistributionSet> distributionSet;
	public static volatile ListAttribute<JpaAction, JpaActionStatus> actionStatus;
	public static volatile SingularAttribute<JpaAction, JpaRollout> rollout;
	public static volatile SingularAttribute<JpaAction, JpaRolloutGroup> rolloutGroup;
	public static volatile SingularAttribute<JpaAction, Long> forcedTime;
	public static volatile SingularAttribute<JpaAction, Boolean> active;
	public static volatile SingularAttribute<JpaAction, JpaTarget> target;
	public static volatile SingularAttribute<JpaAction, Status> status;

}

