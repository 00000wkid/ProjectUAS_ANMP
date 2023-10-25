import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectuts_anmp.OrderData
import com.example.projectuts_anmp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val _orders = MutableLiveData<OrderData>()
    var orders: LiveData<OrderData> = _orders

    init {
        val json = application.resources.openRawResource(R.raw.order_data).bufferedReader().use { it.readText() }
        val gson = Gson()
        val type: Type = object : TypeToken<OrderData>() {}.type
        _orders.value = gson.fromJson(json, type)
    }

    fun loadOrder() {
        val json = getApplication<Application>().resources.openRawResource(R.raw.order_data).bufferedReader().use { it.readText() }
        val gson = Gson()
        val type: Type = object : TypeToken<OrderData>() {}.type
        val orderData = gson.fromJson<OrderData>(json, type)
        _orders.postValue(orderData)
    }
}
