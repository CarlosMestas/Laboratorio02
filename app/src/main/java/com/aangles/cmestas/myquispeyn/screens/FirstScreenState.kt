package com.aangles.cmestas.myquispeyn.screens

import com.aangles.cmestas.myquispeyn.clases.MyItem

data class FirstScreenState (
    val isLoading: Boolean = false,
    val regions: List<MyItem> = emptyList(),
    val error: String = ""
)