# jetotpfield

OTPField built using Jetpack Compose

### Code Snippet 

```kotlin
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
        } 
   )
```


<img src="https://github.com/shmehdi01/jetotpfield/blob/main/scr.png" alt="Screenshot" width="200"/>
