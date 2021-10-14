package livro.kotlin.com.mayson.listadecompras

import android.graphics.Bitmap

data class Produto(
    val id: Int,
    val nome:String,
    val quantidade:Int,
    val valor:Double,
    val foto: Bitmap?
)


