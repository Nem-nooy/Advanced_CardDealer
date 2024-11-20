package kr.ac.kumoh.ce.s20220736.advanced_carddealer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kr.ac.kumoh.ce.s20220736.advanced_carddealer.ui.theme.Advanced_CardDealerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Advanced_CardDealerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            Modifier.padding(innerPadding),
        ) {
            //CardImages()  // CardSection()이라고 하셔도 좋고.
            CardSection()
            ShuffleButton()
        }
    }
}

@Composable
fun ColumnScope.CardSection() {
    val cardResources = IntArray(5)

    cardResources[0]

    cardResources[0] = R.drawable.c_10_of_spades
    cardResources[1] = R.drawable.c_jack_of_spades2
    cardResources[2] = R.drawable.c_queen_of_spades2
    cardResources[3] = R.drawable.c_king_of_spades2
    cardResources[4] = R.drawable.c_ace_of_spades

    CardImages(cardResources)
}

@Composable
fun ColumnScope.CardImages(res: IntArray) {
    if (LocalConfiguration.current.orientation
        == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            Modifier.weight(1f)
                    .background(Color(0, 100, 0))
        ) {
            //Text("가로")  // 회전시키면 버튼 위에 '가로'라는 글자가 나타남.
            Image(
                painter = painterResource(res[0]),
                contentDescription = "Card 1",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[1]),
                contentDescription = "Card 2",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[2]),
                contentDescription = "Card 3",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[3]),
                contentDescription = "Card 4",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[4]),
                contentDescription = "Card 5",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
        }
    }
    else {    // 세로
        Column(
            Modifier.weight(1f)
                    .background(Color(0, 100, 0))
        ) {
            Row(
                Modifier.weight(1f)
            ) {
                //Text("세로")  // 처음에는 버튼 위에 '세로'라는 글자가 나타남.
                Image(
                    painter = painterResource(res[0]),
                    contentDescription = "Card 1",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(res[2]),
                    contentDescription = "Card 2",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
            }
            Row(
                Modifier.weight(1f)
            ) {
                //Text("세로")  // 처음에는 버튼 위에 '세로'라는 글자가 나타남.
                Image(
                    painter = painterResource(res[2]),
                    contentDescription = "Card 3",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(res[3]),
                    contentDescription = "Card 4",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
            }
            Row(
                Modifier.weight(1f)
            ) {
                //Text("세로")  // 처음에는 버튼 위에 '세로'라는 글자가 나타남.
                Image(
                    painter = painterResource(res[4]),
                    contentDescription = "Card 5",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
fun ShuffleButton() {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {}
    ) {
        Text("Good Luck")
    }
}