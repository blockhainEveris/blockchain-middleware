package com.everis.blockchain.validation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * List object wrapper not allowing null objects nor empty lists.
 *
 * @author everis
 */
public class ValidationList {

    /**
     * The item list.
     */
    @Valid
    @NotNull
    @Size(min = 1)
    private List<Object> itemList;

    /**
     * Gets the item list.
     *
     * @return the item list
     */
    public List<Object> getItemList() {
        return itemList;
    }

    /**
     * Sets the item list.
     *
     * @param itemList the new item list
     */
    public void setItemList(final List<Object> itemListValue) {
        this.itemList = itemListValue;
    }

}
