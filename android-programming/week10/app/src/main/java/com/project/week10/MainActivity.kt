package com.project.week10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment // 프래그먼트 가져오기
        val navController = navHostFragment.navController
        val topDest = setOf(R.id.home2, R.id.frag1, R.id.frag2)
        appBarConfiguration = AppBarConfiguration(topDest, drawerLayout) // 햄버거 버튼
//        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout) // 앱 바와 연결시키기, up 버튼
        setupActionBarWithNavController(navController, appBarConfiguration) // 타이틀 바도 자동으로 바뀌게 됨

        // 메뉴 눌렀을 때 액션
        val navView= findViewById<NavigationView>(R.id.nav_view)
        navView.setupWithNavController(navController)
    }

    // Frag1, Frag2 에서 백 버튼 누르면 Home 으로 가도록
    override fun onSupportNavigateUp() : Boolean {
        val navController = findNavController(R.id.fragmentContainerView) // NavController 가져오기
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // 메뉴가 표시되기 위해 작성
    override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
        menuInflater.inflate(R.menu.main_menu, menu) // 인자로 넘어온 menu 에 정의한 리소스를 inflate
        return true
    }

    // 메뉴가 선택되었을 때 액션하기 위해 작성
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.frag1, R.id.frag2 ->
                item.onNavDestinationSelected(findNavController(R.id.fragmentContainerView))
            else -> return super.onOptionsItemSelected(item)
        }


        // 제목으로 선택하는 경우
        // when(item.title) {
        // "Item" -> println("item 선택")
        // "Item2" -> println("item2 선택")
        // "Item3" -> println("item3 선택")

        // id로 선택하는 경우
        // when(item.itemId) {
        //     R.id.home2 -> MyDialogFragment().show(supportFragmentManager, "MyDialog") // Dialog 시작
        //     R.id.frag1 -> println("item2 선택")
        //     R.id.frag2 -> println("item3 선택")
        //     else -> return super.onOptionsItemSelected(item)
        // }



        return true
    }
}


