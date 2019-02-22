package com.kshitijchauhan.haroldadmin.mvrx_lite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kshitijchauhan.haroldadmin.mvrxlite.base.MVRxLiteView
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class HomeFragment : Fragment(), MVRxLiteView<State.HomeState> {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val homeViewModel: HomeViewModel by viewModel {
        val initialState = State.HomeState(Result.Loading())
        parametersOf(initialState)
    }
    private val homeEpoxyController: HomeEpoxyController by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNumbers.setController(homeEpoxyController)
        btNumbers.setOnClickListener {
            homeViewModel.getNumbers()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel.state.observe(viewLifecycleOwner, Observer {
            this@HomeFragment.renderState(it)
        }
        )
    }

    override fun renderState(state: State.HomeState) {
        homeEpoxyController.cancelPendingModelBuild()
        homeEpoxyController.setData(state)
    }
}
