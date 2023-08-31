package com.example.jpastudy

import com.example.jpastudy.entity.*
import com.example.jpastudy.repository.BoardRepository
import com.example.jpastudy.repository.TagRepository
import com.example.jpastudy.repository.UserRepository
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class BasicTest(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val boardRepository: BoardRepository,
    @Autowired private val tagRepository: TagRepository
) {

    @Test
    fun 유저_저장() {
        var user1 = User("user1")

        var user2 = User("user2")

        userRepository.saveAll(listOf(user1, user2))

        val findAllUser = userRepository.findAll()

        Assertions.assertThat(findAllUser).hasSize(2)
    }

    @Test
    fun 게시판_생성(){
        var user = User("user")

        val savedUser = userRepository.save(user)

        val board = Board("title", "content", BoardInformation(null, 1), savedUser, setOf())

        board.addComment(Comment("comment", savedUser))

        boardRepository.save(board)

        val findAllBoard = boardRepository.findAll()

        val foundBoard = findAllBoard.get(0)

        foundBoard.addComment(Comment("comment2", savedUser))

        Assertions.assertThat(foundBoard.comments).hasSize(2)
    }

    @Test
    fun 게시판에_태그_추가(){
        var user = User("user")

        val savedUser = userRepository.save(user)

        val board = Board("title", "content", BoardInformation(null, 1), savedUser, setOf())

        board.addTag(tagRepository.save(Tag("key2","value2")))

        boardRepository.save(board)

        val findAllBoard = boardRepository.findAll()

        val foundBoard = findAllBoard.get(0)

        foundBoard.addTag(tagRepository.save(Tag("key2","value2")))

        Assertions.assertThat(foundBoard.tags).hasSize(2)
    }
}