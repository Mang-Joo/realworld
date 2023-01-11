package realworld.mangjoo.user.adapter.out

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity {
    @Column(name = "created_date")
    @CreatedDate
    val createdDate: LocalDateTime? = null

    @Column(name = "modified_date")
    @LastModifiedDate
    val modifiedDate: LocalDateTime? = null
}
