package com.example.jetbizcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from t he theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    val buttonClikedState = remember{
        mutableStateOf(false)
    }
    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp) ),
            elevation = 4.dp,
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider()
                CreateInfo()
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        buttonClikedState.value = !buttonClikedState.value
                        Log.d("Clicked", "CreateBizCard: Clicked")
                    }) {
                    Text(
                        "Portfolio",
                        style = MaterialTheme.typography.button,
                    )
                }
                if(buttonClikedState.value){
                    Content()
                }
            }

        }
    }
}

@Preview
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp),){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(
                width = 2.dp,
                color = Color.LightGray
            )
        ) {
            Portfolio(data = listOf(
                "Project1",
                "Project2",
                "Project3",
                "Project1",
                "Project2",
                "Project3",
                "Project1",
                "Project2",
                "Project3",
                "Project1",
                "Project2",
                "Project3",
            ))
        }

    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)) {
                    CreateImageProfile(modifier = Modifier.size(75.dp))
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)) {
                        Text(item, fontWeight = FontWeight.Bold)
                        Divider(color = Color.Transparent, modifier = Modifier.height(8.dp))
                        Text("A descriptive description", style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Pedro Cutiño",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
           "Android Developer T2",
            modifier = Modifier.padding(3.dp),
        )
        Text(
            "@pcutinosanchez",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.size(150.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, color = Color.LightGray), elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}