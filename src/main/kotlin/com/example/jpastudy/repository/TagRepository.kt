package com.example.jpastudy.repository

import com.example.jpastudy.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TagRepository: JpaRepository<Tag, UUID> {
}