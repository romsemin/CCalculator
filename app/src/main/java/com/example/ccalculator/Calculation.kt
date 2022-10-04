package com.example.ccalculator

class Calculation {

    companion object {
        fun calculateResult(input: String) : String {
            var result: Double = 0.0

            if (!isInputSequenceValid(input)) {
                println(INVALID_INPUT + input)
                return INVALID_INPUT
            }

            val addAndSubstractOperands = mutableListOf<Double>()
            val addAndSubstractOperations = mutableListOf<Operations>()

            val operands = input.split("+", "-", "*", "/").map { it.toDouble() }.toMutableList()
            val operations = input.filter { Operations.getList().contains(it) }.map { Operations.getOperation(it.toString()) }
            println("Operands = " + operands)
            println("Operations = " + operations)

            for (i in 0 until operations.count()) {
                val operation = operations[i]
                val operandOne = operands[i]
                val operandTwo = operands[i+1]

                if (Operations.getOperationPriority(operation) == 1) {
                    addAndSubstractOperands.add(operandOne)
                    addAndSubstractOperations.add(operation)
                } else {
                    result = Operations.calculate(
                        operandOne,
                        operandTwo,
                        operation
                    )
                    operands[i + 1] = result
                    println("temp result = " + result)
                }
            }

            addAndSubstractOperands.add(operands.last())

            for (i in 0 until addAndSubstractOperations.count()) {
                val operation = addAndSubstractOperations[i]
                val operandOne = addAndSubstractOperands[i]
                val operandTwo = addAndSubstractOperands[i+1]

                result = Operations.calculate(
                    operandOne,
                    operandTwo,
                    operation
                )
                addAndSubstractOperands[i+1] = result
            }

            println("result = " + result)
            return result.toString()
        }

        private fun isInputSequenceValid(sequence: String) : Boolean {
            val sequenceArray = sequence.toCharArray()
            println(sequenceArray)
            var isCharAnOperation = false
            var isCharAPoint = false

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

        private fun isCurrentCharAnOperation(c: Char) : Boolean = Operations.getList().contains(c)

        private fun isCurrentCharAPoint(c: Char) : Boolean = (c == '.')

        private const val INVALID_INPUT = "Error! Invalid input!"
        private const val DIVISION_BY_ZERO = "Error! Division by zero!"
    }
}