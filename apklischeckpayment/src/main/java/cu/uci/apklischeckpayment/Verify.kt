package cu.uci.apklischeckpayment

import android.content.Context
import android.net.Uri
import android.os.RemoteException

class Verify {
    companion object {

        private const val APKLIS_PROVIDER = "content://cu.uci.android.apklis.payment.provider/app/"
        private const val APKLIS_PAID = "paid"
        private const val APKLIS_USER_NAME = "user_name"

        fun isPurchased(context: Context, packageId: String): Pair<Boolean, String?> {
            var paid = false
            var userName: String? = null
            val providerURI: Uri = Uri.parse("$APKLIS_PROVIDER$packageId")
            try {
                val contentResolver = context.contentResolver.acquireContentProviderClient(providerURI)
                val cursor = contentResolver?.query(providerURI, null, null, null, null)
                cursor?.let {
                    if (it.moveToFirst()) {
                        paid = it.getInt(it.getColumnIndex(APKLIS_PAID)) > 0
                        userName = it.getString(it.getColumnIndex(APKLIS_USER_NAME))
                    }
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    contentResolver?.close()
                } else {
                    contentResolver?.release()
                }
                cursor?.close()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            return Pair(paid, userName)
        }
    }
}