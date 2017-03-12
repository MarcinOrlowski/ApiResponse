ApiResponse
===========

[![Release](https://jitpack.io/v/MarcinOrlowski/apiresponse.svg)](https://jitpack.io/#MarcinOrlowski/ApiResponse)

 `ApiResponse` helps simplifying management of API JSON responses. It follows the API
 format as produced by [Api Response Builder](https://github.com/MarcinOrlowski/laravel-api-response-builder)
 library.


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

    compile 'com.github.MarcinOrlowski:ApiResponse:<VERSION>'

 For recent value of `<VERSION>` consult [library releases](https://github.com/MarcinOrlowski/ApiResponse/releases)
 or jitpack badge: [![Release](https://jitpack.io/v/MarcinOrlowski/apiresponse.svg)](https://jitpack.io/#MarcinOrlowski/ApiResponse)

Project support
===============
  
 `ApiResponse` is free software and you can use it fully free of charge in any of your projects, open source or 
 commercial, however if you feel it prevent you from reinventing the wheel, helped having your projects done or simply
 saved you time and money  then then feel free to donate to the project by sending some BTC to 
 `1LbfbmZ1KfSNNTGAEHtP63h7FPDEPTa3Yo`.
  
 ![BTC](img/btc.png)
  

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
  
  * Written and copyrighted &copy;2013-2017 by Marcin Orlowski <mail (#) marcinorlowski (.) com>
  * `ApiResponse` is open-sourced library licensed under the Apache 2.0 license
