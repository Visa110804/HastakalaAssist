package com.example.hastakalaassist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
fun DashboardScreen() {

    val sales = AppData.sales

    val totalSales = sales.sumOf { it.quantity }

    val bestSeller = sales
        .groupBy { it.product }
        .maxByOrNull { it.value.sumOf { sale -> sale.quantity } }
        ?.key ?: "None"

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
            text = "✨ Dashboard",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Your business at a glance 💼",
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🏆 Top Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                emoji = "🏆",
                title = "Best Seller",
                value = bestSeller,
                bgColor = Color(0xFFD1FAE5)
            )

            DashboardCard(
                emoji = "💰",
                title = "Today",
                value = "$totalSales items",
                bgColor = Color(0xFFDBEAFE)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // ⚡ Insights Card
        DashboardCard(
            emoji = "⚡",
            title = "Insights",
            value = if (sales.isEmpty())
                "Add your first sale 👀"
            else
                "Focus more on $bestSeller 🔥",
            bgColor = Color(0xFFFFEDD5),
            fullWidth = true
        )

        Spacer(modifier = Modifier.height(22.dp))

        // 📊 Sales Overview
        Card(
            shape = RoundedCornerShape(22.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "📊 Sales Overview",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 🎯 Fake Pie Chart Illustration
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    // Pie Illustration
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(120.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.sweepGradient(
                                        listOf(
                                            Color(0xFF60A5FA),
                                            Color(0xFF34D399),
                                            Color(0xFFFBBF24),
                                            Color(0xFFF87171),
                                            Color(0xFF60A5FA)
                                        )
                                    ),
                                    shape = CircleShape
                                )
                        )

                        Box(
                            modifier = Modifier
                                .size(55.dp)
                                .background(Color.White, CircleShape)
                        )
                    }

                    // Legend
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        LegendItem("👜 Bags", Color(0xFF60A5FA))
                        LegendItem("🏺 Pottery", Color(0xFF34D399))
                        LegendItem("🎨 Paintings", Color(0xFFFBBF24))
                        LegendItem("🪵 Wooden", Color(0xFFF87171))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 📦 Product Breakdown
                if (sales.isEmpty()) {

                    Text(
                        "No sales data yet 👀",
                        color = Color.Gray
                    )

                } else {

                    sales.groupBy { it.product }
                        .forEach { (product, productSales) ->

                            val count = productSales.sumOf { it.quantity }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(product)

                                Text(
                                    "$count sold 🔥",
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                }
            }
        }
    }
}

@Composable
fun DashboardCard(
    emoji: String,
    title: String,
    value: String,
    bgColor: Color,
    fullWidth: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.then(
            if (fullWidth) Modifier.fillMaxWidth()
            else Modifier.width(120.dp)
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "$emoji $title",
                fontSize = 13.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun LegendItem(name: String, color: Color) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(name)
    }
}