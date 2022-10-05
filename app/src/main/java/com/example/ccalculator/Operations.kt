package com.example.ccalculator

enum class Operations (val raw: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    companion object {
        fun calculate(operandOne: Double, operandTwo: Double, operation: Operations) : Double {
            return when(operation) {
                ADD -> (operandOne + operandTwo)
                SUBTRACT -> (operandOne - operandTwo)
                MULTIPLY -> (operandOne * operandTwo)
                DIVIDE -> (operandOne / operandTwo)
            }
        }

        fun getOperationsList(): List<Char> = values().map { it.raw }

        fun getOperation(operation: String) : Operations {
            return when(operation) {
                "+" -> ADD
                "-" -> SUBTRACT
                "*" -> MULTIPLY
                "/" -> DIVIDE
                else -> ADD
            }
        }

        fun getOperationPriority(operation: Operations) : Int {
            return when(operation) {
                ADD, SUBTRACT -> 1
                MULTIPLY, DIVIDE -> 2
            }
        }

    }
}


