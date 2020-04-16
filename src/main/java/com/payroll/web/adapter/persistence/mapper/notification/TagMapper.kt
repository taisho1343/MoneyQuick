package com.payroll.web.adapter.persistence.mapper.notification

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component

@Suppress("FunctionName")
@Component
@Mapper
interface TagMapper {

    fun selectTAGS_byTagId(tagId: Long): TAGS?

}

data class TAGS(
        val tagId: Long,
        val tagLabel: String
)