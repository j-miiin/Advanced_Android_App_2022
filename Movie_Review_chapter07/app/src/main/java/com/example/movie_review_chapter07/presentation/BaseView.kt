package com.example.movie_review_chapter07.presentation

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}