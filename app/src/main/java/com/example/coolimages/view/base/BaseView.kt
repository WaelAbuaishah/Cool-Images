package com.example.coolimages.view.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.coolimages.MainActivity
import com.example.coolimages.utils.makeToast
import nabed.apps.services.utils.TranslationUtils

open class BaseView : Fragment(){
    private val inValidFormatMessage = "Missing %s annotation for view class %s"
    var hostActivity: Activity? = null
    var baseViewBinding: ViewDataBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = extractLayoutIdFromAnnotation()
        if (baseViewBinding == null) {
            baseViewBinding = DataBindingUtil.inflate(
                inflater,
                layoutId,
                container,
                false
            )
        }
        return baseViewBinding!!.root
    }


    private fun extractLayoutIdFromAnnotation(): Int {

        val layoutId: Int
        val viewClass = this.javaClass
        val layoutAnnotation = viewClass.getAnnotation(ALayoutableView::class.java)

        if (layoutAnnotation == null || layoutAnnotation.layoutId == 0) {
            val errorMessage = String.format(
                inValidFormatMessage,
                ALayoutableView::class.java.name,
                viewClass.name
            )
            throw RuntimeException(errorMessage)
        } else {
            layoutId = layoutAnnotation.layoutId
        }

        return layoutId
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            hostActivity = context
        } else {
            hostActivity = context as Activity
        }
    }

    fun showErrorMessage(message: String?) {
        makeToast(TranslationUtils.getErrorTranslation(message))
    }

    override fun onDetach() {
        super.onDetach()
        hostActivity = null
    }

}