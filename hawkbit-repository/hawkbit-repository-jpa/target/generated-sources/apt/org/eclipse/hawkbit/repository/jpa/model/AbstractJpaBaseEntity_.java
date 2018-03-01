package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AbstractJpaBaseEntity.class)
public abstract class AbstractJpaBaseEntity_ {

	public static volatile SingularAttribute<AbstractJpaBaseEntity, Long> createdAt;
	public static volatile SingularAttribute<AbstractJpaBaseEntity, Long> lastModifiedAt;
	public static volatile SingularAttribute<AbstractJpaBaseEntity, String> createdBy;
	public static volatile SingularAttribute<AbstractJpaBaseEntity, String> lastModifiedBy;
	public static volatile SingularAttribute<AbstractJpaBaseEntity, Integer> optLockRevision;
	public static volatile SingularAttribute<AbstractJpaBaseEntity, Long> id;

}

