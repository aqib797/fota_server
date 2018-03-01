package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.hawkbit.repository.model.TargetUpdateStatus;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaTarget.class)
public abstract class JpaTarget_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaNamedEntity_ {

	public static volatile SingularAttribute<JpaTarget, String> address;
	public static volatile SingularAttribute<JpaTarget, String> controllerId;
	public static volatile ListAttribute<JpaTarget, RolloutTargetGroup> rolloutTargetGroup;
	public static volatile SingularAttribute<JpaTarget, Long> installationDate;
	public static volatile MapAttribute<JpaTarget, String, String> controllerAttributes;
	public static volatile SetAttribute<JpaTarget, JpaTargetTag> tags;
	public static volatile SingularAttribute<JpaTarget, String> securityToken;
	public static volatile SingularAttribute<JpaTarget, Boolean> requestControllerAttributes;
	public static volatile SingularAttribute<JpaTarget, JpaDistributionSet> installedDistributionSet;
	public static volatile SingularAttribute<JpaTarget, TargetUpdateStatus> updateStatus;
	public static volatile SingularAttribute<JpaTarget, Long> lastTargetQuery;
	public static volatile ListAttribute<JpaTarget, JpaAction> actions;
	public static volatile SingularAttribute<JpaTarget, JpaDistributionSet> assignedDistributionSet;

}

