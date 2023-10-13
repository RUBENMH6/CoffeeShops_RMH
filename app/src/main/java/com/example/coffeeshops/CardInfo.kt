package com.example.coffeeshops

data class CardInfo(val image: Int, val name: String, val adress: String)

fun getCardList(): MutableList<CardInfo> {
    return mutableListOf(
        CardInfo(R.drawable.images, "Antico Caffè Grego", "St. Italy, Rome"),
        CardInfo(R.drawable.images1, "Coffee Room", "St. Germany, Berlin"),
        CardInfo(R.drawable.images2, "Coffee Ibiza", "St. Colon, Madrid"),
        CardInfo(R.drawable.images3, "Pudding Coffee Shop", "St. Diagonal, Barclona"),
        CardInfo(R.drawable.images4, "L'Express", "St. Picadilly Circus, London"),
        CardInfo(R.drawable.images5, "Coffee Corner", "St. Àngel Guimerà, Valencia"),
        CardInfo(R.drawable.images6, "Sweet Cup", "St. Kinkerstraat, Amsterdam")
    )
}

