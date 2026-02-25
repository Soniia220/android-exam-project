import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pgr208_eksamen_2025.data.api.AnimeApiModel
import com.example.pgr208_eksamen_2025.components.AnimeItem

// ===HOME LIST===

// Viser anime som et grid-oppsett med to elementer per rad
@Composable
fun AnimeHomeList(animeList: List<AnimeApiModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Grupperer listen i rader med to elementer
        val rows = animeList.chunked(2)

        items(rows) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Tegn hver anime i raden
                for (anime in rowItems) {
                    AnimeItem(
                        anime = anime,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Fyller ut tom plass hvis raden kun har en anime
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}