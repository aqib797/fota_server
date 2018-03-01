package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaArtifact.class)
public abstract class JpaArtifact_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaTenantAwareBaseEntity_ {

	public static volatile SingularAttribute<JpaArtifact, String> filename;
	public static volatile SingularAttribute<JpaArtifact, JpaSoftwareModule> softwareModule;
	public static volatile SingularAttribute<JpaArtifact, String> md5Hash;
	public static volatile SingularAttribute<JpaArtifact, Long> size;
	public static volatile SingularAttribute<JpaArtifact, String> sha1Hash;

}

