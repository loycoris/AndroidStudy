package com.android.room2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class Student() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int = 0

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    lateinit var name: String

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    var age: Int = 0

    constructor(_id: Int) : this() {
        id = _id
    }

    constructor(_name: String, _age: Int) : this() {
        name = _name
        age = _age
    }

    constructor(_id: Int, _name: String, _age: Int) : this() {
        id = _id
        name = _name
        age = _age
    }
}
