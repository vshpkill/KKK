package com.kkk.kotlin

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        but_kotlin.setOnClickListener { showToast("哈哈你好kotlin") }
        val rl_list = findViewById(R.id.rl_list) as RecyclerView
        rl_list.layoutManager = LinearLayoutManager(this)

    }

    fun showToast(content : String,type : Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,content,type).show()
    }
}
