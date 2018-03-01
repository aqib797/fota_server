package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaDistributionSetType.class)
public abstract class JpaDistributionSetType_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaNamedEntity_ {

	public static volatile SingularAttribute<JpaDistributionSetType, String> colour;
	public static volatile SingularAttribute<JpaDistributionSetType, Boolean> deleted;
	public static volatile SetAttribute<JpaDistributionSetType, DistributionSetTypeElement> elements;
	public static volatile SingularAttribute<JpaDistributionSetType, String> key;

}

