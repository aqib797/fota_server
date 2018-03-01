package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaSoftwareModule.class)
public abstract class JpaSoftwareModule_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaNamedVersionedEntity_ {

	public static volatile ListAttribute<JpaSoftwareModule, JpaSoftwareModuleMetadata> metadata;
	public static volatile SingularAttribute<JpaSoftwareModule, Boolean> deleted;
	public static volatile SingularAttribute<JpaSoftwareModule, String> vendor;
	public static volatile SingularAttribute<JpaSoftwareModule, JpaSoftwareModuleType> type;
	public static volatile ListAttribute<JpaSoftwareModule, JpaDistributionSet> assignedTo;
	public static volatile ListAttribute<JpaSoftwareModule, JpaArtifact> artifacts;

}

