package com.example.jpastudy.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "tag_key_value_uk", columnNames = ["`key`", "`value`"])])
class Tag(
    key: String,
    value: String,
) : PrimaryKeyEntity() {
    @Column(name = "`key`", nullable = false)
    var key: String = key
        protected set

    @Column(name = "`value`", nullable = false)
    var value: String = value
        protected set
}