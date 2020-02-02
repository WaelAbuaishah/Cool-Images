package com.example.coolimages.view.base



@kotlin.annotation.Target(AnnotationTarget.CLASS , AnnotationTarget.FILE)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ALayoutableView(
        /**
         * @return the layout id for the view to be inflated
         */
        val layoutId: Int)