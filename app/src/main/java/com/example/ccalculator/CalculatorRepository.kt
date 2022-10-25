package com.example.ccalculator

class CalculatorRepository {

    private val buttonsList : List<ButtonsRowDataModel> = listOf(
        ButtonsRowDataModel("AC", null, null, Operations.DIVIDE.raw.toString()),
        ButtonsRowDataModel("7", "8", "9", Operations.MULTIPLY.raw.toString()),
        ButtonsRowDataModel("4", "5", "6", Operations.SUBTRACT.raw.toString()),
        ButtonsRowDataModel("1", "2", "3", Operations.ADD.raw.toString()),
        ButtonsRowDataModel("0", ".",  null, "=")
    )

    fun getButtonsList() = buttonsList
}