package com.example.fetch_androidinternshipcodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetch_androidinternshipcodingexercise.ui.theme.FetchAndroidInternshipCodingExerciseTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchAndroidInternshipCodingExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ItemList(viewModel)
                }
            }
        }
    }
}

// Display all the items grouped by "listId"
@Composable
fun ItemList(viewModel: ItemViewModel) {
    val displayItems = viewModel.items.value

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        displayItems.forEach {(listId, listItems) ->
            item {
                Text(text = "List ID: $listId",
                     style = MaterialTheme.typography.titleMedium,
                     modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(listItems) {item ->
                ItemContent(item)
            }
        }
    }
}


// Display the ID and Name of each item in the data
@Composable
fun ItemContent(item: Item) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "ID: ${item.id}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Name: ${item.name}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    FetchAndroidInternshipCodingExerciseTheme {
        ItemContent(Item(id = 1, listId = 1, name = "Sample Item"))
    }
}