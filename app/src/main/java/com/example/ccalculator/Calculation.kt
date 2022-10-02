package com.example.ccalculator

class Calculation {

    companion object {
        fun calculateResult(input: String) : String {
            var result = ""


            if (!isInputSequenceValid(input)) {
                println(INVALID_INPUT + input)
                return INVALID_INPUT
            }

            var operands = input.split("+", "-", "*", "/")
            val operations = input.filter { Operations.getList().contains(it) }
            println("Operands = " + operands)
            println("Operations = " + operations)

            return result
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
//        return Operations.getOperationsArray().contains(c)

        private fun isCurrentCharAPoint(c: Char) : Boolean = (c == '.')

        private const val INVALID_INPUT = "Error! Invalid input!"
        private const val DIVISION_BY_ZERO = "Error! Division by zero!"
    }
}