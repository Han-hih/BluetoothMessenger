package com.app.bluetoothmessenger_android.ui.model

import androidx.compose.ui.graphics.Color
import com.app.bluetoothmessenger_android.domain.model.ProfileTone

data class ToneColors(
    val fill: Color,
    val accent: Color = fill
)

fun ProfileTone.toToneColors(): ToneColors = when (this) {
    ProfileTone.ORANGE -> ToneColors(fill = Color(0xFFF2A766))
    ProfileTone.SLATE -> ToneColors(fill = Color(0xFF98A2B3))
    ProfileTone.CORAL -> ToneColors(fill = Color(0xFFF06C55))
    ProfileTone.SAND -> ToneColors(fill = Color(0xFFC6A483))
    ProfileTone.BROWN -> ToneColors(fill = Color(0xFF7A574A))
    ProfileTone.BLUE -> ToneColors(fill = Color(0xFF57C6FF))
    ProfileTone.PURPLE -> ToneColors(fill = Color(0xFF6D54E8))
    ProfileTone.GREEN -> ToneColors(fill = Color(0xFF54D39B))
    ProfileTone.YELLOW -> ToneColors(fill = Color(0xFFFFB454))
}
