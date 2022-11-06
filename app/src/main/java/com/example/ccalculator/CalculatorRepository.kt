package com.example.ccalculator

class CalculatorRepository {

    private val buttonsList : MutableList<ButtonRow> = mutableListOf(
        ButtonRow("AC", null, null, Operation.DIVIDE.raw.toString()),
        ButtonRow("7", "8", "9", Operation.MULTIPLY.raw.toString()),
        ButtonRow("4", "5", "6", Operation.SUBTRACT.raw.toString()),
        ButtonRow("1", "2", "3", Operation.ADD.raw.toString()),
        ButtonRow("0", ".",  null, "=")
    )

    fun getButtonsList() = buttonsList
}