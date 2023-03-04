package com.pluu.view.edittext

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import java.lang.ref.WeakReference

class EditTextWithContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private val parentRect = Rect()
    private var parentView: WeakReference<View>? = null

    private fun getContainer(): View? = parentView?.get()

    override fun getFocusedRect(r: Rect?) {
        super.getFocusedRect(r)
        val container = getContainer() ?: return
        if (r != null) {
            container.getFocusedRect(parentRect)
            r.bottom = parentRect.bottom
        }
    }

    override fun getGlobalVisibleRect(r: Rect?, globalOffset: Point?): Boolean {
        val container = getContainer()
        return container?.getGlobalVisibleRect(r, globalOffset)
            ?: super.getGlobalVisibleRect(r, globalOffset)
    }

    override fun requestRectangleOnScreen(rectangle: Rect?): Boolean {
        val container = getContainer()
        return if (container != null && rectangle != null) {
            val bottomOffset = container.height - height
            parentRect.set(
                rectangle.left,
                rectangle.top,
                rectangle.right,
                rectangle.bottom + bottomOffset
            )
            super.requestRectangleOnScreen(parentRect)
        } else {
            super.requestRectangleOnScreen(rectangle)
        }
    }

    fun setParentContainer(view: View) {
        this.parentView = WeakReference(view)
    }
}