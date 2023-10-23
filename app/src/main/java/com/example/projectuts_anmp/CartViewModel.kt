package com.example.projectuts_anmp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectuts_anmp.MenuItem

class CartViewModel : ViewModel() {
    // Buat LiveData untuk item keranjang
    private val cartItemsLiveData = MutableLiveData<List<MenuItem>>()

    // Buat SessionManager (sesuaikan dengan kelas SessionManager Anda)
    private val sessionManager = SessionManager()

    // Inisialisasi LiveData
    init {
        cartItemsLiveData.value = sessionManager.getCart()
    }

    // Fungsi untuk mendapatkan LiveData item keranjang
    fun getCartItems(): LiveData<List<MenuItem>> {
        return cartItemsLiveData
    }

    // Fungsi untuk menambahkan item ke keranjang
    fun addItemToCart(item: MenuItem) {
        val currentItems = cartItemsLiveData.value ?: emptyList()
        val updatedItems = currentItems.toMutableList()
        updatedItems.add(item)

        // Simpan item baru ke keranjang dan perbarui LiveData
        sessionManager.addToCart(item)
        cartItemsLiveData.postValue(updatedItems)
    }
}
