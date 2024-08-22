package com.example.bank_misr_login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_misr_login.ui.theme.Bank_misr_loginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bank_misr_loginTheme {
                BankMisrLogin()
            }
        }
    }
}

@Composable
fun BankMisrLogin() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Condition to enable or disable the Login button
    val isButtonEnabled = username.isNotBlank() && password.isNotBlank()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.bm_icon),
                contentDescription = "Bank Misr Logo",
                modifier = Modifier
                    .size(110.dp)
                    .padding(start = 15.dp)
            )
            TextButtonExample(stringResource(R.string.TextIcon))
        }

        // Passing the state and the onValueChange callback to update the state
        TextFieldSample(userName = stringResource(R.string.username), onValueChange = { username = it })
        PasswordFieldSample(userName = stringResource(R.string.password), onValueChange = { password = it })

        TextButtonForgetUser()
        FilledButton(isButtonEnabled)
        Row (horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth()){
            Text(text = stringResource(R.string.need_help), Modifier.padding(start = 30.dp, top = 20.dp,end=0.dp))
            ContactUs()
        }
        Text("_______________________________________",
            modifier = Modifier
                .padding(top = 40.dp, start = 20.dp)
                .fillMaxWidth(1f)
                .alpha(0.5f),
            color = Color.Gray,)
        Row (modifier = Modifier.fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.our_products),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 15.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.exchange_rate),
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp)
                    .padding(start = 5.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.security_tips),
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp)
                    .padding(start = 5.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.nearest_branch_or_atm),
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp)
                    .padding(start = 5.dp)
            )
        }
        Row {
            Text(stringResource(R.string.our_products) , modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(start = 10.dp))
            Text(stringResource(R.string.exchange_rate) , modifier = Modifier.fillMaxWidth(0.3f))
            Text(
                stringResource(R.string.security_tips) , modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(start = 15.dp))
            Text(
                stringResource(R.string.nearest_branch_or_atm) , modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 10.dp))
        }
    }
}

@Composable
fun TextFieldSample(userName: String, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        label = { Text(userName, Modifier.alpha(1f)) },
        modifier = Modifier
            .padding(start = 30.dp)
            .fillMaxWidth(0.9f)
            .alpha(0.6f),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 15.sp)
    )
}

@Composable
fun PasswordFieldSample(userName: String, onValueChange: (String) -> Unit) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            onValueChange(it)
        },
        label = { Text(userName, fontSize = 15.sp, modifier = Modifier.alpha(1f)) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .padding(start = 30.dp, top = 20.dp)
            .fillMaxWidth(0.9f)
            .alpha(0.6f),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black, fontSize = 15.sp),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(id = R.drawable.ic_visibility)
            else
                painterResource(id = R.drawable.ic_visibility_off)

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Image(
                    painter = image,
                    contentDescription = description
                )
            }
        }
    )
}

@Composable
fun FilledButton(isEnabled: Boolean) {
    Button(
        onClick = { /* Handle login logic */ },
        enabled = isEnabled, // Enable or disable the button based on the input fields
        modifier = Modifier
            .padding(start = 30.dp, top = 20.dp)
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) Color.Red else Color.Gray, // Change color when enabled or disabled
            contentColor = Color.White
        ),
    ) {
        Text(stringResource(R.string.login), fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun TextButtonExample(text: String) {

    TextButton(
        onClick = { }
    ) {
        Text(text, fontSize = 20.sp, color = Color.Red, modifier = Modifier.padding(top = 30.dp))
    }
}

@Composable
fun TextButtonForgetUser() {
    TextButton(
        onClick = { }
    ) {
        Text(
            stringResource(R.string.forgot_password_username),
            fontSize = 13.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 1.dp, start = 19.dp),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun ContactUs() {
    TextButton(
        onClick = { }
    ) {
        Text(
            stringResource(R.string.contact_us),
            fontSize = 15.sp,
            color = Color.Red,
            modifier = Modifier.padding(top = 14.dp, start =0.dp),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BankMisrLoginPreview(modifier: Modifier = Modifier) {
    BankMisrLogin()
}
