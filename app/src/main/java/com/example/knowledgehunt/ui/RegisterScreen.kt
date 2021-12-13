package com.example.knowledgehunt.ui

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.knowledgehunt.ui.components.BackTopBar
import com.example.knowledgehunt.ui.components.OutlinedButtonItem
import com.example.knowledgehunt.ui.components.Screens
import com.example.knowledgehunt.ui.components.TextFieldUnit
import com.example.knowledgehunt.viewModels.RegisterScreenViewModel


@Composable
fun RegisterScreen(navController: NavHostController) {
    val viewModel: RegisterScreenViewModel = viewModel()

    Scaffold(
        topBar = {
            BackTopBar(
                title = Screens.Register.title,
                buttonIcon = Icons.Default.ArrowBack,
                modifier = Modifier,
                onClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {
            Image(
                Icons.Rounded.Check,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                    .clickable { },

                )

        },

        isFloatingActionButtonDocked = viewModel.floatingActionButtonState.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            RequestContentPermission(viewModel)


            Row {
                TextFieldUnit(
                    hint = "First Name",
                    onImeAction = {
                        viewModel.firstNameErrorState.value =
                            viewModel.firstNameState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.firstNameErrorState,
                    textState = viewModel.firstNameState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
                TextFieldUnit(
                    hint = "Last Name",
                    onImeAction = {
                        viewModel.lastNameErrorState.value =
                            viewModel.lastNameState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.lastNameErrorState,
                    textState = viewModel.lastNameState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Text
                )
            }
            TextFieldUnit(
                hint = "User Name",
                onImeAction = {
                    viewModel.userNameErrorState.value =
                        viewModel.userNameState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.userNameErrorState,
                textState = viewModel.userNameState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Text
            )
            TextFieldUnit(
                hint = "Phone",
                onImeAction = {
                    viewModel.phoneErrorState.value =
                        viewModel.phoneState.value.text.isEmpty()
                },
                modifier = Modifier
                    .padding(8.dp),
                imeAction = ImeAction.Next,
                errorState = viewModel.phoneErrorState,
                textState = viewModel.phoneState,
                errorText = "Required!",
                KeyboardType = KeyboardType.Phone
            )
            Row(modifier = Modifier.wrapContentSize(Alignment.Center)) {
                TextFieldUnit(
                    hint = "Age",
                    onImeAction = {
                        viewModel.ageErrorState.value =
                            viewModel.ageState.value.text.isEmpty()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(0.5f),
                    imeAction = ImeAction.Next,
                    errorState = viewModel.ageErrorState,
                    textState = viewModel.ageState,
                    errorText = "Required!",
                    KeyboardType = KeyboardType.Number
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .weight(0.5f)
                ) {
                    OutlinedButton(
                        onClick = { viewModel.genderExpanded.value = true },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.Gray),
                    ) {
                        Text(
                            text = "Gender: " + viewModel.genderItems[viewModel.genderSelectedIndex.value] + "🔻",
                            Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray
                        )
                    }
                    DropdownMenu(
                        expanded = viewModel.genderExpanded.value,
                        onDismissRequest = { viewModel.genderExpanded.value = false },
                        modifier = Modifier
                    ) {
                        viewModel.genderItems.forEachIndexed { index, s ->
                            DropdownMenuItem(onClick = {
                                viewModel.genderSelectedIndex.value = index
                                viewModel.genderExpanded.value = false
                            }) {

                                Text(text = s)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun RequestContentPermission(viewModel: RegisterScreenViewModel) {
    val context = LocalContext.current
    val launcher: ManagedActivityResultLauncher<String, Uri?> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.imageUri.value = uri
    }
    Column {
        if (viewModel.bitmap.value == null) {
            Image(
                Icons.Default.PersonOutline,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(250.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
        }
        viewModel.imageUri.value?.let {
            if (Build.VERSION.SDK_INT < 28) {
                viewModel.bitmap.value = MediaStore.Images
                    .Media.getBitmap(context.contentResolver, it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver, it)
                viewModel.bitmap.value = ImageDecoder.decodeBitmap(source)
            }

            viewModel.bitmap.value?.let { btm ->

                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .size(250.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colors.primary, CircleShape)
                )
            }

        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButtonItem(
            onClick = {
                launcher.launch("image/*")
            },
            text = "Pick a Profile Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enableState = mutableStateOf(true),
        )

    }
}
