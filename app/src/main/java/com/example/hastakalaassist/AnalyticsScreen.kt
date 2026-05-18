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
fun AnalyticsScreen() {

    val sales = AppData.sales

    val totalSales = sales.sumOf { it.quantity }

    val bestSeller = sales
        .groupBy { it.product }
        .maxByOrNull { it.value.sumOf { sale -> sale.quantity } }
        ?.key ?: "None"

    val productBreakdown = sales.groupBy { it.product }

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
            text = "📊 Analytics",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Track your sales performance 🚀",
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 📈 Summary Card
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE0F2FE)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "📦 Total Items Sold",
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "$totalSales",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "🏆 Best Seller",
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = bestSeller,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 🥧 Pie Chart Card
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "🥧 Product Distribution",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 🎯 Fake Pie Chart
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(140.dp)
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
                            .size(60.dp)
                            .background(Color.White, CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                AnalyticsLegendItem(
                    "🏺 Pottery",
                    Color(0xFF60A5FA)
                )

                Spacer(modifier = Modifier.height(8.dp))

                AnalyticsLegendItem(
                    "🎨 Paintings",
                    Color(0xFF34D399)
                )

                Spacer(modifier = Modifier.height(8.dp))

                AnalyticsLegendItem(
                    "🪵 Wooden",
                    Color(0xFFFBBF24)
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        // 📋 Product Breakdown
        Text(
            text = "📋 Product Breakdown",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (sales.isEmpty()) {

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "😕 No sales data yet",
                    modifier = Modifier.padding(20.dp),
                    color = Color.Gray
                )
            }

        } else {

            productBreakdown.forEach { (product, list) ->

                val count = list.sumOf { it.quantity }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),

                    shape = RoundedCornerShape(18.dp),

                    elevation = CardDefaults.cardElevation(5.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),

                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = product,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = "$count sold 🔥",
                            color = Color(0xFF7C3AED),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnalyticsLegendItem(
    name: String,
    color: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(14.dp)
                .background(color, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(name)
    }
}