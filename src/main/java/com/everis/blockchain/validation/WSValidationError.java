package com.everis.blockchain.validation;

import java.io.Serializable;

/**
 * Validation error model for Webservice layer.
 *
 * @author everis
 * @since 1.7
 */
public class WSValidationError implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Type.
     */
    private String type;

    /**
     * Object.
     */
    private String object;

    /**
     * details.
     */
    private String details;

    /**
     * Description.
     */
    private String description;

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(final String typeValue) {
        this.type = typeValue;
    }

    /**
     * Gets the object.
     *
     * @return the object
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object.
     *
     * @param object the new object
     */
    public void setObject(final String objectValue) {
        this.object = objectValue;
    }

    /**
     * Gets the details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details.
     *
     * @param details the new details
     */
    public void setDetails(final String detailsValue) {
        this.details = detailsValue;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String descriptionValue) {
        this.description = descriptionValue;
    }
}
