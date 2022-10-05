package com.example.ccalculator

import java.io.IOException

enum class Operations (val raw: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    companion object {

        @Throws(CalculationThrowable::class)
        fun calculate(operandOne: Double, operandTwo: Double, operation: Operations) : Double {
            if (operation == DIVIDE && operandTwo == 0.0) {
                throw CalculationThrowable(
                    CalculationError.DIVISION_BY_ZERO,
                    DIVISION_BY_ZERO
                )
            } else {
                return when (operation) {
                    ADD -> (operandOne + operandTwo)
                    SUBTRACT -> (operandOne - operandTwo)
                    MULTIPLY -> (operandOne * operandTwo)
                    DIVIDE -> (operandOne / operandTwo)
                }
            }
        }

        fun getOperation(operation: String) : Operations {
            return when(operation) {
                "+" -> ADD
                "-" -> SUBTRACT
                "*" -> MULTIPLY
                "/" -> DIVIDE
                else -> ADD
            }
        }

        fun getOperationsList(): List<Char> = values().map { it.raw }

        fun getOperationPriority(operation: Operations) : Int {
            return when(operation) {
                ADD, SUBTRACT -> 1
                MULTIPLY, DIVIDE -> 2
            }
        }

        private const val DIVISION_BY_ZERO = "Error! Division by zero!"
    }
}


