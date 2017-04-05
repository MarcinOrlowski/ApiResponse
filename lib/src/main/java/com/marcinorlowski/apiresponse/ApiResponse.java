package com.marcinorlowski.apiresponse;

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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.marcinorlowski.apiresponse.helpers.parceler.JSONObjectParcelConverter;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

/**
 * API response container
 */
@Parcel (Parcel.Serialization.BEAN)
final public class ApiResponse {

	public static final String KEY_SUCCESS = "success";
	public static final String KEY_CODE = "code";
	public static final String KEY_LOCALE = "locale";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_DATA = "data";

	@Accessors (prefix = "m") @Setter @Getter
	@Expose
	@SerializedName (KEY_SUCCESS)
	protected boolean mSuccess = false;

	@Accessors (prefix = "m") @Setter @Getter
	@Expose
	@SerializedName (KEY_CODE)
	protected int mCode = 0;

	@Accessors (prefix = "m") @Setter @Getter
	@Expose
	@SerializedName (KEY_LOCALE)
	protected String mLocale = "";

	@Accessors (prefix = "m") @Getter @Expose
	@SerializedName (KEY_LOCALE)
	protected String mMessage = "";

	/**
	 * HTTP Response code. Useful mainly while dealing with errors
	 */
	@Accessors (prefix = "m") @Setter @Getter
	protected int mHttpCode;

	@ParcelPropertyConverter (JSONObjectParcelConverter.class)
	protected JSONObject mResponseJsonObject = null;

	public ApiResponse() {
		// dummy
	}

	public ApiResponse(@NonNull String json) throws JSONException {
		populateFromJsonResponse(json);
	}

	public ApiResponse(@NonNull Exception e) {
		setSuccess(false);
		setMessage(e.getMessage());
	}

	/**
	 * Returns true if Api response is successful AND response "data" contains "key" index.
	 *
	 * @param key
	 * @return
	 */
	@Tolerate
	public boolean isSuccess(String key) {
		return (isSuccess() && (getData() != null) && (getData().has(key)));
	}

	/**
	 * Populate from JSON response.
	 *
	 * @param json JSON String to process
	 *
	 * @return
	 *
	 * @throws JSONException jSON exception in case of parsing errors
	 */
	@SuppressWarnings ("UnusedReturnValue")
	@NonNull
	public ApiResponse populateFromJsonResponse(@NonNull String json) throws JSONException {
		JSONObject j = new JSONObject(json);

		setSuccess(j.getBoolean(KEY_SUCCESS));
		setCode(j.getInt(KEY_CODE));
		setLocale(j.getString(KEY_LOCALE));
		setMessage(j.getString(KEY_MESSAGE));

		setResponseJsonObject(j);

		return this;
	}


	/**
	 * Sets message. If @null is passed converts it to empty string
	 *
	 * @param message message
	 *
	 * @return
	 */
	@SuppressWarnings ("UnusedReturnValue")
	@NonNull
	public ApiResponse setMessage(@Nullable String message) {
		if (message == null) {
			message = "";
		}
		mMessage = message;
		return this;
	}

	/**
	 * Sets response json object.
	 *
	 * @param json the json
	 *
	 * @return the response json object
	 */
	@SuppressWarnings ("UnusedReturnValue")
	@NonNull
	public ApiResponse setResponseJsonObject(@Nullable JSONObject json) {
		mResponseJsonObject = json;
		return this;
	}

	/**
	 * Gets response JSON object.
	 *
	 * @return the response jSON object
	 */
	@Nullable
	public JSONObject getResponseJsonObject() {
		return mResponseJsonObject;
	}

	/**
	 * Gets Data node from the response
	 *
	 * @return the data
	 */
	@Nullable
	public JSONObject getData() {
		JSONObject result = null;

		JSONObject j = getResponseJsonObject();
		if ((j != null) && (j.has("data"))) {
			try {
				result = getResponseJsonObject().getJSONObject(KEY_DATA);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Gets int value from response referenced by given key
	 *
	 * @param key key to look for
	 *
	 * @return the int
	 */
	@Nullable
	public Integer getInt(@NonNull String key) {
		return getInt(key, null);
	}

	/**
	 * Gets int value from response referenced by given key
	 *
	 * @param key
	 * @param defaultValue
	 *
	 * @return return value referenced by key, or defaultValue if not found
	 */
	@SuppressWarnings ("ConstantConditions")
	@Nullable
	public Integer getInt(@NonNull String key, @Nullable Integer defaultValue) {
		Integer result = defaultValue;

		JSONObject j = getData();
		if ((j != null) && j.has(key)) {
			try {
				result = j.getInt(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Gets string from the response referenced by key
	 *
	 * @param key key to look for
	 *
	 * @return the string or @null otherwise
	 */
	@Nullable
	public String getString(@NonNull String key) {
		return getString(key, null);
	}

	/**
	 * Gets string from the response referenced by key. Default value is returned when key is not present in response
	 *
	 * @param key          key to look for
	 * @param defaultValue default value to be returned when no key is found or key is not a string
	 *
	 * @return the string
	 */
	@SuppressWarnings ("ConstantConditions")
	@Nullable
	public String getString(String key, String defaultValue) {
		String result = defaultValue;
		JSONObject j = getData();
		if ((j != null) && j.has(key)) {
			try {
				result = j.getString(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * Gets json object.
	 *
	 * @param key key
	 *
	 * @return the json object
	 */
	@SuppressWarnings ("ConstantConditions")
	@Nullable
	public JSONObject getJsonObject(String key) {
		JSONObject result = null;

		JSONObject j = getData();
		if ((j != null) && j.has(key)) {
			try {
				result = j.getJSONObject(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
