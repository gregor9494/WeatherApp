package com.example.grzesiek.myapplication.screens.main


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grzesiek.myapplication.R
import com.example.grzesiek.myapplication.core.platform.BaseActivity
import com.example.grzesiek.myapplication.core.platform.EventObserver
import com.example.grzesiek.myapplication.databinding.ActivityMainBinding
import com.example.grzesiek.myapplication.utils.toViewVisibility
import com.google.android.material.snackbar.Snackbar
import eu.davidea.flexibleadapter.FlexibleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var activityMainBinding: ActivityMainBinding
    private val flexibleAdapter = FlexibleAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.lifecycleOwner = this
        activityMainBinding.viewModel = mainViewModel
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = flexibleAdapter
        initObservers()
        mainViewModel.loadActualWeather()
        mainViewModel.listenWeatherUpdateError()
    }

    fun initObservers() {
        observeErrorMessage()
        observeViewState()
    }

    fun observeViewState() {
        mainViewModel.actualState.observe(this, Observer {
            llError.visibility = (it is MainViewState.Error).toViewVisibility()
            if (it is MainViewState.Error) {
                tvErrorInfo.setText(it.weatherUpdateError.messageRes)
                bTryAgain.visibility = it.weatherUpdateError.canTryAgain.toViewVisibility()
            }
            progressBar.visibility = (it is MainViewState.Loading).toViewVisibility()
            if (it is MainViewState.Result) {
                val data = it.weatherDataWithForecast
                flexibleAdapter.updateDataSet(MainActivityListItemsCreator.createList(data))
            }
        })
    }

    fun observeErrorMessage() {
        mainViewModel.actualErrorMessage.observe(this, EventObserver{
            Snackbar.make(container, it, Snackbar.LENGTH_LONG).show()
        })
    }

}
