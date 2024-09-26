package com.example.fetch_androidinternshipcodingexercise

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ItemViewModel : ViewModel() {
    // map with keys as listId, values as the list of items with that listId
    private val _items = mutableStateOf<Map<Int, List<Item>>>(emptyMap())
    val items: State<Map<Int, List<Item>>> = _items

    init {
        fetchItems()
    }

    /*
        Fetch (get it?) the items and process the data by:
        1) Filtering out any items where "name" is blank or null
        2) Sorting the results first by "listId" then by "name"
    */
    private fun fetchItems() {
        viewModelScope.launch {
            val rawItems = RetrofitInstance.api.getItems()
            val processedItems = rawItems
                .filterNot { it.name.isNullOrBlank() }
                .sortedWith(compareBy({it.listId}, {it.name}))
                .groupBy { it.listId }
            _items.value = processedItems
        }
    }
}