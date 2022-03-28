package com.v.nevi.p.sv.android.math.views

import android.content.Context
import android.util.AttributeSet


class ResizableButton(context: Context, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatButton(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, (width/2).toInt())
    }
}