package com.example.live_broadcasting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity(),onLiveClick {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var RecyclerView: RecyclerView
    private lateinit var mList: ArrayList<ItemsViewModel>
    private lateinit var mDbRef: DatabaseReference
    private lateinit var adapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var CustomAdapter=CustomAdapter(mList,this)
        mAuth=FirebaseAuth.getInstance()
        mDbRef= FirebaseDatabase.getInstance().getReference()

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        CustomAdapter.notifyDataSetChanged()
        val data = ArrayList<ItemsViewModel>()


        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel(R.drawable.game, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter


        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            //write logic for logout
            mAuth.signOut()
            val intent = Intent(this@MainActivity, login::class.java)
            finish()
            startActivity(intent)
            return true

        }
        return true
    }

    override fun onLiveItemClicked(position: Int) {
    val intent=Intent(this,broadcast::class.java)
        intent.putExtra("game",mList[position].text)
        startActivity(intent)
    }

}


