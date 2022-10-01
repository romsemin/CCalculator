package com.example.ccalculator

enum class Buttons(val properties: ButtonsRow) {
    FIRST(ButtonsRow("0", ".",  null, "=")),
    SECOND(ButtonsRow("1", "2", "3", "+")),
    THIRD(ButtonsRow("4", "5", "6", "-")),
    FOURTH(ButtonsRow("7", "8", "9", "x")),
    FIFTH(ButtonsRow("AC", null, null, "/"))
}