package my.com.m1.onegold.shared.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import my.com.m1.onegold.shared.viewmodel.model.UserModel

class ApplicationShareViewModel : ViewModel() {
    val user: MutableLiveData<UserModel?> = MutableLiveData()
}