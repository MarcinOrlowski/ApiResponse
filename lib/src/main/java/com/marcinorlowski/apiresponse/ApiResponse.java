package com.marcinorlowski.apiresponse;

/**
 ******************************************************************************
 *
 * Copyright 2017-2019 Marcin Orlowski
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <mail@MarcinOrlowski.com>
 *
 ******************************************************************************
 */

import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import org.json.JSONException;
import org.json.JSONObject;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

/**
 * API response container
 */
final public class ApiResponse implements Parcelable {

	public static final String KEY_SUCCESS = "success";
	public static final String KEY_CODE = "code";
	public static final String KEY_LOCALE = "locale";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_DATA = "data";

	// -----------------------------------------------------------------------------------------

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

	// -----------------------------------------------------------------------------------------

	@Expose
	protected boolean success = false;

	/**
	 * @param success
	 *
	 * @return
	 */
	@NonNull
	public ApiResponse setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	/**
	 * Returns @true if received API response indicates success
	 *
	 * @return
	 */
	public boolean success() {
		return this.success;
	}

	/**
	 * Returns true if API response is successful AND response "data" contains "key" index.
	 *
	 * @param key JSON node key to look for. If @null passed, acts as isSuccess()
	 *
	 */
	public boolean success(@Nullable String key) {
		if (key == null) {
			return success();
		} else {
			return (success() && (getData() != null) && (getData().has(key)));
		}
	}

	/**
	 * Returns true if API response indicates failure
	 *
	 */
	public boolean failed() {
		return (!success());
	}

	// -----------------------------------------------------------------------------------------

	@Expose
	protected int code = 0;

	@NonNull
	public ApiResponse setCode(int code) {
		this.code = code;
		return this;
	}

	public int getCode() {
		return this.code;
	}

	// -----------------------------------------------------------------------------------------

	@Expose
	protected String locale = "";

	@NonNull
	public ApiResponse setLocale(@Nullable String locale) {
		this.locale = locale;
		return this;
	}

	@Nullable
	@NonNull
	public String getLocale() {
		return this.locale;
	}

	// -----------------------------------------------------------------------------------------

	@Expose
	@NonNull
	protected String message = "";

	/**
	 * Sets message string. If @null is passed, it is internally changed and set as empty string
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
		this.message = message;
		return this;
	}

	@NonNull
	public String getMessage() {
		return this.message;
	}

	// -----------------------------------------------------------------------------------------

	/**
	 * HTTP Response code. Useful mainly while dealing with errors
	 */
	protected int httpCode;

	@NonNull
	public ApiResponse setHttpCode(int httpCode) {
		this.httpCode = httpCode;
		return this;
	}

	public int getHttpCode() {
		return this.httpCode;
	}

	// -----------------------------------------------------------------------------------------

	@NonNull
	protected JSONObject responseJsonObject = new JSONObject();

	// -----------------------------------------------------------------------------------------

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
	 * Sets response json object.
	 *
	 * @param json the json
	 *
	 * @return the response json object
	 */
	@SuppressWarnings ("UnusedReturnValue")
	@NonNull
	public ApiResponse setResponseJsonObject(@Nullable JSONObject json) {
		responseJsonObject = json;
		return this;
	}

	/**
	 * Gets response JSON object.
	 *
	 * @return the response jSON object
	 */
	@Nullable
	public JSONObject getResponseJsonObject() {
		return responseJsonObject;
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
		if ((j != null) && (j.has(KEY_DATA))) {
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

	@Override
	public int describeContents() { return 0; }

	@Override
	public void writeToParcel(android.os.Parcel dest, int flags) {
		dest.writeString(responseJsonObject.toString());
		dest.writeInt(httpCode);
	}

	protected ApiResponse(android.os.Parcel in) {
		try {
			populateFromJsonResponse(in.readString());
			this.httpCode = in.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final Creator<ApiResponse> CREATOR = new Creator<ApiResponse>() {
		@Override
		public ApiResponse createFromParcel(android.os.Parcel source) {return new ApiResponse(source);}

		@Override
		public ApiResponse[] newArray(int size) {return new ApiResponse[size];}
	};
}
