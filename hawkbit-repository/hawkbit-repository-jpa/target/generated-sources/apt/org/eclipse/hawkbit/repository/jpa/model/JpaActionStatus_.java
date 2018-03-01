package org.eclipse.hawkbit.repository.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.hawkbit.repository.model.Action.Status;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JpaActionStatus.class)
public abstract class JpaActionStatus_ extends org.eclipse.hawkbit.repository.jpa.model.AbstractJpaTenantAwareBaseEntity_ {

	public static volatile SingularAttribute<JpaActionStatus, Long> occurredAt;
	public static volatile SingularAttribute<JpaActionStatus, JpaAction> action;
	public static volatile ListAttribute<JpaActionStatus, String> messages;
	public static volatile SingularAttribute<JpaActionStatus, Status> status;

}

