package com.giftthomu.DtreeTestApp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giftthomu.DtreeTestApp.R
import com.giftthomu.DtreeTestApp.RecordsViewModel
import com.giftthomu.DtreeTestApp.UserAction
import com.giftthomu.DtreeTestApp.ui.theme.BackgroundColor
import com.giftthomu.DtreeTestApp.ui.theme.CardBackgroundColor
import com.giftthomu.DtreeTestApp.ui.theme.cairoFont



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecordsScreen(
    viewModel: RecordsViewModel
) {

    val state = viewModel.state

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(20.dp)
    ) {
        Crossfade(
            targetState = state.isSearchBarVisible,
            animationSpec = tween(durationMillis = 500)
        ) {
            if (it) {
                SearchAppBar(
                    onCloseIconClicked = {
                        viewModel.onAction(UserAction.CloseIconClicked)
                    },
                    onInputValueChange = { newText ->
                        viewModel.onAction(
                            UserAction.TextFieldInput(newText)
                        )
                    },
                    text = state.searchText,
                    onSearchClicked = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )
            } else {
                TopAppBar(
                    onSearchIconClicked = {
                        viewModel.onAction(UserAction.SearchIconClicked)
                    }
                )
            }
        }

        Divider(
            color = CardBackgroundColor,
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 30.dp)
        )
        LazyColumn {
            items(state.list) { actor ->
                SingleItemCard(
                    Name = actor,
                    Surname = actor,
                    Age = actor,
                    City = actor

                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun SearchAppBar(
    onCloseIconClicked: () -> Unit,
    onInputValueChange: (String) -> Unit,
    text: String,
    onSearchClicked: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            onInputValueChange(it)
        },
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 18.sp
        ),
        placeholder = {
            Text(
                text = "Search...",
                fontFamily = cairoFont,
                color = Color.White.copy(alpha = ContentAlpha.medium)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.White.copy(
                    alpha = ContentAlpha.medium
                )
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    if (text.isNotEmpty()) {
                        onInputValueChange("")
                    } else {
                        onCloseIconClicked()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close Icon",
                    tint = Color.White
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.White.copy(
                alpha = ContentAlpha.medium
            ),
            focusedBorderColor = Color.White,
            cursorColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClicked() }
        )
    )
}

@Composable
fun SingleItemCard(
    Name: String,
    Surname: String,
    Age:String,
    City:String

) {
    Card(
        backgroundColor = CardBackgroundColor,
        contentColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_people),
                contentDescription = "People Icon"
            )
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(15.dp),
               verticalArrangement = Arrangement.Bottom

           ) {
               Spacer(modifier = Modifier.width(10.dp))
               Text(
                   text = Name,
                   fontSize = 18.sp,
                   fontFamily = cairoFont
               )
               Text(
                   text =Surname,
                   fontSize = 12.sp,
                   fontFamily = cairoFont
               )
               Text(
                   text = Age,
                   fontSize = 12.sp,
                   fontFamily = cairoFont)
               Text(
                   text = City,
                   fontSize = 12.sp,
                   fontFamily = cairoFont
               )
           }
        }
    }
}

@Composable
fun TopAppBar(
    onSearchIconClicked: () -> Unit,

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Filter by City",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onSearchIconClicked) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun Prev() {
    RecordsScreen(
        viewModel = RecordsViewModel()
    )
}










