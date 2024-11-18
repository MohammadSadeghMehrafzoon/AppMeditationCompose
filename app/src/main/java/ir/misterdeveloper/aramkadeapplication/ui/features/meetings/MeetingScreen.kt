package ir.misterdeveloper.aramkadeapplication.ui.features.meetings

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.misterdeveloper.aramkadeapplication.R
import ir.misterdeveloper.aramkadeapplication.model.data.PodcastItem
import org.koin.core.parameter.parametersOf

@Composable
fun MeetingScreen(id: String,title:String) {

    val viewModel = getNavViewModel<MeetingViewModel>(
        parameters = { parametersOf(id.toInt()) }
    )

    val podcastDataState = viewModel.dataPodcast

    LaunchedEffect(id) {
        viewModel.updatePodcastBy(id.toInt())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TopAppBar(
            elevation = 0.dp,
            modifier = Modifier.height(290.dp),
            backgroundColor = Color.Transparent,
            title = { Text(text = "") },
            actions = {

                ImageWithTextScreen(
                    imageResId = R.drawable.meditate,
                    text = title
                )
            }
        )

        PodcastList(podcastDataState = podcastDataState)

    }

}


@Composable
fun PodcastList(
    podcastDataState: MutableState<List<PodcastItem>>,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(podcastDataState.value) { podcastItem ->

            Card(
                modifier = Modifier
                    .border(1.dp, Color.Green, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(70.dp)
                    .clickable {

                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Unspecified,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 40.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = podcastItem.session,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = "${podcastItem.time}دقیقه ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }


}

@Composable
fun ImageWithTextScreen(imageResId: Int, text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}