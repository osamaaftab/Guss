package com.osamaaftab.gousto.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.osamaaftab.gousto.R
import com.osamaaftab.gousto.databinding.ActivityProductListBinding
import com.osamaaftab.gousto.presentation.adapter.GenericListAdapter
import com.osamaaftab.gousto.presentation.viewholder.ProductViewHolder
import com.osamaaftab.gousto.presentation.viewmodel.ProductViewModel
import com.osamaaftab.network.domain.model.ProductModel
import org.koin.android.viewmodel.ext.android.viewModel


class ProductListActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var activityMainBinding: ActivityProductListBinding
    private val productViewModel: ProductViewModel by viewModel()
    private var itineraryListAdapter: GenericListAdapter<ProductModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_product_list)
        activityMainBinding.lifecycleOwner = this
        initListener()
        initAdapter()
        initObserver()
        productViewModel.loadProductList()
    }

    private fun initListener() {
        activityMainBinding.refreshLayout.setOnRefreshListener(this)
    }

    private fun initObserver() {

        productViewModel.getProductList().observe(this, Observer {
            itineraryListAdapter?.itemList = it
            activityMainBinding.productRecyclerView.visibility = View.VISIBLE
        })
        productViewModel.getOnProgressShow().observe(this, Observer {
            if (it == true) {
                activityMainBinding.indeterminateBar.visibility = View.VISIBLE
            } else activityMainBinding.indeterminateBar.visibility = View.GONE
        })
        productViewModel.getOnErrorShow().observe(this, Observer {
            activityMainBinding.refreshLayout.isRefreshing = false
            if (it == true) {
                activityMainBinding.statusLbl.visibility = View.VISIBLE
            } else activityMainBinding.statusLbl.visibility = View.GONE
        })
        productViewModel.getProduct().observe(this, Observer {
           // Here we can start a new activity with data pass in intent.
        })
    }

    private fun initAdapter() {
        itineraryListAdapter = object : GenericListAdapter<ProductModel>() {

            override fun getViewHolder(viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder {
                return ProductViewHolder<ProductModel>(
                    viewDataBinding, productViewModel
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.product_item
            }
        }
        activityMainBinding.productRecyclerView.adapter = itineraryListAdapter
    }



    override fun onRefresh() {
        productViewModel.refreshProductList()
    }
}