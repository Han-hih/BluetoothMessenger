package com.app.bluetoothmessenger_android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.bluetoothmessenger_android.domain.model.ChatPreview
import com.app.bluetoothmessenger_android.domain.model.ConnectedPerson
import com.app.bluetoothmessenger_android.presentation.home.HomeUiState
import com.app.bluetoothmessenger_android.ui.model.toToneColors

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    onBluetoothClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF2FA7F7)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = "Hi, Inho!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.72f)
                )
                Text(
                    text = "현재 연결된 친구",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "${uiState.connectedPeople.count { it.isConnected }}명 연결됨",
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFDDF4FF)
                )
            }
            IconButton(
                onClick = onBluetoothClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(
                        color = Color.White.copy(alpha = 0.14f),
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Bluetooth,
                    contentDescription = "주변 블루투스 보기",
                    tint = Color.White
                )
            }
        }

        ConnectedPeopleRow(
            people = uiState.connectedPeople,
            modifier = Modifier.padding(bottom = 18.dp)
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF8F7FD),
            shape = RoundedCornerShape(topStart = 34.dp, topEnd = 34.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                FilterRow()
                Text(
                    text = "채팅 목록",
                    modifier = Modifier.padding(top = 20.dp, bottom = 14.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(uiState.chats) { chat ->
                        ChatListItem(chat = chat)
                    }
                }
            }
        }
    }
}

@Composable
private fun ConnectedPeopleRow(
    people: List<ConnectedPerson>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Connected Now",
            modifier = Modifier.padding(horizontal = 24.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.76f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            people.forEach { person ->
                ConnectedPersonChip(person = person)
            }
        }
    }
}

@Composable
private fun ConnectedPersonChip(person: ConnectedPerson) {
    val tone = person.profileTone.toToneColors()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(72.dp)
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(tone.fill),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = person.initials,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .padding(end = 0.dp, bottom = 0.dp)
                    .size(16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (person.isConnected) Color(0xFF58DD8E) else Color(0xFFF1C94A))
            )
        }
        Text(
            text = person.name,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun FilterRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(shape = RoundedCornerShape(18.dp), color = Color.White, tonalElevation = 2.dp) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier.padding(12.dp),
                tint = Color(0xFF232234)
            )
        }
        FilterChip(label = "직접 메시지", count = 6, selected = true)
        FilterChip(label = "그룹", count = 2, selected = false)
    }
}

@Composable
private fun FilterChip(
    label: String,
    count: Int,
    selected: Boolean
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = if (selected) Color(0xFFF3E55A) else Color.White,
        tonalElevation = if (selected) 0.dp else 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, color = Color(0xFF2C2A39), fontWeight = FontWeight.SemiBold)
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF2B2740)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun ChatListItem(chat: ChatPreview) {
    val tone = chat.profileTone.toToneColors()

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(tone.fill),
                contentAlignment = Alignment.Center
            ) {
                Text(text = chat.initials, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = chat.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = chat.time,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF948FA8)
                    )
                }
                Text(
                    text = chat.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6F6A83),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (chat.unreadCount > 0) {
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFFF5D8F)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chat.unreadCount.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.GraphicEq,
                    contentDescription = null,
                    tint = Color(0xFF6D54E8)
                )
            }
        }
    }
}
