package com.chichi289.cleanarchitecture.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chichi289.cleanarchitecture.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealToolbar(
    title: String,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.txt_desc_back_button),
                )
            }
        }
    )
}

@Preview
@Composable
fun MealToolbarPreview() {
    MealToolbar(
        title = "Meal Toolbar",
        onBackClicked = {}
    )
}