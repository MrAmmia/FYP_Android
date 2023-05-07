package net.thebookofcode.www.fyp.login

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.iid.FirebaseInstanceId
import dagger.hilt.android.AndroidEntryPoint
import net.thebookofcode.www.fyp.MainActivity
import net.thebookofcode.www.fyp.R
import net.thebookofcode.www.fyp.databinding.ActivityLoginBinding
import net.thebookofcode.www.fyp.databinding.SigningAdminModalPopupBinding
import com.google.android.gms.tasks.OnCompleteListener


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var userID: String = ""
    private var password: String = ""
    val TAG = "Firebase Instance ID"

    private lateinit var connectivityManager: ConnectivityManager
    private val networkStateLiveData = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {

                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token: String = task.result.getToken()

                // Log and use token as needed
                Log.d(TAG, "FCM registration token: $token")
            })


        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Register a broadcast receiver to receive network connectivity change events
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, intentFilter)

        // Update the UI based on the initial network connectivity state
        networkStateLiveData.observe(this, Observer { isConnected ->
            updateUI(isConnected)
        })


        binding.login.setOnClickListener {
            loginUser()
        }
        binding.admin.setOnClickListener {
            showAdminPopUp()

        }
        binding.forgot.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "An Email would be sent soon!!",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        // Update the network state LiveData based on the initial network connectivity state
        networkStateLiveData.postValue(isConnected())
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Update the network state LiveData based on the new network connectivity state
            networkStateLiveData.postValue(isConnected())
        }
    }

    private fun isConnected(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun updateUI(isConnected: Boolean) {
        with(binding) {
            if (isConnected) {

                linearLayout.alpha = 1F
                noNetwork.visibility = View.INVISIBLE
                enableAll()

            } else {
                linearLayout.alpha = 0.6F
                noNetwork.visibility = View.VISIBLE
                disableAll()
            }
        }
    }

    private fun disableAll() {
        with(binding){
            login.isEnabled = false
            userIDEditText.isEnabled = false
            passwordEditText.isEnabled = false
            forgot.isEnabled = false
            admin.isEnabled = false
        }
    }

    private fun enableAll() {
        with(binding){
            login.isEnabled = true
            userIDEditText.isEnabled = true
            passwordEditText.isEnabled = true
            forgot.isEnabled = true
            admin.isEnabled = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    private fun showAdminPopUp() {
        val bottomSheetDialog =
            BottomSheetDialog(LoginActivity@ this, R.style.LoanSavedBottomSheetDialogTheme)
        val inflater = LayoutInflater.from(applicationContext)
        val popupBinding = SigningAdminModalPopupBinding.inflate(inflater)
        bottomSheetDialog.setContentView(popupBinding.root)
        popupBinding.adminLogin.setOnClickListener {
            loginAdmin(popupBinding)
        }
        bottomSheetDialog.show()
    }

    private fun loginAdmin(binding: SigningAdminModalPopupBinding) {
        with(binding) {
            if (adminIDEditText.text.toString()
                    .isNotBlank() && adminPasswordEditText.text.toString().isNotBlank()
            ) {
                val adminID = adminIDEditText.text.toString()
                val password = adminPasswordEditText.text.toString()
                gotoMain()
                TODO("Set up API to check admin")
                // if true, go to main activity
            } else {
                Toast.makeText(
                    applicationContext,
                    "UserID and Password cannot be blank!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun loginUser() {
        with(binding) {
            if (userIDEditText.text.toString().isNotBlank() && passwordEditText.text.toString()
                    .isNotBlank()
            ) {
                userID = userIDEditText.text.toString()
                password = passwordEditText.text.toString()
                gotoMain()
                TODO("Set up API to check user")
                // if true, go to main activity

            } else {
                Toast.makeText(
                    applicationContext,
                    "UserID and Password cannot be blank!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}