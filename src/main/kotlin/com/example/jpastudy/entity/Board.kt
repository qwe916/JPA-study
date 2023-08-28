package com.example.jpastudy.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
class Board(
    title: String,
    content: String,
    information: BoardInformation,
    writer: User,
    tags: Set<Tag>,
) : PrimaryKeyEntity() {
    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false, length = 3000)
    var content: String = content
        protected set

    @Embedded
    var information: BoardInformation = information
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var writer: User = writer
        protected set

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "board_tag_assoc",
        joinColumns = [JoinColumn(name = "board_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")],
    )
    protected val mutableTags: MutableSet<Tag> = tags.toMutableSet()
    val tags: Set<Tag> get() = mutableTags.toSet()

    @ElementCollection
    @CollectionTable(name = "board_comment")
    private val mutableComments: MutableList<Comment> = mutableListOf()
    val comments: List<Comment> get() = mutableComments.toList()

    fun addTag(tag: Tag) {
        mutableTags.add(tag)
    }

    fun removeTag(tagId: UUID) {
        mutableTags.removeIf { it.id == tagId }
    }

    fun addComment(comment: Comment) {
        mutableComments.add(comment)
    }

    init {
        writer.writeBoard(this)
    }
}

@Embeddable
data class BoardInformation(
    @Column
    val link: String?,

    @Column(nullable = false)
    val _rank: Int,
)

@Embeddable
data class Comment(
    @Column(length = 3000)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    val writer: User,
)