package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaTargetFilterQuery.class)
public abstract class JpaTargetFilterQuery_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaTenantAwareBaseEntity_ {

	public static volatile SingularAttribute<JpaTargetFilterQuery, JpaDistributionSet> autoAssignDistributionSet;
	public static volatile SingularAttribute<JpaTargetFilterQuery, String> query;
	public static volatile SingularAttribute<JpaTargetFilterQuery, String> name;

}

