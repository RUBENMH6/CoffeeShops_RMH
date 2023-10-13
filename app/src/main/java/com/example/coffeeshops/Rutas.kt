package com.example.coffeeshops

sealed class Routes(val route : String){
    object CoffeeShops: Routes("CoffeeShops")
    object CoffeeShop: Routes("CoffeeShop") }