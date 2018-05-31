ApiResponse
===========

[![Release](https://jitpack.io/v/MarcinOrlowski/ApiResponse.svg)](https://jitpack.io/#MarcinOrlowski/ApiResponse)
![Downloads](https://jitpack.io/v/MarcinOrlowski/ApiResponse/month.svg)
[![Dependency Status](https://dependencyci.com/github/MarcinOrlowski/ApiResponse/badge)](https://dependencyci.com/github/MarcinOrlowski/ApiResponse)

 `ApiResponse` helps simplifying management of API JSON responses. It follows the API
 format as produced by [Api Response Builder](https://github.com/MarcinOrlowski/laravel-api-response-builder)
 library but can be used to consume response JSONs produced by any API.

 Includes bundlers for [IcePick](https://github.com/frankiesardo/icepick) 
 and [Android-State](https://github.com/evernote/android-state) state saving libraries.


Installation
============

 Edit your master `gradle.build` file and **add** `maven { url 'https://jitpack.io' }` to your current
 `repositories` block content (if you use other jitpack hosted libraries, then this step can be skipped):

    allprojects {
      repositories {
        maven { url 'https://jitpack.io' }
        }
    }

 Next, edit your **module**'s `build.gradle` and the following dependency:

    implementation 'com.github.MarcinOrlowski:ApiResponse:<VERSION>'

 For recent value of `<VERSION>` consult [library releases](https://github.com/MarcinOrlowski/ApiResponse/releases)
 or jitpack badge: [![Release](https://jitpack.io/v/MarcinOrlowski/ApiResponse.svg)](https://jitpack.io/#MarcinOrlowski/ApiResponse)


Contributing
============
  
 Please report any issue spotted using [GitHub's project tracker](https://github.com/MarcinOrlowski/ApiResponse/issues).
   
 If you'd like to contribute to the this project, please [open new ticket](https://github.com/MarcinOrlowski/ApiResponse/issues) 
 **before doing any work**. This will help us save your time in case I'd not be able to accept such changes. But if all is good and 
 clear then follow common routine:
  
  * fork the project
  * create new branch
  * do your changes
  * send pull request
 
  
License
=======
  
  * Written and copyrighted &copy;2013-2018 by Marcin Orlowski <mail (#) marcinorlowski (.) com>
  * `ApiResponse` is open-sourced library licensed under the Apache 2.0 license
