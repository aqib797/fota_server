package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupErrorAction;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupErrorCondition;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupStatus;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupSuccessAction;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupSuccessCondition;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaRolloutGroup.class)
public abstract class JpaRolloutGroup_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaNamedEntity_ {

	public static volatile SingularAttribute<JpaRolloutGroup, JpaRolloutGroup> parent;
	public static volatile SingularAttribute<JpaRolloutGroup, JpaRollout> rollout;
	public static volatile SingularAttribute<JpaRolloutGroup, String> errorConditionExp;
	public static volatile SingularAttribute<JpaRolloutGroup, Float> targetPercentage;
	public static volatile SingularAttribute<JpaRolloutGroup, String> targetFilterQuery;
	public static volatile ListAttribute<JpaRolloutGroup, RolloutTargetGroup> rolloutTargetGroup;
	public static volatile SingularAttribute<JpaRolloutGroup, String> errorActionExp;
	public static volatile SingularAttribute<JpaRolloutGroup, RolloutGroupSuccessCondition> successCondition;
	public static volatile SingularAttribute<JpaRolloutGroup, RolloutGroupSuccessAction> successAction;
	public static volatile SingularAttribute<JpaRolloutGroup, String> successActionExp;
	public static volatile SingularAttribute<JpaRolloutGroup, RolloutGroupErrorAction> errorAction;
	public static volatile SingularAttribute<JpaRolloutGroup, Integer> totalTargets;
	public static volatile SingularAttribute<JpaRolloutGroup, RolloutGroupErrorCondition> errorCondition;
	public static volatile SingularAttribute<JpaRolloutGroup, RolloutGroupStatus> status;
	public static volatile SingularAttribute<JpaRolloutGroup, String> successConditionExp;

}

