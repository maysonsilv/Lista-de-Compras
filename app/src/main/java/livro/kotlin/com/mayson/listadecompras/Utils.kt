package livro.kotlin.com.mayson.listadecompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.ByteArrayOutputStream


val produtosGlobal = mutableListOf<Produto>()

fun Bitmap.toByteArray(): ByteArray{

    // responsavel por fazer a transformação do Bitmap em BitArray
    val stream = ByteArrayOutputStream()

    //comprimindo a imagem
    this.compress(android.graphics.Bitmap.CompressFormat.PNG, 0, stream)

    //transformando em um array de caracteres
    return stream.toByteArray()
}

fun ByteArray.toBitmap() :Bitmap{

    return BitmapFactory.decodeByteArray(this, 0, this.size);

}

