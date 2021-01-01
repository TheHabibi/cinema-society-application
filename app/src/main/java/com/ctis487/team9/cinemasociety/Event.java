package com.ctis487.team9.cinemasociety;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private int id;
    private String title;
    private String year;
    private String director;
    private String date;
    private String time;
    private String image;

    public Event() {

    }
    public Event(int id, String title, String year, String director, String date, String time, String image) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.date = date;
        this.time = time;
        this.image = image;
    }

    protected Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        year = in.readString();
        director = in.readString();
        date = in.readString();
        time = in.readString();
        image = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(director);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(image);

    }
}
