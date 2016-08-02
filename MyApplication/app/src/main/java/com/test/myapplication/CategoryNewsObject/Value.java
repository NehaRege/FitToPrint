
package com.test.myapplication.CategoryNewsObject;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("about")
    @Expose
    private List<About> about = new ArrayList<About>();
    @SerializedName("provider")
    @Expose
    private List<Provider> provider = new ArrayList<Provider>();
    @SerializedName("datePublished")
    @Expose
    private String datePublished;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("clusteredArticles")
    @Expose
    private List<ClusteredArticle> clusteredArticles = new ArrayList<ClusteredArticle>();
    @SerializedName("mentions")
    @Expose
    private List<Mention> mentions = new ArrayList<Mention>();

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
    public List<About> getAbout() {
        return about;
    }

    /**
     * 
     * @param about
     *     The about
     */
    public void setAbout(List<About> about) {
        this.about = about;
    }

    /**
     * 
     * @return
     *     The provider
     */
    public List<Provider> getProvider() {
        return provider;
    }

    /**
     * 
     * @param provider
     *     The provider
     */
    public void setProvider(List<Provider> provider) {
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

    /**
     * 
     * @return
     *     The clusteredArticles
     */
    public List<ClusteredArticle> getClusteredArticles() {
        return clusteredArticles;
    }

    /**
     * 
     * @param clusteredArticles
     *     The clusteredArticles
     */
    public void setClusteredArticles(List<ClusteredArticle> clusteredArticles) {
        this.clusteredArticles = clusteredArticles;
    }

    /**
     * 
     * @return
     *     The mentions
     */
    public List<Mention> getMentions() {
        return mentions;
    }

    /**
     * 
     * @param mentions
     *     The mentions
     */
    public void setMentions(List<Mention> mentions) {
        this.mentions = mentions;
    }

}
