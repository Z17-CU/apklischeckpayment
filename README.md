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
* Step 2. Add the dependency
```groovy

   implementation 'com.github.Z17-CU:apklischeckpayment:$VERSION'
```
### Usage

* It is necessary to have the Apklis application installed and a section started to check, otherwise the user would be null and the payment would be false.

* Check paid
```kotlin
    val response = Verify.isPurchased(this, PACKAGE_ID)
    val userName = response.second
    val isPaid = response.first
```
### Contributing
All contributions are welcome!!!
