package dz.islem.weatherapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: VM

    abstract fun createViewModel() : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cleanupVM()
    }
}