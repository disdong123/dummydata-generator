package kr.disdong.dummydata.generator.persistence.module.post.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime

@Entity(name = "post")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val userId: Long,

    @Column(
        nullable = false,
        unique = false,
        length = 100,
    )
    val name: String,

    @Column(
        nullable = false,
        unique = false,
        length = 20,
    )
    val phone: String,

    @Column
    @CreatedDate
    val createdAt: ZonedDateTime = ZonedDateTime.now(),

    @Column
    @LastModifiedDate
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
)
