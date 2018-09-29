package santoni.packag.com.onlinestorekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btnLogin.setOnClickListener {

            val loginURL = "http://192.168.100.11/Android/login_app_user.php?email=" +
                    activity_main_edt_loginEmail.text.toString() + "&pass=" +
                    activity_main_edt_loginPassword.text.toString()

            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET, loginURL, Response.Listener {
                response ->

                if (response.equals("The user does exist")) {

                    Person.email = activity_main_edt_loginEmail.text.toString()
                    Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
                    val homeIntent = Intent(this@MainActivity,HomeScreen::class.java)
                    startActivity(homeIntent)

                } else {

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Alert")
                    dialogBuilder.setMessage(response)
                    dialogBuilder.create().show()
                }


            }, Response.ErrorListener { error ->

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert")
                dialogBuilder.setMessage(error.message)
                dialogBuilder.create().show()

            })

            requestQ.add(stringRequest)
        }



        activity_main_btnLoginSignUp.setOnClickListener {

            var signUpIntent = Intent(this@MainActivity, SignUpLayout::class.java)
            startActivity(signUpIntent)


        }

    }
}
