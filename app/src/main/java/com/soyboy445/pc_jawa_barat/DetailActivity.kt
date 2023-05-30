package com.soyboy445.pc_jawa_barat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getintent = intent
        val foodString = getintent.getStringExtra("food")
        val foodJson = parseJson(foodString!!).get(0)
        nameDetail.text = foodJson.getString("name").toString()
        priceDetail.text = foodJson.getString("price").toString()
        descriptionDetail.text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh."

    }


}