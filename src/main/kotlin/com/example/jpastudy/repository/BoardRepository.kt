package com.example.jpastudy.repository

import com.example.jpastudy.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BoardRepository : JpaRepository<Board, UUID> {
}