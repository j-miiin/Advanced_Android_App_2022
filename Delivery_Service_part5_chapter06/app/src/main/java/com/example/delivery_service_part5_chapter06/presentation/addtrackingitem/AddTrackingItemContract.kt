package com.example.delivery_service_part5_chapter06.presentation.addtrackingitem

import com.example.delivery_service_part5_chapter06.data.entity.ShippingCompany
import com.example.delivery_service_part5_chapter06.presentation.BasePresenter
import com.example.delivery_service_part5_chapter06.presentation.BaseView

class AddTrackingItemContract {

    interface View : BaseView<Presenter> {

        fun showShippingCompaniesLoadingIndicator()

        fun hideShippingCompaniesLoadingIndicator()

        fun showSaveTrackingItemIndicator()

        fun hideSaveTrackingItemIndicator()

        fun showCompanies(companies: List<ShippingCompany>)

        fun enableSaveButton()

        fun disableSaveButton()

        fun showErrorToast(message: String)

        fun finish()
    }

    interface Presenter : BasePresenter {

        var invoice: String?
        var shippingCompanies: List<ShippingCompany>?
        var selectedShippingCompany: ShippingCompany?

        fun changeSelectedShippingCompany(companyName: String)

        fun changeShippingInvoice(invoice: String)

        fun saveTrackingItem()
    }
}