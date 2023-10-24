package com.example.projectuts_anmp

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectuts_anmp.MenuItem

class CartViewModel : ViewModel() {
    // Buat LiveData untuk item keranjang
    private val cartItemsLiveData = MutableLiveData<List<MenuItem>?>()
    private val sessionManager = SessionManager(requireContext())


    // Inisialisasi LiveData
    init {
        cartItemsLiveData.value = sessionManager.getCart()
    }

    // Fungsi untuk mendapatkan LiveData item keranjang
    fun getCartItems(): LiveData<List<MenuItem>> {
        return cartItemsLiveData as LiveData<List<MenuItem>>

    }

    // Fungsi untuk menambahkan item ke keranjang
    fun addItemToCart(item: MenuItem) {
        val currentItems = cartItemsLiveData.value ?: emptyList()
        val updatedItems = currentItems.toMutableList()
        updatedItems.add(item)

        // Simpan item baru ke keranjang dan perbarui LiveData
        sessionManager.addToCart(item,1)
        cartItemsLiveData.postValue(updatedItems)
    }
    fun removeItem(position: Int) {
        val currentItems = cartItemsLiveData.value?.toMutableList()

        if (currentItems != null && currentItems.size > position) {
            currentItems.removeAt(position)
            sessionManager.saveCart(currentItems)
            cartItemsLiveData.postValue(currentItems)
        }
    }

}
