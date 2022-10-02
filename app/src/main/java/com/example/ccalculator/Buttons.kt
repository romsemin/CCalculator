package com.example.ccalculator

enum class Buttons(val properties: DataModel) {
    FIRST(DataModel.ButtonsRow("0", ".",  null, "=", null)),
    SECOND(DataModel.ButtonsRow("1", "2", "3", "+", null)),
    THIRD(DataModel.ButtonsRow("4", "5", "6", "-", null)),
    FOURTH(DataModel.ButtonsRow("7", "8", "9", "x", null)),
    FIFTH(DataModel.ButtonsRow("AC", null, null, "/", null)),
    SIX(DataModel.TextView("_"))
}

