package com.example.delivery_service_part5_chapter06.presentation

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}