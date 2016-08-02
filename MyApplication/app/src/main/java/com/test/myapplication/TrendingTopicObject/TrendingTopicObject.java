
package com.test.myapplication.TrendingTopicObject;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendingTopicObject {

    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("instrumentation")
    @Expose
    private Instrumentation instrumentation;
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
     *     The instrumentation
     */
    public Instrumentation getInstrumentation() {
        return instrumentation;
    }

    /**
     * 
     * @param instrumentation
     *     The instrumentation
     */
    public void setInstrumentation(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
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
