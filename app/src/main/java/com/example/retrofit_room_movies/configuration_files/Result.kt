package com.example.retrofit_room_movies.configuration_files

import android.os.Parcel
import android.os.Parcelable

data class Result(
        val id: Int,
        val original_title: String?,
        val overview: String?,
        val poster_path: String?,
        val release_date: String?,
        val title: String?,
) :Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun toString(): String {
        return "Title: %s\n\nSynopsis: %s\n\nReleaseDate: %s".format(original_title,overview,release_date)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeString(poster_path)
        parcel.writeString(release_date)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}