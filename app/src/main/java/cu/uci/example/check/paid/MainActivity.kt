package cu.uci.example.check.paid

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cu.uci.apklischeckpayment.Verify

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response = Verify.isPurchased(this, "com.example.nova.prosalud")

        val userName = response.second
        val isPaid = response.first
        val install = response.third

        /*
            action:
            1 - Install apklis
            2 - Login in Apklis
            3 - Purchase app
        */

        if (install) {
            // Apklis instalada comprobar compra y login

            if (userName == null) {
                // Usuario no logueado

                Verify.showAlert(
                    this,
                    1,
                    "com.example.nova.prosalud",
                    "Usuario no logueado",
                    "Para poder usar ${getString(R.string.app_name)} debe iniciar sección en Apklis, luego de iniciar sección puede continuar con el uso de ${
                        getString(R.string.app_name)
                    }"
                )
            } else {
                // Usuario loguado, comprobar compra

                if (isPaid) {
                    // Aplicación comprada, su lógica aquí

                    Toast.makeText(
                        applicationContext,
                        "Todo esta bien, programe sus siguientes acciones",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // Aplicación no comprada

                    Verify.showAlert(
                        this,
                        2,
                        "com.example.nova.prosalud",
                        "Aplicación no comprada",
                        "Para poder usar ${getString(R.string.app_name)} debe comprar nuestra aplicación, luego de que la compra sea confirmada puede continuar el uso de nuestra aplicación"
                    )
                }
            }
        } else {
            // Apklis no instalado

            Verify.showAlert(
                this,
                0,
                "com.example.nova.prosalud",
                "Apklis no instalada",
                "Para poder usar ${getString(R.string.app_name)} debe instalar Apklis y luego iniciar sección dentro de esta"
            )
        }
    }
}
