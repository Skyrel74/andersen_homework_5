package ru.skyrel74.andersen_homework_5

import androidx.appcompat.app.AppCompatActivity

const val ROOT_TAG = "ROOT_TAG"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStackImmediate(ROOT_TAG, 0)
    }
}