package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaDistributionSet.class)
public abstract class JpaDistributionSet_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaNamedVersionedEntity_ {

	public static volatile ListAttribute<JpaDistributionSet, JpaDistributionSetMetadata> metadata;
	public static volatile SingularAttribute<JpaDistributionSet, Boolean> deleted;
	public static volatile ListAttribute<JpaDistributionSet, JpaTargetFilterQuery> autoAssignFilters;
	public static volatile SingularAttribute<JpaDistributionSet, Boolean> requiredMigrationStep;
	public static volatile ListAttribute<JpaDistributionSet, JpaTarget> installedAtTargets;
	public static volatile ListAttribute<JpaDistributionSet, JpaTarget> assignedToTargets;
	public static volatile SingularAttribute<JpaDistributionSet, JpaDistributionSetType> type;
	public static volatile SingularAttribute<JpaDistributionSet, Boolean> complete;
	public static volatile ListAttribute<JpaDistributionSet, JpaAction> actions;
	public static volatile SetAttribute<JpaDistributionSet, JpaSoftwareModule> modules;
	public static volatile SetAttribute<JpaDistributionSet, JpaDistributionSetTag> tags;

}

