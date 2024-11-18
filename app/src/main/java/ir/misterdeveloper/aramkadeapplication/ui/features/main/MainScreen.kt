package ir.misterdeveloper.aramkadeapplication.ui.features.main


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import ir.misterdeveloper.aramkadeapplication.model.data.Category
import ir.misterdeveloper.aramkadeapplication.model.data.Items
import ir.misterdeveloper.aramkadeapplication.util.MyScreen
import ir.misterdeveloper.aramkadeapplication.util.NetworkChecker
import org.koin.core.parameter.parametersOf


@Composable
fun MainScreen() {
    val context = LocalContext.current
    val uiController = rememberSystemUiController()
    val navigation = getNavController()
    SideEffect { uiController.setNavigationBarColor(Color.White) }


    val viewModel = getNavViewModel<MainViewModel>(
        parameters = { parametersOf(NetworkChecker(context).isInternetConnected, 1) }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 0.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                CategoryList(
                    dataState = viewModel.categoryList,
                    onCategorySelected = { categoryId ->
                        viewModel.updateCategory(categoryId)
                    }
                )
            }

        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(
                items = viewModel.itemsData.value,
                key = { item -> item.id }
            ) { dataItem ->
                DataCardView(dataItem = dataItem, onRemoveItemClicked = {
                    val id = it
                    val title = dataItem.titile
                    navigation.navigate(
                        MyScreen.MeetingScreen.rout +  "/$id/$title"
                    )
                })
            }
        }
    }
}

@Composable
fun CategoryList(
    dataState: MutableState<List<Category>>,
    onCategorySelected: (Int) -> Unit
) {
    val selectedCategory = remember { mutableStateOf<Category?>(null) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(dataState.value) { category ->
            val isSelected = selectedCategory.value == category

            Card(
                modifier = Modifier
                    .width(120.dp)
                    .clickable {
                        selectedCategory.value = category
                        onCategorySelected(category.id)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Unspecified,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = category.titile,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = if (isSelected) Color.Green else Color.Gray,
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun DataCardView(dataItem: Items, onRemoveItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .size(width = 60.dp, height = 250.dp)
            .padding(8.dp)
            .clickable {
                onRemoveItemClicked.invoke(dataItem.id.toString())
            },
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {


            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    AsyncImage(
                        model = dataItem.image, contentDescription = "data",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .width(180.dp)
                            .height(135.dp)
                    )


                    Spacer(modifier = Modifier.height(20.dp))


                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .border(1.dp, Color.Green, RoundedCornerShape(16.dp))
                            .padding(bottom = 3.dp, top = 3.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = dataItem.titile,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color.Black,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(1.dp))

                            Text(
                                text = "تعداد جلسات: ${dataItem.sessions}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }


            }

        }
    }
}


