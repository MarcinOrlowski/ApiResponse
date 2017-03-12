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

import org.json.JSONObject;
import org.parceler.ParcelConverter;

/**
 * JSONObject Parceler converter.
 *
 * To make Parceler deal with JSONObject, annotate it with:
 *
 *   `@ParcelPropertyConverter (JSONObjectParcelConverter.class)`
 *
 * JSONObject foo;
 *
 */
public class JSONObjectParcelConverter implements ParcelConverter<JSONObject> {
	@Override
	public void toParcel(JSONObject input, Parcel parcel) {
		if (input == null) {
			parcel.writeInt(-1);
		} else {
			parcel.writeString(input.toString());
		}
	}

	@Override
	public JSONObject fromParcel(Parcel parcel) {
		try {
			return new JSONObject(parcel.readString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
