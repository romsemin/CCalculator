package com.example.ccalculator

sealed class DataModel {
    data class ButtonsRow(
        var firstButton: String?,
        var secondButton: String?,
        var thirdButton: String?,
        var fourthButton: String?,
        var textField: String?
    ) : DataModel()

    data class TextView(
        var text: String
    ) : DataModel()
}
