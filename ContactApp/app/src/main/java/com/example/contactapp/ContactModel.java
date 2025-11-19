package com.example.contactapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ContactModel implements Parcelable {

    private int image;
    private String ContactName;

    private String ContactNumber;

    /*
     Creating constructor for Contact to access th Private members
     */
    public ContactModel(int image ,String contactName, String contactNumber){
        this.image = image;
        this.ContactName = contactName;
        this.ContactNumber = contactNumber;


    }
    /*
     Getter and setter
     */

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    // this constructor are used to create a object from Parcel
    protected ContactModel(Parcel in){
        image = in.readInt();
        ContactName = in.readString();
        ContactNumber = in.readString();
    }
    // writing data into parcel

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(ContactName);
        dest.writeString(ContactNumber);
    }
    // usually return 0 (Used for spacial objects like FileDescriptor)

    @Override
    public int describeContents() {
        return 0;
    }
    // CREATOR
    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel source) {
            return new ContactModel(source); //  creates new object using Parcel constructor
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

}
