package livro.kotlin.com.mayson.listadecompras

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.db.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //implementando o adaptador
        val produtosAdapter = ProdutoAdapter(this)
        //produtosAdapter.addAll(produtosGlobal)

        //definindo o adaptador na lista
        list_view_produtos.adapter = produtosAdapter

        //definição do ouvinte do botão adicionar





        list_view_produtos.setOnItemLongClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->


            val item = produtosAdapter.getItem(position)
//
//            //removendo o item clicado na lista
            produtosAdapter.remove(item)


            //deletando do banco de dados
            if (item != null) {
                deletarProduto(item.id)
            }

            //retorno indicando que o clique foi realizado com sucesso
            toast("item deletado com sucesso")
            true

        }

        btn_adicionar.setOnClickListener{

            startActivity<CadastroActivity>()
        }


    }


    fun deletarProduto(idProduto: Int) {

        database.use {

            delete("produtos", "id = {id}", "id" to idProduto)
        }

    }



    override fun onResume() {
        super.onResume()

        database.use {


            select("produtos").exec {

                //Criando o parser para montar o objeto produto
                val parser = rowParser {

                        id: Int, nome: String,
                        quantidade: Int,
                        valor: Double,
                        foto: ByteArray? ->


                    //Montagem do objeto produto com as colunas do banco
                    Produto(id, nome, quantidade, valor, foto?.toBitmap())

                }

                //criando a lista de produtos com dados do banco
                var listProdutos = parseList(parser)

                //limpando os dados da lista e carregando as novas informações

                val adapter = list_view_produtos.adapter as ProdutoAdapter
                adapter.clear()
                adapter.addAll(listProdutos)

                //efetuando a multiplicação e soma da quantidade e valor dos produtos
                val soma = listProdutos.sumByDouble {
                    it.valor * it.quantidade
                }

                //formatando em formato moeda
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                txt_total.text = "TOTAL: ${f.format(soma)}"



            }


        }

    }



}

//    fun atualizaProduto(idProduto: Int) {
//
//        database.use {
//
//            update("produtos", "nome" to "nome").whereArgs("id = {id}", "id" to idProduto)
//        }
//    }
//
//}