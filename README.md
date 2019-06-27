# Level Up Application
[![CircleCI](https://circleci.com/gh/meshnesh/lms_mpasho_news.svg?style=svg)](https://circleci.com/gh/meshnesh/lms_mpasho_news) [![Maintainability](https://api.codeclimate.com/v1/badges/2fbc5549effe96daf941/maintainability)](https://codeclimate.com/github/meshnesh/lms_mpasho_news/maintainability)

This is an Android application that consumes [NewsApi](https://newsapi.org/) Top Stories API and displays the following headlines and abstracts:

Top Headlines News
Search News

## Getting Started

This project is built with Gradle, the [Android Gradle plugin](http://tools.android.com/tech-docs/new-build-system/user-guide). Follow the steps below to set up the project locally.

* Install `Java JDK`, `version 8` or greater


* Clone [lms_mpasho_news](https://github.com/meshnesh/lms_mpasho_news) inside your working folder.

* Generate api key from https://newsapi.org/
* Put the api key in [MainActivity.java file](https://github.com/meshnesh/lms_mpasho_news/blob/develop/app/src/main/java/com/lms/mpasho_lms_news/view/MainActivity.java) as a value to API_KEY

* Start Android Studio
* Select "Open Project" and select the generated root Project folder.
* Once the project has compiled -> run the project!
    ```
    from the android main menu, click on 'Run' tab, and click on 'Run MainActivity'
    connect your android mobile phone or simple use an inbuilt android emulator
    ```
## Running Test

* Run
    > $ ./gradlew test

## Coverage

* Coverage was implemented by Jacoco


    
## Built With

* [Android Tools](https://developer.android.com/) - Used to develop mobile, watch and T.V applications

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests.

## Authors

Antony Munene.

### License

    Copyright 2019 Antony Munene

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
