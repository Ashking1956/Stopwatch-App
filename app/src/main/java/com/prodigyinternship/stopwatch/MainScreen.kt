import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prodigyinternship.stopwatch.StopwatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel = StopwatchViewModel()) {
    var isRunning by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Stopwatch App",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(),
            )

        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StopwatchDisplay(viewModel = viewModel)
            Spacer(modifier = Modifier.padding(24.dp))
            ControlButtons(
                isRunning = isRunning,
                onStart = {
                    viewModel.start()
                    isRunning = true
                },
                onPause = {
                    viewModel.pause()
                    isRunning = false
                },
                onReset = {
                    viewModel.reset()
                    isRunning = false
                }
            )
        }
    }
}

@Composable
fun StopwatchDisplay(viewModel: StopwatchViewModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${viewModel.minutes.toString().padStart(2, '0')}:",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "${viewModel.seconds.toString().padStart(2, '0')}.",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = viewModel.milliseconds.toString().padStart(2, '0'),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ControlButtons(
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onStart,
            enabled = !isRunning,
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
            )
        ) {
            Text("Start")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onPause,
            enabled = isRunning,
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
            )
        ) {
            Text("Pause")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onReset,
            enabled = !isRunning,
            modifier = Modifier.padding(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(

            )
        ) {
            Text("Reset")
        }
    }
}
