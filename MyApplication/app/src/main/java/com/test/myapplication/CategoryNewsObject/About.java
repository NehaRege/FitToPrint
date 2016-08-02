
package com.test.myapplication.CategoryNewsObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class About {

    @SerializedName("readLink")
    @Expose
    private String readLink;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The readLink
     */
    public String getReadLink() {
        return readLink;
    }

    /**
     * 
     * @param readLink
     *     The readLink
     */
    public void setReadLink(String readLink) {
        this.readLink = readLink;
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
