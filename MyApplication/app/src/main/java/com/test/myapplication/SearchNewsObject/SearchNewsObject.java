
package com.test.myapplication.SearchNewsObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchNewsObject{

    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("readLink")
    @Expose
    private String readLink;
    @SerializedName("totalEstimatedMatches")
    @Expose
    private Integer totalEstimatedMatches;
    @SerializedName("value")
    @Expose
    private List<Value> value = new ArrayList<Value>();

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
     *     The totalEstimatedMatches
     */
    public Integer getTotalEstimatedMatches() {
        return totalEstimatedMatches;
    }

    /**
     * 
     * @param totalEstimatedMatches
     *     The totalEstimatedMatches
     */
    public void setTotalEstimatedMatches(Integer totalEstimatedMatches) {
        this.totalEstimatedMatches = totalEstimatedMatches;
    }

    /**
     * 
     * @return
     *     The value
     */
    public List<Value> getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(List<Value> value) {
        this.value = value;
    }

}
