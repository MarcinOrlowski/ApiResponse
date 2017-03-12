package com.marcinorlowski.apiresponse.helpers.parceler;

/*
 ******************************************************************************
 *
 * Copyright 2017 Marcin Orłowski
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <mail@MarcinOrlowski.com>
 *
 ******************************************************************************
 */

import android.os.Parcel;

import org.json.JSONArray;
import org.parceler.ParcelConverter;

/**
 * JSONArray Parceler converter.
 *
 * To make Parceler deal with JSONObject, annotate it with:
 *
 *   `@ParcelPropertyConverter (JSONArrayParcelConverter.class)`
 *
 * JSONObject foo;
 *
 */
public class JSONArrayParcelConverter implements ParcelConverter<JSONArray> {
	@Override
	public void toParcel(JSONArray input, Parcel parcel) {
		if (input == null) {
			parcel.writeInt(-1);
		} else {
			parcel.writeString(input.toString());
		}
	}

	@Override
	public JSONArray fromParcel(Parcel parcel) {
		try {
			return new JSONArray(parcel.readString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
