package com.example.jpastudy.repository

import com.example.jpastudy.entity.Party
import org.springframework.data.jpa.repository.JpaRepository

interface PartyRepository: JpaRepository<Party, Long> {
}