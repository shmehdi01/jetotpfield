package lib.syed.jetotpfield

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lib.syed.jetotpfield.ui.theme.JetOTPFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetOTPFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    OTPScreen()
                }
            }
        }
    }
}

@Composable
fun OTPScreen() {

    var otpText by remember {
        mutableStateOf("")
    }

    Scaffold {
        Column(Modifier
            .padding(it)
            .fillMaxSize()
            .padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "General")
            OTPField { otp ->
                //OnComplete
                otpText = otp
            }
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Circular")
            CircleOTP()
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Custom Shape")
            CustomShapeOTP()
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Box")
            BoxOTP()
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Rounded")
            RoundedOTP()
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Text(text = "Underline")
            UnderlineOTP()
            Spacer(modifier = Modifier.padding(vertical = 12.dp))

            Text(text = "OTP is $otpText")
        }
    }
}

@Composable
fun CircleOTP() {
    OTPField(shape = CircleShape, underlineStroke = 0f) { otp ->
        //OnComplete
        Log.d("OTP", otp)
    }
}

@Composable
fun CustomShapeOTP() {
    OTPField(shape = CustomShape(), underlineStroke = 0f) { otp ->
        //OnComplete
        Log.d("OTP", otp)
    }
}

@Composable
fun BoxOTP() {
    OTPField(underlineStroke = 0f, backgroundColor = Color.Cyan, underlineColor = Color.Cyan) { otp ->
        //OnComplete
        Log.d("OTP", otp)
    }
}

@Composable
fun RoundedOTP() {
    OTPField(underlineStroke = 0f, backgroundColor = Color.Cyan, underlineColor = Color.Cyan, shape = RoundedCornerShape(12.dp)) { otp ->
        //OnComplete
        Log.d("OTP", otp)
    }
}

@Composable
fun UnderlineOTP() {
    OTPField(underlineStroke = 2f, backgroundColor = Color.Transparent, shape = RoundedCornerShape(12.dp)) { otp ->
        //OnComplete
        Log.d("OTP", otp)
    }
}


@Composable
fun OtpView() {
    OTPField(
        backgroundColor = MaterialTheme.colors.primary, //Change background color,
        shape = RoundedCornerShape(12.dp), //Change shape
        underlineColor = Color.Red,
        underlineStroke = 2f,
        cursorBrush = SolidColor(MaterialTheme.colors.secondary),
        length = 5, //OTP length default is 5
        textStyle = TextStyle(fontSize = 20.sp), //Change text style,
        onComplete ={ otp ->
            Log.d("OTP", otp)
        } )
}



class CustomShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}


