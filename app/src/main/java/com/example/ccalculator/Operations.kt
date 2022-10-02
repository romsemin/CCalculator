package com.example.ccalculator

enum class Operations (val raw: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    companion object {
        fun calculate(operandOne: Double, operandTwo: Double, operation: Operations) : Double {
            return when(operation) {
                Operations.ADD -> (operandOne + operandTwo)
                Operations.SUBTRACT -> (operandOne - operandTwo)
                Operations.MULTIPLY -> (operandOne * operandTwo)
                Operations.DIVIDE -> (operandOne / operandTwo)
            }
        }

        fun getOperationsArray() : Array<Char> {
            val operationsArray = mutableListOf<Char>()
            Operations.values().forEach {
                operationsArray.add(it.raw)
            }
            return operationsArray.toTypedArray()
        }

        fun getList(): List<Char> {
            return values().map { it.raw }
        }
    }
}


