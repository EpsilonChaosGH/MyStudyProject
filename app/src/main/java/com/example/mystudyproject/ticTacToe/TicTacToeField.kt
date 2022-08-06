package com.example.mystudyproject.ticTacToe

enum class Cell {
    PLAYER_1,
    PLAYER_2,
    EMPTY
}
typealias OnFieldChangedListener = (field: TicTacToeField) -> Unit

class TicTacToeField(
    val rows: Int,
    val columns: Int
) {

    private val cells = Array(rows) { Array(columns) { Cell.EMPTY } }

    val listeners = mutableSetOf<OnFieldChangedListener>()
    fun getSell(row: Int, column: Int): Cell {
        if (row < 0 || column < 0 || row >= rows || column >= columns) return Cell.EMPTY
        return cells[row][column]
    }

    fun setSell(row: Int, column: Int, cell: Cell) {
        if (row < 0 || column < 0 || row >= rows || column >= columns) return
        if (cells[row][column] != cell) {
            cells[row][column] = cell
            listeners.forEach { it.invoke(this) }
        }
    }
}