package com.example.subway_info_part5_chapter05.presenter

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}