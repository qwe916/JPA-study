package com.example.jpastudy.repository

import com.example.jpastudy.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
}