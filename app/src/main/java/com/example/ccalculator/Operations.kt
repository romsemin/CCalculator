package com.example.ccalculator

enum class Operations(val raw: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    companion object {

        @Throws(CalculationThrowable::class)
        fun Operations.calculate(operandOne: Double,
                      operandTwo: Double,
        ) : Double {
            if (this == DIVIDE && operandTwo == 0.0) {
                throw CalculationThrowable(
                    CalculationError.DIVISION_BY_ZERO,
                    DIVISION_BY_ZERO
                )
            } else {
                return when (this) {
                    ADD -> (operandOne.plus(operandTwo))
                    SUBTRACT -> (operandOne.minus(operandTwo))
                    MULTIPLY -> (operandOne.times(operandTwo))
                    DIVIDE -> (operandOne.div(operandTwo))
                }
            }
        }

        @Throws(CalculationThrowable::class)
        fun String.getOperation() : Operations {
            return when(this) {
                "+" -> ADD
                "-" -> SUBTRACT
                "*"-> MULTIPLY
                "/" -> DIVIDE
                else -> throw CalculationThrowable(CalculationError.UNKNOWN_OPERATION)
            }
        }

        fun getOperationsCharList(): List<Char> = values().map { it.raw }

        fun Operations.getOperationPriority() : Int {
            return when(this) {
                ADD, SUBTRACT -> 1
                MULTIPLY, DIVIDE -> 2
            }
        }

        private const val DIVISION_BY_ZERO = "Error! Division by zero!"
    }
}


