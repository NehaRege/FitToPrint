
package com.test.myapplication.TrendingTopicsObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.myapplication.MainActivity;
import com.test.myapplication.R;

public class Value implements Parcelable {

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

    public Value(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<Value> CREATOR = new Parcelable.Creator<Value>() {
        public Value createFromParcel(Parcel in) {
            return new Value(in);
        }

        public Value[] newArray(int size) {

            return new Value[size];
        }

    };

    public void readFromParcel(Parcel in) {

        name = in.readString();
        webSearchUrl = in.readString();
        webSearchUrlPingSuffix = in.readString();
        isBreakingNews = in.readByte() != 0;

        //        TODO: Need to figure out and add line for image

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeString(name);
        parcel.writeString(webSearchUrl);
        parcel.writeString(webSearchUrlPingSuffix);
        parcel.writeByte((byte) (isBreakingNews ? 1 : 0));

        //        TODO: Need to figure out and add line for image
    /*    // Convert Drawable to Bitmap first:
        Bitmap bitmap = (Bitmap)(iag.getBitmap();
        // Serialize bitmap as Parcelable:
        out.writeParcelable(bitmap, flags);*/


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
