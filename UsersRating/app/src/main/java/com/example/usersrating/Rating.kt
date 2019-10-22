package com.example.usersrating

class Rating(user: String, rate: Int, comment: String){
    var usuario = user
    var classificacao = rate
    var comentario = comment
    enum class RatingStars {
        One, Two, Three, Four, Five
    }
}