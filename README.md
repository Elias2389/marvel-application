

* * *



# Marvel Character App Documentation



**API used: *https://developer.marvel.com/*



**Use Cases:** 

* User will be able to see a list of characters from API.

* User will be able to see the character detail when that is selected.



* * *

### Tools

* Kotlin

* ViewModel

* LiveData

* Room

* Dagger Hilt

* Coroutines

* Junit

* Mockk

* Coil

* Retrofit

* Moshi

* Roboelectric

* MockWebServer



* * *

## Structure



### Architecture

MVVM:


**Presentation layer:** Views(Activities, Fragments) and ViewModels.


**Domain layer:** Use cases.


**Data layer:** Repositories.

* * *

### Error m1 processor and Room library
If you have some error compiling this app in Apple Mac with m1 processor you can try add this in gradle:
`allprojects {
       configurations.all {
           resolutionStrategy {
               force 'org.xerial:sqlite-jdbc:3.34.0'
           }
       }
   }`

* * *

### Run detekt rules and fix

`./gradlew detektAll -PdetektAutoFix=true`


