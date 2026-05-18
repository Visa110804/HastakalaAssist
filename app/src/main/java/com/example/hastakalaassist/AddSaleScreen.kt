package com.example.hastakalaassist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddSaleScreen() {

    var selectedProduct by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(1) }
    var showMessage by remember { mutableStateOf(false) }

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
            text = "🛒 Add Sale",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Track your artisan products ✨",
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🎨 Product Selection
        Text(
            text = "🎨 Select Product",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ROW 1
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            SelectionChip("🏺 Pottery", selectedProduct) {
                selectedProduct = "🏺 Pottery"
            }

            SelectionChip("🎨 Paintings", selectedProduct) {
                selectedProduct = "🎨 Paintings"
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ROW 2
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            SelectionChip("🪵 Wooden", selectedProduct) {
                selectedProduct = "🪵 Wooden"
            }

            SelectionChip("👜 Bags", selectedProduct) {
                selectedProduct = "👜 Bags"
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ROW 3
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            SelectionChip("🔑 Keychains", selectedProduct) {
                selectedProduct = "🔑 Keychains"
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🌈 Color Selection
        Text(
            text = "🌈 Select Color",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            SelectionChip("🔴 Red", selectedColor) {
                selectedColor = "🔴 Red"
            }

            SelectionChip("🔵 Blue", selectedColor) {
                selectedColor = "🔵 Blue"
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            SelectionChip("🟡 Yellow", selectedColor) {
                selectedColor = "🟡 Yellow"
            }

            SelectionChip("🟢 Green", selectedColor) {
                selectedColor = "🟢 Green"
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 📦 Quantity
        Text(
            text = "📦 Quantity",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    if (quantity > 1) quantity--
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6)
                )
            ) {
                Text("➖")
            }

            Text(
                text = "   $quantity   ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
                    quantity++
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8B5CF6)
                )
            ) {
                Text("➕")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 💾 Save Button
        Button(
            onClick = {

                if (
                    selectedProduct.isNotEmpty() &&
                    selectedColor.isNotEmpty()
                ) {

                    val sale = Sale(
                        selectedProduct,
                        selectedColor,
                        quantity
                    )

                    AppData.sales.add(sale)



                    showMessage = true
                }
            },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7C3AED)
            ),

            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "💾 Save Sale",
                fontSize = 16.sp
            )
        }

        if (showMessage) {

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "✅ Sale Saved Successfully!",
                color = Color(0xFF15803D),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun SelectionChip(
    text: String,
    selected: String,
    onClick: () -> Unit
) {

    val isSelected = text == selected

    val bgColor =
        if (isSelected) Color(0xFF7C3AED)
        else Color.White

    val textColor =
        if (isSelected) Color.White
        else Color.DarkGray

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier.clickable {
            onClick()
        }
    ) {

        Text(
            text = text,
            color = textColor,
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 10.dp
            )
        )
    }
}