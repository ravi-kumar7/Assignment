package com.android.assignment.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithFacts(
    @Embedded val category:Category,
            @Relation(
                parentColumn = "title",
                entityColumn = "category"
            ) val fact: List<Fact>
)