package com.example.cookapplite.RecipeFeature.domain

import java.util.*

data class Recipe (
    var uuid : UUID,
    var title : String,
    var author : String,
    var recipe : String,
)
{}