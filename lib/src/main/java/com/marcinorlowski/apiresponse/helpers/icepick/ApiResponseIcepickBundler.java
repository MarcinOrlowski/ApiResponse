package com.marcinorlowski.apiresponse.helpers.icepick;

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

import android.os.Bundle;

import com.marcinorlowski.apiresponse.ApiResponse;

import org.parceler.Parcels;

import icepick.Bundler;

/**
 * Icepick bundler for ApiResponse model
 *
 * When annotating response for save sate use:
 *
 * @State (ApiResponseIcepickBundler.class)
 * ApiResponse foo;
 */
public class ApiResponseIcepickBundler implements Bundler<ApiResponse> {

	@Override
	public void put(String key, ApiResponse value, Bundle bundle) {
		bundle.putParcelable(key, Parcels.wrap(value));
	}

	@Override
	public ApiResponse get(String key, Bundle bundle) {
		return Parcels.unwrap(bundle.getParcelable(key));
	}

}
