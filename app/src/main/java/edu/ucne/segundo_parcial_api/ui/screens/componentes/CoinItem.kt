package edu.ucne.segundo_parcial_api.ui.screens.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.ucne.segundo_parcial_api.data.dto.CoinDto
import java.text.DecimalFormat

@Composable
fun CoinItem(
    coin: CoinDto,
    onClick : (CoinDto) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 2.dp).fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .clickable { onClick(coin) }.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row{
                AsyncImage(modifier = Modifier.size(55.dp),
                    model = ImageRequest.Builder(LocalContext.current).data(coin.imageUrl)
                        .crossfade(true).build(),
                    contentDescription = coin.descripcion,
                )

                Spacer(modifier = Modifier.width(10.dp))
                Text(text = coin.descripcion, fontWeight = FontWeight.Bold)
            }

            val decimal = DecimalFormat("#,###.######")

            Text(
                text = "$" + decimal.format(coin.valor?.toDouble()), color = Color.Blue, fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End
            )
        }
    }
}