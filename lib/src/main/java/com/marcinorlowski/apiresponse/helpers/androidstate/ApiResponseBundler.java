package com.marcinorlowski.apiresponse.helpers.androidstate;

/*
 ******************************************************************************
 *
 * Copyright 2017-2018 Marcin Orlowski
 *
 * Licensed under the Apache License 2.0
 *
 ******************************************************************************
 *
 * @author Marcin Orlowski <mail@MarcinOrlowski.com>
 *
 ******************************************************************************
 */

import android.os.Bundle;

import com.evernote.android.state.Bundler;
import com.marcinorlowski.apiresponse.ApiResponse;

import org.parceler.Parcels;


/**
 * Android-State bundler for ApiResponse model
 * https://github.com/evernote/android-state
 *
 * When annotating response for save sate use:
 *
 * @State (ApiResponseBundler.class)
 * ApiResponse foo;
 */
public class ApiResponseBundler implements Bundler<ApiResponse> {

	@Override
	public void put(String key, ApiResponse value, Bundle bundle) {
		bundle.putParcelable(key, Parcels.wrap(value));
	}

	@Override
	public ApiResponse get(String key, Bundle bundle) {
		return Parcels.unwrap(bundle.getParcelable(key));
	}

}
