package com.chichi289.cleanarchitecture.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.chichi289.cleanarchitecture.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealSearchBar(
    onQuerySearch: (String) -> Unit,
) {

    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }


    Box(Modifier.fillMaxWidth()) {
        // Talkback focus order sorts based on x and y position before considering z-index. The
        // extra Box with semantics and fillMaxWidth is a workaround to get the search bar to focus
        // before the content.
        Box(
            Modifier
                .semantics { isTraversalGroup = true }
                .zIndex(1f)
                .fillMaxWidth()) {
            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                query = text,
                onQueryChange = { text = it },
                onSearch = {
                    active = false
                    onQuerySearch(text)
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = { Text(stringResource(R.string.txt_search_dishes)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            ) {

            }
        }
    }
}

@Preview
@Composable
fun MealSearchBarPreview() {
    MealSearchBar(
        onQuerySearch = {}
    )
}