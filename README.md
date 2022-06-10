# Check app paid in [Apklis](https://www.apklis.cu/es/) APK
[![](https://jitpack.io/v/Z17-CU/apklischeckpayment.svg)](https://jitpack.io/#Z17-CU/apklischeckpayment)

### Installing
* Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
* Step 2. Add the dependencys
```groovy

   implementation 'com.github.Z17-CU:apklischeckpayment:$VERSION'
```
* Only for Java App add this dependency
```groovy

   implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8:$LAST_VERSION'
```

### Usage

* It is necessary to have the Apklis application installed and a section started to check, otherwise the user would be null and the payment would be false.

* Check paid Kotlin
```kotlin
    val response = Verify.isPurchased(this, PACKAGE_ID)
    val userName = response.second
    val isPaid = response.first
```

* Check paid Java
```java
   Pair<Boolean, String> response = Verify.Companion.isPurchased(this, PACKAGE_ID);
   String userName = response.getSecond();
   Boolean isPaid = response.getFirst();
```
### Contributing
All contributions are welcome!!!
