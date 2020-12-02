package cu.uci.example.check.paid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cu.uci.apklischeckpayment.Verify

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response = Verify.isPurchased(this, "com.example.nova.prosalud")

        val userName = response.second
        val isPaid = response.first

        Toast.makeText(this, "User $userName ${if (isPaid) "paid" else "does not paid"}", Toast.LENGTH_LONG).show()
    }
}