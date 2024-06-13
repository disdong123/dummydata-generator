package kr.disdong.dummydata.generator.persistence.module.user.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.ZonedDateTime

@Entity(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(
        nullable = false,
        unique = false,
        length = 100,
    )
    var name: String,

    @Column(
        nullable = false,
        unique = false,
        length = 20,
    )
    val phone: String,
    @Column
    @CreatedDate
    var createdAt: ZonedDateTime = ZonedDateTime.now(),

    @Column
    @LastModifiedDate
    var updatedAt: ZonedDateTime = ZonedDateTime.now(),
)

@Entity(name = "post")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(
        nullable = false,
        unique = false,
        length = 100,
    )
    var name: String,

    @Column(
        nullable = false,
        unique = false,
        length = 20,
    )
    val phone: String,

    @Column
    @CreatedDate
    var createdAt: ZonedDateTime = ZonedDateTime.now(),

    @Column
    @LastModifiedDate
    var updatedAt: ZonedDateTime = ZonedDateTime.now(),
)
