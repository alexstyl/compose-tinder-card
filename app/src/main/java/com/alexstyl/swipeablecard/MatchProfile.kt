package com.alexstyl.swipeablecard

import androidx.annotation.DrawableRes

data class MatchProfile(
    val name: String,
    @DrawableRes val drawableResId: Int,
)

val profiles = listOf(
    MatchProfile("Erlich Bachman", R.drawable.erlich),
    MatchProfile("Richard Hendricks", R.drawable.richard),
    MatchProfile("Laurie Bream", R.drawable.laurie),
    MatchProfile("Russ Hanneman", R.drawable.russ),
    MatchProfile("Dinesh Chugtai", R.drawable.dinesh),
    MatchProfile("Monica Hall", R.drawable.monica),
    MatchProfile("Bertram Gilfoyle", R.drawable.gilfoyle),

    MatchProfile("Peter Gregory", R.drawable.peter),
    MatchProfile("Jared Dunn", R.drawable.jared),
    MatchProfile("Nelson Bighetti", R.drawable.big_head),
    MatchProfile("Gavin Belson", R.drawable.gavin),
    MatchProfile("Jian Yang", R.drawable.jian),
    MatchProfile("Jack Barker", R.drawable.barker),
)