package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun InfoScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.tietoa_sovelluksesta),
            style = MaterialTheme.typography.titleLarge
        )

        InfoCard(
            title = stringResource(R.string.miksi_t_m_ppi),
            body = stringResource(R.string.kurssi_mobiiliohjelmointi_natiiviteknologioilla) +
                    stringResource(R.string.oamk_insin_riopinnot_erik_ohjelmistokehitys) +
                    stringResource(R.string.tekij_merja_sotkasiira)
        )

        InfoCard(
            title = stringResource(R.string.mit_sovellus_tekee),
            body = stringResource(R.string.n_ytt_nykyisen_s_n_laitteen_sijainnin_perusteella)
        )

        InfoCard(
            title = stringResource(R.string.data),
            body = stringResource(R.string.s_tiedot_haetaan_open_meteo_api_sta_ei_api_avainta)
        )

        InfoCard(
            title = stringResource(R.string.tekniikat),
            body = stringResource(R.string.kotlin_jetpack_compose) +
                    stringResource(R.string.navigation_home_info) +
                    stringResource(R.string.mvvm_viewmodel_repository) +
                    stringResource(R.string.ktor_kotlinx_serialization_http_json) +
                    stringResource(R.string.fused_location_provider_sijainti)
        )

        InfoCard(
            title = stringResource(R.string.toiminnallisuudet),
            body = stringResource(R.string.automaattinen_haku_luvan_j_lkeen) +
                    stringResource(R.string.latausindikaattori_haun_aikana) +
                    stringResource(R.string.virheilmoitus_jos_sijaintia_tai_dataa_ei_saada)
        )

        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.versio_1_0),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun InfoCard(title: String, body: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(text = body, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
