package livro.kotlin.com.mayson.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.list_view_item.view.*
import java.text.NumberFormat
import java.util.Locale


class ProdutoAdapter(context: Context) : ArrayAdapter<Produto>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val v:View

        if (convertView != null) {
            v = convertView
        } else {
            //infla o layout
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)

        }

        val item = getItem(position)

        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foco)


        if (item != null) {
            txt_qtd.text = item.quantidade.toString()
        }
        if (item != null) {
            txt_produto.text = item.nome
        }


        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        //formatando a variável no formato da moeda
        if (item != null) {
            txt_valor.text = f.format(item.valor)
        }



        if (item?.foto != null){
            img_produto.setImageBitmap(item?.foto)
        }


        return v

        //obtendo a instancia do objeto de formatação


    }






}