package com.example.jpastudy.entity

import jakarta.persistence.*

@Entity
@Table(name = "`user`")
class User(
    name: String,
) : PrimaryKeyEntity() {
    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "writer")
    protected val mutableBoards: MutableList<Board> = mutableListOf()
    val boards: List<Board> get() = mutableBoards.toList()

    fun writeBoard(board: Board) {
        mutableBoards.add(board)
    }
}