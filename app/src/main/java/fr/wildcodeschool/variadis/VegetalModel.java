package fr.wildcodeschool.variadis;

import android.os.Parcel;
import android.os.Parcelable;


public class VegetalModel implements Parcelable {


    public static final Creator<VegetalModel> CREATOR = new Creator<VegetalModel>() {
        @Override
        public VegetalModel createFromParcel(Parcel in) {
            return new VegetalModel(in);
        }

        @Override
        public VegetalModel[] newArray(int size) {
            return new VegetalModel[size];
        }
    };
    private String name;
    private String pictureUrl;

    public VegetalModel(String pictureUrl, String name) {
        this.pictureUrl = pictureUrl;
        this.name = name;
    }

    protected VegetalModel(Parcel in) {
        name = in.readString();
        pictureUrl = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pictureUrl);
    }
}
