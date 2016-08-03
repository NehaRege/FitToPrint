
package com.test.myapplication.TrendingTopicsObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("webSearchUrl")
    @Expose
    private String webSearchUrl;
    @SerializedName("webSearchUrlPingSuffix")
    @Expose
    private String webSearchUrlPingSuffix;
    @SerializedName("isBreakingNews")
    @Expose
    private Boolean isBreakingNews;

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
     *     The image
     */
    public Image getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The webSearchUrl
     */
    public String getWebSearchUrl() {
        return webSearchUrl;
    }

    /**
     * 
     * @param webSearchUrl
     *     The webSearchUrl
     */
    public void setWebSearchUrl(String webSearchUrl) {
        this.webSearchUrl = webSearchUrl;
    }

    /**
     * 
     * @return
     *     The webSearchUrlPingSuffix
     */
    public String getWebSearchUrlPingSuffix() {
        return webSearchUrlPingSuffix;
    }

    /**
     * 
     * @param webSearchUrlPingSuffix
     *     The webSearchUrlPingSuffix
     */
    public void setWebSearchUrlPingSuffix(String webSearchUrlPingSuffix) {
        this.webSearchUrlPingSuffix = webSearchUrlPingSuffix;
    }

    /**
     * 
     * @return
     *     The isBreakingNews
     */
    public Boolean getIsBreakingNews() {
        return isBreakingNews;
    }

    /**
     * 
     * @param isBreakingNews
     *     The isBreakingNews
     */
    public void setIsBreakingNews(Boolean isBreakingNews) {
        this.isBreakingNews = isBreakingNews;
    }

}
