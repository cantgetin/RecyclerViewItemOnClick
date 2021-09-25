package ru.unit6.course.android.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import ru.unit6.course.android.retrofit.ui.main.MainFragment
import ru.unit6.course.android.retrofit.ui.main.UserInfoFragment

class MainActivity : AppCompatActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        AppDatabase.invoke(applicationContext)
    }*/



    val itemClickListener: (View, Int, Int) -> Unit = { view, position, type ->

        //var fragment2 = UserInfoFragment()

        var fragment2 = UserInfoFragment(position, backBtnClickListener)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment2)
            .commitNow()

    }

    var fragment1 = MainFragment(itemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment1)
                .commitNow()
        }

        AppDatabase.invoke(applicationContext)
    }

    val backBtnClickListener: () -> Unit = {

        var fragment1 = MainFragment(itemClickListener)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment1)
            .commitNow()


    }



}