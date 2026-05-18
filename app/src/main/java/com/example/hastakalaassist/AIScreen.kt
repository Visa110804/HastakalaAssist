package com.example.hastakalaassist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AIScreen() {

    // 🤖 AI Insights
    val insights = listOf(
        "🏺 Pottery products are trending this week 🔥",
        "🎨 Paintings sales increased by 20% 📈",
        "🪵 Wooden items have steady demand 💼",
        "👜 Bags are performing well in red color ❤️",
        "🔑 Keychains need more promotion 📣"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF3B0),
                        Color(0xFFD6EAF8)
                    )
                )
            )
            .padding(16.dp)
    ) {

        // ✨ Header
        Text(
            text = "🤖 AI Insights",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Smart business suggestions powered by AI ✨",
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 📊 AI Score Card
        Card(
            shape = RoundedCornerShape(22.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEDE9FE)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "🚀 Business Growth Score",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "87%",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7C3AED)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Your shop performance looks great 🎉",
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 💡 Insights Title
        Text(
            text = "💡 Personalized Suggestions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        // 🎯 Insight Cards
        insights.forEachIndexed { index, insight ->

            val cardColor = when (index % 5) {
                0 -> Color(0xFFD1FAE5)
                1 -> Color(0xFFDBEAFE)
                2 -> Color(0xFFFFEDD5)
                3 -> Color(0xFFFCE7F3)
                else -> Color(0xFFEDE9FE)
            }

            InsightCard(
                text = insight,
                bgColor = cardColor
            )

            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun InsightCard(
    text: String,
    bgColor: Color
) {

    Card(
        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(6.dp),

        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),

        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "✨",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}