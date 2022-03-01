package com.ainsigne.mvvmtemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.ainsigne.common.base.BaseActivity
import com.ainsigne.mvvmtemplate.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.callbackFlow

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    fun manyAndEmptyParametersMustFail(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String
    ) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding
            .inflate(layoutInflater)
            .apply {
                setContentView(root)
            }
        
        binding.bottomNavGame.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.game_nav_host_fragment
            )
        )
    }
}
