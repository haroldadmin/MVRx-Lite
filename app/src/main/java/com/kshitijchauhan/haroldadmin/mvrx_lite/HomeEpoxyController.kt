package com.kshitijchauhan.haroldadmin.mvrx_lite

import android.widget.TextView
import com.airbnb.epoxy.*

class HomeEpoxyController: TypedEpoxyController<State.HomeState>() {

    override fun buildModels(state: State.HomeState) {
        when (state.numbers) {
            is Result.Success -> {
                state.numbers.data.forEach { number ->
                    number {
                        id(number)
                        number(number)
                    }
                }
            }
            else -> Unit
        }.safe
    }
}

@EpoxyModelClass(layout = android.R.layout.simple_list_item_1)
abstract class NumberModel: EpoxyModelWithHolder<NumberModel.NumberHolder>() {

    @EpoxyAttribute
    var number: Long? = null

    override fun bind(holder: NumberHolder) {
        super.bind(holder)
        holder.textView.text = number?.toString() ?: "Error"
    }

    inner class NumberHolder: KotlinEpoxyHolder() {
        val textView by bind<TextView>(android.R.id.text1)
    }
}