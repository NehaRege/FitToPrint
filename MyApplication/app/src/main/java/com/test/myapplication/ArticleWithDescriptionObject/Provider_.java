
package com.test.myapplication.ArticleWithDescriptionObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provider_ {

    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The _type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
