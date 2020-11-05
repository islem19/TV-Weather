package dz.islem.weatherapp.ui.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment <VM : BaseViewModel> : Fragment(){
    lateinit var viewModel : VM

    abstract fun createViewModel() : VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = createViewModel()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.cleanupVM()
    }
}