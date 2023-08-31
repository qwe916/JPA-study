package com.example.jpastudy.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "`party`")
class Party (
    name: String,
    description: String,
    members: Set<User>,
): PrimaryKeyEntity(){
    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(nullable = false, length = 3000)
    var description: String = description
        protected set

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    protected val mutableMembers: MutableSet<User> = members.toMutableSet()
    val members: Set<User> get() = mutableMembers.toSet()

    fun addMember(member: User) {
        mutableMembers.add(member)
    }

    fun removeMember(memberId: UUID) {
        mutableMembers.removeIf { it.id == memberId }
    }

}