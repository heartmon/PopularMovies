package com.heartmon.android.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Property;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by heartmon on 1/22/2017.
 */

/*
Parcelable starter code obtained from
https://www.sitepoint.com/transfer-data-between-activities-with-android-parcelable/
*/

public class Movie implements Parcelable {
    private String posterPath;
    private String overview;
    private Integer id;
    private String title;
    private Float voteAverage;
    private String backdropPath;
    private Date releaseDate;

    public Movie(String posterPath, String overview, Integer id, String title, Float voteAverage, String backdropPath, Date releaseDate) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "posterPath='" + posterPath + '\'' +
                ", overview='" + overview + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    //Parcelable
    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        //write all properties to the parcle
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeInt(id);
        dest.writeFloat(voteAverage);
        dest.writeString(title);
        dest.writeString(backdropPath);
        dest.writeLong(releaseDate.getTime());
    }

    //constructor used for parcel
    public Movie(Parcel parcel){
        //read and set saved values from parcel
        posterPath = parcel.readString();
        overview = parcel.readString();
        id = parcel.readInt();
        voteAverage = parcel.readFloat();
        title = parcel.readString();
        backdropPath = parcel.readString();
        releaseDate = new Date(parcel.readLong());
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){

        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}
