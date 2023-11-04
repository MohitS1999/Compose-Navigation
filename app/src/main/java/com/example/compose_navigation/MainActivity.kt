package com.example.compose_navigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose_navigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration"){
        composable("registration"){
            RegistrationScreen {
                navController.navigate("main/${it}")
            }
        }
        composable("login"){
            LoginScreen()
        }
        composable("main/{email}", arguments = listOf(
            navArgument("email"){
                type = NavType.StringType
            }
        )){
            val email = it.arguments?.getString("email")
            MainScreen(email!!)
        }
    }
}

@Composable
fun RegistrationScreen(onClick : (email:String)->Unit){
    Text(text = "Registration",style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.clickable {
            onClick("8810mohit@gmail.com")
        })

}
@Composable
fun LoginScreen(){
    Text(text = "Login",style = MaterialTheme.typography.headlineMedium)

}
@Composable
fun MainScreen(email: String){
    Text(text = "Main - $email",style = MaterialTheme.typography.headlineMedium)

}