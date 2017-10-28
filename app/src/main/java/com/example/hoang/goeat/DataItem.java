package com.example.hoang.goeat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by hoang on 10/22/2017.
 */

public class DataItem implements Parcelable {

    private String itemId;
    private String item;
    private String image;
    private String url;

    public DataItem() {
    }

    public DataItem(String itemId, String item, String image, String url) {
        if (itemId == null) {
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.item = item;
        this.image = image;
        this.url = url;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public String toString() {
        return "DataItem{" +
                "itemId='" + itemId + '\'' +
                ", item='" + item + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.item);
        dest.writeString(this.image);
        dest.writeString(this.url);
    }

    protected DataItem(Parcel in) {
        this.itemId = in.readString();
        this.item = in.readString();
        this.image = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}