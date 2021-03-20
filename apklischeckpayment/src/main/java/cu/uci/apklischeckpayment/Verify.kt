package cu.uci.apklischeckpayment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.RemoteException

class Verify {
    companion object {

        private const val APKLIS_PROVIDER = "content://cu.uci.android.apklis.payment.provider/app/"
        private const val APKLIS_PAID = "paid"
        private const val APKLIS_USER_NAME = "user_name"

        fun isPurchased(context: Context, packageId: String): Triple<Boolean, String?, Boolean> {
            var paid = false
            var install = false
            var userName: String? = null

            val packageManager: PackageManager = context.packageManager
            try {
                install = true
                packageManager.getPackageInfo("cu.uci.android.apklis", PackageManager.GET_ACTIVITIES)
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
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                install = false
            }
            return Triple(paid, userName, install)
        }

        fun showAlert(context: Context, action: Int, packageId: String, title: String, message: String) {
            /*
               action = 0 | Install apklis
               action = 1 | Login
               action = 2 | Purchase
             */
            val dialog = AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Ir a Apklis") { _, _ ->
                        when (action) {
                            0 -> {
                                val url = "https://www.apklis.cu/application/cu.uci.android.apklis"
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(url)
                                context.startActivity(intent)
                            }
                            1 -> {
                                val intent: Intent? = context.packageManager.getLaunchIntentForPackage("cu.uci.android.apklis")
                                intent!!.action = Intent.ACTION_VIEW
                                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                                context.startActivity(intent)
                            }
                            2 -> {
                                val url = "https://www.apklis.cu/application/$packageId"
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.data = Uri.parse(url)
                                context.startActivity(intent)
                            }
                        }
                    }
                    .show()
        }
    }
}
