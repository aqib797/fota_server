/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.tenancy.configuration.validator;

<<<<<<< HEAD
import org.eclipse.hawkbit.exception.SpServerError;
import org.eclipse.hawkbit.exception.AbstractServerRtException;
=======
import org.eclipse.hawkbit.exception.AbstractServerRtException;
import org.eclipse.hawkbit.exception.SpServerError;
>>>>>>> c80b5b5c09c23126118b1e114cc92be86baba850

/**
 * Exception which is thrown, when the validation of the configuration value has
 * not been successful.
 *
 */
public class TenantConfigurationValidatorException extends AbstractServerRtException {

    private static final long serialVersionUID = 1L;
    private static final SpServerError THIS_ERROR = SpServerError.SP_CONFIGURATION_VALUE_INVALID;

    /**
     * Default constructor.
     */
    public TenantConfigurationValidatorException() {
        super(THIS_ERROR);
    }

    /**
     * Parameterized constructor.
     * 
     * @param cause
     *            of the exception
     */
    public TenantConfigurationValidatorException(final Throwable cause) {
        super(THIS_ERROR, cause);
    }

    /**
     * Parameterized constructor.
     * 
     * @param message
     *            of the exception
     * @param cause
     *            of the exception
     */
    public TenantConfigurationValidatorException(final String message, final Throwable cause) {
        super(message, THIS_ERROR, cause);
    }

    /**
     * Parameterized constructor.
     * 
     * @param message
     *            of the exception
     */
    public TenantConfigurationValidatorException(final String message) {
        super(message, THIS_ERROR);
    }

}
