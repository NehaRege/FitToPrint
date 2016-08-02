
package com.test.myapplication.CategoryNewsObject;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClusteredArticle {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("about")
    @Expose
    private List<About_> about = new ArrayList<About_>();
    @SerializedName("provider")
    @Expose
    private List<Provider_> provider = new ArrayList<Provider_>();
    @SerializedName("datePublished")
    @Expose
    private String datePublished;
    @SerializedName("category")
    @Expose
    private String category;

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

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The about
     */
    public List<About_> getAbout() {
        return about;
    }

    /**
     * 
     * @param about
     *     The about
     */
    public void setAbout(List<About_> about) {
        this.about = about;
    }

    /**
     * 
     * @return
     *     The provider
     */
    public List<Provider_> getProvider() {
        return provider;
    }

    /**
     * 
     * @param provider
     *     The provider
     */
    public void setProvider(List<Provider_> provider) {
        this.provider = provider;
    }

    /**
     * 
     * @return
     *     The datePublished
     */
    public String getDatePublished() {
        return datePublished;
    }

    /**
     * 
     * @param datePublished
     *     The datePublished
     */
    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    /**
     * 
     * @return
     *     The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
