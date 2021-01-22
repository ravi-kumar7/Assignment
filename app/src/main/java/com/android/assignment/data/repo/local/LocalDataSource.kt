package com.android.assignment.data.repo.local

import com.android.assignment.data.model.Category
import com.android.assignment.data.model.Response
import com.android.assignment.data.model.State
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val factDao: FactDao) {

    fun saveData(response: Response)
    {
        factDao.insertCategory(Category(response.title))
        val facts = response.rows.filter {
            !it.title.isNullOrBlank()
        }.map {
            it.category = response.title
            return@map it
        }
        factDao.insertFacts(facts)
    }

    fun getCategoryWithFacts(): State {
        val res = factDao.getCategoryWithFacts()
        return if(res==null)
            State.Error(-1)
        else
            State.Success(res)
    }
}