package com.example.ccalculator

enum class Operation(val raw: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    companion object {

        @Throws(OperationThrowable::class)
        fun Operation.calculate(
            operandOne: Double,
            operandTwo: Double,
        ): Double {
            return when (this) {
                ADD -> (operandOne.plus(operandTwo))
                SUBTRACT -> (operandOne.minus(operandTwo))
                MULTIPLY -> (operandOne.times(operandTwo))
                DIVIDE -> {
                    when (operandTwo) {
                        0.0 -> {
                            throw OperationThrowable(
                                OperationError.DIVISION_BY_ZERO,
                                DIVISION_BY_ZERO
                            )
                        }
                        else -> (operandOne.div(operandTwo))
                    }
                }
            }
        }

        @Throws(OperationThrowable::class)
        fun String.getOperation(): Operation {
            return when (this) {
                "+" -> ADD
                "-" -> SUBTRACT
                "*" -> MULTIPLY
                "/" -> DIVIDE
                else -> throw OperationThrowable(OperationError.UNKNOWN_OPERATION)
            }
        }

        fun getOperationsCharList(): List<Char> = values().map { it.raw }

        fun Operation.getOperationPriority(): Int {
            return when (this) {
                ADD, SUBTRACT -> 1
                MULTIPLY, DIVIDE -> 2
            }
        }

        private const val DIVISION_BY_ZERO = "Error! Division by zero!"
    }
}


