package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RolloutTargetGroup.class)
public abstract class RolloutTargetGroup_ {

	public static volatile SingularAttribute<RolloutTargetGroup, JpaRolloutGroup> rolloutGroup;
	public static volatile ListAttribute<RolloutTargetGroup, JpaAction> actions;
	public static volatile SingularAttribute<RolloutTargetGroup, JpaTarget> target;

}

