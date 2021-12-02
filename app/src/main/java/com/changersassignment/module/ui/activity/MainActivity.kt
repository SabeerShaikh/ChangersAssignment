package com.changersassignment.module.ui.activity


import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.changersassignment.R
import com.changersassignment.config.ChangersProducationDI
import com.changersassignment.config.DI
import com.changersassignment.databinding.ActivityMainBinding
import com.changersassignment.domain.model.BreedData
import com.changersassignment.module.ui.adapter.CustomSpinner
import com.changersassignment.module.ui.adapter.DataAdapter
import com.changersassignment.module.ui.viewmodel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var vBinding: ActivityMainBinding
    private var viewModel: MainViewModel? = null
    private var di: DI? = null
    var uimodelList: ArrayList<BreedData> = ArrayList<BreedData>()
    private var adapter: DataAdapter? = null
    var limitSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        di = ChangersProducationDI(Application())
        vBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vBinding.callback = this
        viewModel = ViewModelProvider(
            this,
            (di as ChangersProducationDI).provideViewModelFactory()
        ).get<MainViewModel>(MainViewModel::class.java)
        vBinding.rvData.layoutManager = GridLayoutManager(this, 3)
        adapter = DataAdapter()
        vBinding.rvData.adapter = adapter
        vBinding.pbMain.visibility = View.VISIBLE
        vBinding.rvData.setHasFixedSize(true)
        vBinding.rvData.itemAnimator = DefaultItemAnimator()

        loadPostData(1, 20)

        vBinding.swipeRefreshLayout.setOnRefreshListener {
            uimodelList = ArrayList<BreedData>()
            adapter?.clearData()
            loadPostData(1, 20)
        }

        vBinding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    private fun loadPostData(page: Int, limit: Int) {
        limitSize += 20
        viewModel?.postsData(page, limit)?.observe(this@MainActivity, Observer {

            if (it.success) {
                vBinding.pbMain.visibility = View.GONE
                vBinding.swipeRefreshLayout.isRefreshing = false

                it.response?.let { element ->
                    uimodelList.addAll(element)
                }
                adapter?.appendData(uimodelList)
                getSpinnerData(uimodelList)

                vBinding.rvData.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (!recyclerView.canScrollVertically(1) && dy != 0) {
                            if (limitSize == 100) {
                                loadPostData(page + 1, 2)
                            }
                            if (limitSize < 102) {
                                loadPostData(page + 1, 20)
                            }
                        }
                    }
                })

            } else {
                vBinding.swipeRefreshLayout.isRefreshing = false

                vBinding.pbMain.visibility = View.GONE

            }
        })

    }


    private fun getSpecificBreed(name: String) {
        var uimodelList: ArrayList<BreedData> = ArrayList<BreedData>()

        viewModel?.getSpecificBreed(name)?.observe(this@MainActivity, Observer {

            if (it.success) {
                vBinding.pbMain.visibility = View.GONE
                it.response?.let { element ->
                    uimodelList.addAll(element)
                }
                if (uimodelList.size > 0) {
                    adapter?.updateData(uimodelList)
                }

            } else {
                //  toast("User Data is not loaded")
                vBinding.pbMain.visibility = View.GONE

            }
        })

    }


    private fun getSpinnerData(breedDataList: ArrayList<BreedData>) {
        var newBreedDataList: ArrayList<BreedData> = ArrayList<BreedData>()
        newBreedDataList.add(0, BreedData())
        newBreedDataList.addAll(breedDataList)

        val spinnerAdapter = CustomSpinner(this, newBreedDataList)
        vBinding.mySpinner.adapter = spinnerAdapter
        vBinding.mySpinner.setSelection(0, false)
        vBinding.mySpinner.onItemSelectedListener = this@MainActivity


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position > 0) {
            getSpecificBreed(uimodelList[position].name)
        }

    }


    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}