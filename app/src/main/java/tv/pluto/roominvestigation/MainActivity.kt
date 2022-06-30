package tv.pluto.roominvestigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tv.pluto.roominvestigation.room.RoomHelper
import tv.pluto.roominvestigation.room.User
import tv.pluto.roominvestigation.ui.theme.RoomInvestigationTheme
import tv.pluto.roominvestigation.workmanager.TempWorker

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = RoomHelper.createDb(applicationContext)
        val user = db.userDao().loadUserById(1)
        setContent {
            val state by user.collectAsState(
                initial = User(1, "empty", "empty"),
            )
            RoomInvestigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            state?.let {
                                Text(text = it.toString())
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            MyButton("Generate code: 5") {
                                Thread {
                                    for (i in 1..100) {
                                        TempWorker.dbWork(applicationContext)
                                    }
                                }.start()
                                SecondProcessBroadcastReceiver.start(applicationContext)
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            MyButton("Generate code: ?") {
                                Thread {
                                    for (i in 1..100) {
                                        TempWorker.dbWork(applicationContext)
                                    }
                                }.start()
                                SecondProcessBroadcastReceiver.start(
                                    applicationContext,
                                    true,
                                )
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            MyButton("Generate code: ?") {
                                Thread {
                                    for (i in 1..100) {
                                        TempWorker.dbWork(applicationContext)
                                    }
                                }.start()
                                SecondProcessBroadcastReceiver.start(
                                    applicationContext,
                                    true,
                                    true,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyButton(name: String, onClick: () -> Unit) {
    Button(
        onClick = {
            Log.d("sansay", "onClick $name")
            onClick()
        },
        content = { Text(text = name) },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomInvestigationTheme {
        MyButton("start local") {}
        MyButton("start remote") {}
    }
}