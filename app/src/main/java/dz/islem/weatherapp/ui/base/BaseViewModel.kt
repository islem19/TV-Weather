package dz.islem.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()

    fun cleanupVM(){
        compositeDisposable.dispose()
    }

}