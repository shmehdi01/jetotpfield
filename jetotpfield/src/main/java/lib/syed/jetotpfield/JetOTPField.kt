package lib.syed.jetotpfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import lib.syed.jetotpfield.viewmodels.OTPFieldViewModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun OTPField(
    length: Int = 4,
    sizeField: Dp = 60.dp,
    backgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity),
    shape: Shape = RoundedCornerShape(0.dp),
    underlineColor: Color = MaterialTheme.colors.primary,
    underlineStroke: Float = 2f,
    cursorBrush: Brush = SolidColor(Color.Black),
    textStyle: TextStyle? = null,
    onComplete: (String) -> Unit,
) {


    val viewModel: OTPFieldViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OTPFieldViewModel(length) as T
        }
    })


    val decorationModifier = Modifier
        .clip(shape)
        .background(backgroundColor)
        .drawBehind {
            val x = size.width - underlineStroke
            val y = size.height - underlineStroke

            drawLine(
                color = underlineColor,
                start = Offset(0f, y),// bottom-left point of the box
                end = Offset(x, y),// bottom-right point of the box
                strokeWidth = underlineStroke
            )
        }


    Row(Modifier.padding(horizontal = 20.dp)) {
        repeat(length) { index ->
            Box(modifier = Modifier.padding(end = 8.dp)) {
                BasicTextField(
                    value = viewModel.fields[index],
                    onValueChange = { value ->
                        if (value.length > 1) {
                            return@BasicTextField
                        }
                        viewModel.onChange(value, index)
                        if (index == length - 1) {
                            val otp = viewModel.otp
                            if (otp.length != length) {
                                return@BasicTextField
                            }
                            onComplete(otp)
                        }
                    },
                    Modifier
                        .size(sizeField)
                        .focusRequester(viewModel.focusRequesters[index])
                        .onKeyEvent {
                            if (it.key.keyCode == Key.Backspace.keyCode) {
                                viewModel.onChange("", index)
                            }
                            false
                        },
                    textStyle = textStyle ?: TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    maxLines = 1,
                    cursorBrush = cursorBrush,
                    decorationBox = { innerTextField ->

                        Box(modifier = decorationModifier) {
                            TextFieldDefaults.TextFieldDecorationBox(
                                value = "xyz",
                                innerTextField = innerTextField,
                                enabled = true,
                                singleLine = true,
                                visualTransformation = VisualTransformation.None,
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                            )
                        }
                    },
                )
            }
        }
    }
}