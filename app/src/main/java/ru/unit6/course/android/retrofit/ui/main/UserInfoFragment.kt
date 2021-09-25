package ru.unit6.course.android.retrofit.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.api.ApiHelper
import ru.unit6.course.android.retrofit.data.api.RetrofitBuilder
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.data.repository.MainRepository
import kotlinx.coroutines.launch
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import ru.unit6.course.android.retrofit.data.model.UserDB


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private var ARG_PARAM1 = 1
private const val ARG_PARAM2 = "param2"
private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
private val appDatabase = AppDatabase.getDatabase()

suspend fun getUserFromDB(id: Int) : UserDB = appDatabase.userDao().getUser(id)



class UserInfoFragment(param21: Int, val bntBackClick: () -> Unit) : Fragment() {
    private var lol = param21
    // TODO: Rename and change types of parameters
    private var selectedId: Int? = null
    private var param2: String? = null
    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val mainRepository: MainRepository = MainRepository(apiHelper)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //selectedId = ARG_PARAM1
            ARG_PARAM1 = lol
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.userinfo_fragment, container, false)



        var name = view.findViewById<TextView>(R.id.userName)
        var id = view.findViewById<TextView>(R.id.userId)
        var email = view.findViewById<TextView>(R.id.userEmail)
        var avatar = view.findViewById<ImageView>(R.id.userAvatar)



        lifecycleScope.launch {
            var user = getUserFromDB(lol)

            name.text = "Name: " + user.name
            id.text = "Id: " + user.id
            email.text = "Email: " + user.email

            Glide.with(view).load(user.avatar).into(avatar);


            var btn = view.findViewById<Button>(R.id.btnBack)

            btn.setOnClickListener {
                bntBackClick()
            }

        }


        return view
    }

}