package com.example.testcomponenttree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.* // ktlint-disable no-wildcard-imports
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcomponenttree.ui.theme.TestComponentTreeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComponentTreeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // val attachments = remember { mutableStateListOf<String>("Photo", "Library", "Audio", "Video") }
                    var favourites by mutableStateOf(listOf<String>("Photo", "Library", "Audio", "Video"))

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("attachmentList"),
                    ) {
                        items(
                            items = favourites,
                            itemContent = {
                                Attachment(it) {
                                    favourites = favourites.toMutableList().also { itemList ->
                                        itemList.remove(it)
                                    }
//                                    attachments.remove(it)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Attachment(
    attachment: String,
    onRemoveClicked: () -> Unit,
) {
    Box(
        Modifier
            .width(90.dp)
            .height(90.dp)
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            .testTag("AttachmentContainer"),
    ) {
        Box(
            modifier = Modifier
                .matchParentSize(),
        ) {
            Text(text = attachment)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .clickable { onRemoveClicked() }
                .background(MaterialTheme.colorScheme.surface)
                .testTag("removeAttachmentButton"),
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                modifier = Modifier
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestComponentTreeTheme {
        Greeting("Android")
    }
}
