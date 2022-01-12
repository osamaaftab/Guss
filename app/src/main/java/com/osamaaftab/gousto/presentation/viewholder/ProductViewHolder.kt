package com.osamaaftab.gousto.presentation.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.gousto.BR
import com.osamaaftab.gousto.presentation.adapter.GenericListAdapter
import com.osamaaftab.gousto.presentation.viewmodel.ProductViewModel

class ProductViewHolder<T>(
    private val viewDataBinding: ViewDataBinding,
    private val productViewModel: ProductViewModel
) : RecyclerView.ViewHolder(viewDataBinding.root),
    GenericListAdapter.Binder<T> {


    override fun bind(data: T, position: Int) {
        viewDataBinding.setVariable(BR.product, data)
        viewDataBinding.setVariable(BR.view_model, productViewModel)
    }
}