package com.example.ccalculator

class Calculation {

    companion object {

        fun calculateResult(input: String) : String {
            var result = 0.0

            if (!isInputSequenceValid(input)) {
                return INVALID_INPUT
            }

            val addAndSubtractOperands = mutableListOf<Double>()
            val addAndSubtractOperations = mutableListOf<Operations>()

            val operands = input.split("+", "-", "*", "/").map { it.toDouble() }.toMutableList()
            val operations = input.filter { Operations.getOperationsList().contains(it) }.map { Operations.getOperation(it.toString()) }

            for (i in 0 until operations.count()) {
                val operation = operations[i]
                val operandOne = operands[i]
                val operandTwo = operands[i+1]

                if (Operations.getOperationPriority(operation) == 1) {
                    addAndSubtractOperands.add(operandOne)
                    addAndSubtractOperations.add(operation)
                } else {
                    try {
                        result = Operations.calculate(
                            operandOne,
                            operandTwo,
                            operation
                        )
                    } catch (e: CalculationThrowable) {
                        return e.errorMessage.toString()
                    }
                    operands[i + 1] = result
                }
            }

            addAndSubtractOperands.add(operands.last())

            for (i in 0 until addAndSubtractOperations.count()) {
                val operation = addAndSubtractOperations[i]
                val operandOne = addAndSubtractOperands[i]
                val operandTwo = addAndSubtractOperands[i+1]

                result = Operations.calculate(
                    operandOne,
                    operandTwo,
                    operation
                )
                addAndSubtractOperands[i+1] = result
            }
            return ("$input = $result")
        }

        private fun isInputSequenceValid(sequence: String) : Boolean {
            val sequenceArray = sequence.toCharArray()
            var isCharAnOperation = false
            var isCharAPoint = false

            if (isCurrentCharAPoint(sequence.first()) || isCurrentCharAnOperation(sequence.first())
                || isCurrentCharAPoint(sequence.last()) || isCurrentCharAnOperation(sequence.last())) {
                return false
            }

            for (c in sequenceArray) {
                if (isCurrentCharAPoint(c)) {
                    if (isCharAPoint) {
                        return false
                    } else {
                        isCharAPoint = true
                        isCharAnOperation = false
                    }
                } else if (isCurrentCharAnOperation(c)) {
                    if (isCharAnOperation) {
                        return false
                    } else {
                        isCharAnOperation = true
                        isCharAPoint = false
                    }
                } else {
                    isCharAPoint = false
                    isCharAnOperation = false
                }
            }
            return true
        }

        private fun isCurrentCharAnOperation(c: Char) : Boolean = Operations.getOperationsList().contains(c)

        private fun isCurrentCharAPoint(c: Char) : Boolean = (c == '.')

        private const val INVALID_INPUT = "Error! Invalid input!"

    }
}