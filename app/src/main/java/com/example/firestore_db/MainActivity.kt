package com.example.firestore_db
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db=Firebase.firestore

        var user= hashMapOf("first" to "harry",
            "last" to "stark",
            "DoB" to 1999
            )
        db.collection("starksFamily").document("Harry Stark")
            .set(user)
            .addOnSuccessListener {
                Log.d("db", "success")
                Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.w("db", "Error adding document", it)
                Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show()
            }

       var s=db.collection("starksFamily").document("Harry Stark")
        s.get().addOnSuccessListener {
            var ss=it.get("first").toString()
            Toast.makeText(this,ss,Toast.LENGTH_SHORT).show()

            s.update("first","jonsnow")
        }

        var up= hashMapOf<String,Any>("last" to FieldValue.delete())
        s.update(up).addOnSuccessListener {
            Toast.makeText(this, "Deleted last field", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        var u = hashMapOf("Name" to "Harry Stark")

        s.set(u, SetOptions.merge())

    }
}